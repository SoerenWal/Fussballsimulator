package a_gui2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import b_entitaeten.Ball;
import b_entitaeten.LogikHelper;
import b_entitaeten.Mannschaft;
import b_entitaeten.Roboter;
import b_entitaeten.Spielfeld;
import b_entitaeten.Tor;

/**
 * Einstiegspunkt des Programms. Initialisiert die Teams und startet die Menüinteraktion.
 */


public class Navigation {

	static Mannschaft heimmannschaft;
	static Mannschaft gastmannschaft;
	static Ball ball;

	public static void main(String[] args) {
		heimmannschaft = new Mannschaft(new HashMap<String, Roboter>(), new Tor(Spielfeld.zeilen / 2, 0));
		gastmannschaft = new Mannschaft(new HashMap<String, Roboter>(),
				new Tor(Spielfeld.zeilen / 2, Spielfeld.spalten - 1));
		ball = Ball.getInstance(Spielfeld.zeilen / 2, Spielfeld.spalten / 2);
		menuInteraktion();
	}

	private static String menuStartText = "0. Spiel laden\n1. Mannschaften laden\n2. Mannschaften anlegen\n3. Spiel starten\n4. Mannschaften speichern\n5. Programm beenden";
	private static String menuSpielText = "0. Spielrunde ausführen\n1. Spielstand anzeigen\n2. Spiel speichern & beenden";

	
	
	private static void menuStart(int menuOption) {
		switch (menuOption) {
		case 0:
			// Spiel laden
			ArrayList<Object> entitaeten;
			try {
				entitaeten = LogikHelper.laden(); // wirft eine NullPointerException
				heimmannschaft = (Mannschaft) entitaeten.get(0);
				gastmannschaft = (Mannschaft) entitaeten.get(1);
				ball = (Ball) entitaeten.get(2); // wirft eine IndexOutOfBoundsException
				System.out.println("\nIhr Spielstand wurde erfolgreich geladen.");
				menuSpielSchleife();
			} catch (NullPointerException e) {
				System.out.println(
						"\nEs konnte leider kein Spiel geladen werden.\nLegen Sie bitte zunächst beide Mannschaften an.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println(
						"\nIhre Mannschaften wurden erfolgreich geladen.\nSie können ein neues Spiel starten.");
			}
			break;
		case 1:
			// Mannschaften laden
			try {
				entitaeten = LogikHelper.laden(); // wirft eine NullPointerException
				heimmannschaft = (Mannschaft) entitaeten.get(0);
				gastmannschaft = (Mannschaft) entitaeten.get(1);
				System.out.println(
						"\nIhre Mannschaften wurden erfolgreich geladen.\nSie können ein neues Spiel starten.");
			} catch (NullPointerException e) {
				System.out.println(
						"\nEs konnte leider keine Mannschaften geladen werden.\nLegen Sie bitte zunächst beide Mannschaften an.");
			}
			break;
		case 2:
			// Mannschaft anlegen
			LogikHelper.mannschaftAnlegen(heimmannschaft, gastmannschaft);
			break;
		case 3:
			// Spiel starten
			if (heimmannschaft.getSpieler().isEmpty() && gastmannschaft.getSpieler().isEmpty()) {
				System.out.println("\nLaden Sie bitte ein Spiel oder legen Sie zunächst beide Mannschaften an.");
				menuStartSchleife();
			}
			LogikHelper.ballbesitzSetzen(ball, heimmannschaft, gastmannschaft);
			LogikHelper.initialePositionenSetzen(ball, heimmannschaft, gastmannschaft);
			LogikHelper.stelleMannschaftenAuf(heimmannschaft, gastmannschaft);
			LogikHelper.abfragenSpieldauer(ball);
			menuSpielSchleife();
			break;
		case 4:
			// Mannschaften speichern
			ArrayList<Object> mannschaften = new ArrayList<>();
			if (heimmannschaft != null || gastmannschaft != null) {
				if (heimmannschaft != null) {
					mannschaften.add(heimmannschaft);
				}
				if (gastmannschaft != null) {
					mannschaften.add(gastmannschaft);
					LogikHelper.speichern(mannschaften);
				}
			} else {
				System.out.println("Bitte legen Sie zunächst eine Mannschaften an.");
			}
			break;
		case 5:
			// Programm beenden
			System.out.println("\nDas Programm wurde erfolgreich beendet.\nBis zum nächsten Mal!");
			System.exit(1);
			break;
		}

	}

	private static void menuSpiel(int menuOption) {
		switch (menuOption) {
		case 0:
			// Spielrunde ausführen
			System.out.println("\nNoch " + Navigation.ball.spieldauer + " Spielrunden verbleiben.");
			Spielfeld.maleSpielfeld(ball, heimmannschaft, gastmannschaft);
			LogikHelper.aktualisiereBallbesitz(ball, heimmannschaft, gastmannschaft);
			Spielfeld.maleSpielfeld(ball, heimmannschaft, gastmannschaft);
			Spielfeld.spielfeldAnzeigen();
			spielzugAusfuehren(ball, heimmannschaft, gastmannschaft);
			break;
		case 1:
			// Spielstand anzeigen
			heimmannschaft.gegenueberstellen(ball, gastmannschaft);
			break;
		case 2:
			// Spiel speichern
			ArrayList<Object> entitaeten = new ArrayList<>();
			entitaeten.add(heimmannschaft);
			entitaeten.add(gastmannschaft);
			entitaeten.add(ball);
			LogikHelper.speichern(entitaeten);
			LogikHelper.siegerAnzeigen(heimmannschaft, gastmannschaft);
			menuStartSchleife();
			break;
		}
	}

	/**
     * Führt eine komplette Spielrunde für beide Mannschaften aus.
     *
     * @param ball
     * @param heimmannschaft 
     * @param gastmannschaft 
     * @return true, wenn die Runde abgeschlossen wurde
     */
	
	public static boolean spielzugAusfuehren(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		int menuEingabe;
		Scanner sc = new Scanner(System.in);
		System.out.println("\nBitte wählen Sie die Aktionenen für " + heimmannschaft.getName() + ".");
		while (true) {
			System.out.println(
					"\n0. Stuermer\n1. Mittelfeldspieler\n2. Mittelfeldspieler2\n3. Verteidiger\n4. Torwart\n5. Spielzug beenden");

			menuEingabe = LogikHelper.menuEingabe(5);
			Roboter r = heimmannschaft.menuSpieler(menuEingabe);
			if (r == null) {
				break;
			}
			System.out.println(
					"\n0. Keine Aktion\n1. Laufen\n2. Passen\n3. Torschuss\n4. Blocken\n5. Energie aufladen\n6. Ball aufheben");
			menuSpielzug(r, LogikHelper.menuEingabe(6), ball, heimmannschaft);

		}
		System.out.println("\nBitte wählen Sie die Aktionenen für " + gastmannschaft.getName() + ".");
		while (true) {
			System.out.println(
					"\n0. Stürmer\n1. Mittelfeldspieler\n2. Mittelfeldspieler2\n3. Verteidiger\n4. Torwart\n5. Spielzug beenden");
			menuEingabe = LogikHelper.menuEingabe(5);
			Roboter r = gastmannschaft.menuSpieler(menuEingabe);
			if (r == null) {
				break;
			}
			System.out.println(
					"\n0. Keine Aktion\n1. Laufen\n2. Passen\n3. Torschuss\n4. Blocken\n5. Energie aufladen\n6. Ball aufheben");
			menuSpielzug(r, LogikHelper.menuEingabe(6), ball, heimmannschaft);

		}

		for (Roboter r : heimmannschaft.getSpieler().values()) {
			r.ausfallen(ball);
		}
		for (Roboter r : gastmannschaft.getSpieler().values()) {
			r.ausfallen(ball);
		}

		ball.spieldauer--;
		return true;
	}

	private static void menuSpielzug(Roboter r, int menuOption, Ball ball, Mannschaft mannschaft) {
		switch (menuOption) {
		case 0:
			// Keine Aktion
			break;
		case 1:
			// Laufen
			if (!r.getAusgefallen()) {
				r.laufen();
				r.ausfallen(ball);
				LogikHelper.aktualisiereBallbesitz(ball, heimmannschaft, gastmannschaft);
				Spielfeld.maleSpielfeld(ball, heimmannschaft, gastmannschaft);
				Spielfeld.spielfeldAnzeigen();
			} else {
				System.out.println("\n" + r.getName() + " fällt weiterhin aus.");
			}
			break;
		case 2:
			// Passen
			if (!r.getAusgefallen()) {
				System.out.println("\nWelchem Teamkollegen möchten Sie passen?");
				System.out.println(
						"\n0. Stürmer\n1. Mittelfeldspieler\n2. Mittelfeldspieler2\n3. Verteidiger\n4. Torwart");
				if (heimmannschaft.getSpieler().values().contains(r)) {
					r.passen(heimmannschaft.menuSpieler(LogikHelper.menuEingabe(5)), ball);
					r.ausfallen(ball);
				} else {
					r.passen(gastmannschaft.menuSpieler(LogikHelper.menuEingabe(5)), ball);
					r.ausfallen(ball);
				}
			} else {
				System.out.println("\n" + r.getName() + " fällt weiterhin aus.");
			}

			LogikHelper.aktualisiereBallbesitz(ball, heimmannschaft, gastmannschaft);
			Spielfeld.maleSpielfeld(ball, heimmannschaft, gastmannschaft);
			Spielfeld.spielfeldAnzeigen();
			break;
		case 3:
			// Torschuss
			if (!r.getAusgefallen()) {
				r.schießen(heimmannschaft, gastmannschaft);
				r.ausfallen(ball);
			} else {
				System.out.println("\n" + r.getName() + " fällt weiterhin aus.");
			}
			LogikHelper.aktualisiereBallbesitz(ball, heimmannschaft, gastmannschaft);
			Spielfeld.maleSpielfeld(ball, heimmannschaft, gastmannschaft);
			Spielfeld.spielfeldAnzeigen();
			break;
		case 4:
			// Blocken
			if (!r.getAusgefallen()) {
				if (heimmannschaft.getSpieler().values().contains(r)) {
					r.blocken(ball, gastmannschaft.holeSpielerBallbesitz(ball));
					r.ausfallen(ball);
				} else {
					r.blocken(ball, heimmannschaft.holeSpielerBallbesitz(ball));
					r.ausfallen(ball);
				}
				LogikHelper.aktualisiereBallbesitz(ball, heimmannschaft, gastmannschaft);
				Spielfeld.maleSpielfeld(ball, heimmannschaft, gastmannschaft);
				Spielfeld.spielfeldAnzeigen();
			} else {
				System.out.println("\n" + r.getName() + " fällt weiterhin aus.");
			}
			break;
		case 5:
			// Energie aufladen
			if (!r.getAusgefallen()) {
				r.energieAufladen();
			} else {
				System.out.println("\n" + r.getName() + " fällt weiterhin aus.");
			}
			break;
		case 6:
			// Ball aufheben
			if (!heimmannschaft.pruefeBallbesitz(ball) && !gastmannschaft.pruefeBallbesitz(ball)) {
				if (!r.getAusgefallen()) {
					r.ballAufheben(ball);
					LogikHelper.aktualisiereBallbesitz(ball, heimmannschaft, gastmannschaft);
					Spielfeld.maleSpielfeld(ball, heimmannschaft, gastmannschaft);
					Spielfeld.spielfeldAnzeigen();
				} else {
					System.out.println("\n" + r.getName() + " fällt weiterhin aus.");
				}
			}
		}
	}

	private static void titelAnzeigen() {
		// Quelle: https://www.asciiart.eu/text-to-ascii-art
		System.out.println(" _____       ___ _           _ _     _                 _       _             \r\n"
				+ "|  ___|   _ / _ \\ |__   __ _| | |___(_)_ __ ___  _   _| | __ _| |_ ___  _ __ \r\n"
				+ "| |_ | | | | |/ / '_ \\ / _` | | / __| | '_ ` _ \\| | | | |/ _` | __/ _ \\| '__|\r\n"
				+ "|  _|| |_| | |\\ \\ |_) | (_| | | \\__ \\ | | | | | | |_| | | (_| | || (_) | |   \r\n"
				+ "|_|   \\__,_| ||_/_.__/ \\__,_|_|_|___/_|_| |_| |_|\\__,_|_|\\__,_|\\__\\___/|_|   \r\n"
				+ "           |_|                                                               ");
		System.out.println("\nWillkommen im Start-Menu!\nBitte geben Sie eine Ganzzahl ein, um im Menu zu navigieren.");
	}

	private static void menuStartSchleife() {
		while (true) {
			System.out.println("\n" + menuStartText);
			menuStart(LogikHelper.menuEingabe(5));
		}
	}

	private static void menuSpielSchleife() {
		System.out.println("\nWillkommen im Spielmenu!");
		while (true) {
			System.out.println("\n" + menuSpielText);
			menuSpiel(LogikHelper.menuEingabe(2));
		}
	}

	/**
     * Startet das Menüsystem für die Spielkonfiguration und den Spielstart.
     */
	
	private static void menuInteraktion() {
		titelAnzeigen();
		menuStartSchleife();
	}
}