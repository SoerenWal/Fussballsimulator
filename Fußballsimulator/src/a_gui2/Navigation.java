package a_gui2;

import java.util.ArrayList;
import java.util.HashMap;
import b_entitaeten.Ball;
import b_entitaeten.LogikHelper;
import b_entitaeten.Mannschaft;
import b_entitaeten.Roboter;
import b_entitaeten.Spielfeld;
import b_entitaeten.Tor;

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
	private static String menuSpielText = "0. Spielrunde ausführen\n1. Spielstand anzeigen\n2. Spiel speichern\n3. Spiel speichern & beenden";

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
				System.out.println("\nIhre Mannschaften wurden erfolgreich geladen.\nSie können ein neues Spiel starten.");
			}
			break;
		case 1:
			// Mannschaften laden
			try {
				entitaeten = LogikHelper.laden(); // wirft eine NullPointerException
				heimmannschaft = (Mannschaft) entitaeten.get(0);
				gastmannschaft = (Mannschaft) entitaeten.get(1);
				System.out.println("\nIhre Mannschaften wurden erfolgreich geladen.\nSie können ein neues Spiel starten.");
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
			if (heimmannschaft.spieler.isEmpty() && gastmannschaft.spieler.isEmpty()) {
				System.out.println("\nLaden Sie bitte ein Spiel oder legen Sie zunächst beide Mannschaften an.");
				menuStartSchleife();
			}
			LogikHelper.ballbesitzSetzen(ball, heimmannschaft, gastmannschaft);
			LogikHelper.initialePositionenSetzen(ball, heimmannschaft, gastmannschaft);
			LogikHelper.stelleMannschaftenAuf(heimmannschaft, gastmannschaft);
			LogikHelper.abfragenSpieldauer();
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
			heimmannschaft.pruefeBallbesitz(ball);
			gastmannschaft.pruefeBallbesitz(ball);
			System.out.println("\nNoch " + Ball.spieldauer + " Spielrunden verbleiben.");
			Spielfeld.maleSpielfeld(ball, heimmannschaft, gastmannschaft);
			Spielfeld.spielfeldAnzeigen();
			if (!LogikHelper.spielzugAusführen(ball, heimmannschaft, gastmannschaft)) {
				menuSpiel(3);
			}
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
			break;
		case 3:
			// Spiel beenden
			LogikHelper.siegerAnzeigen(heimmannschaft, gastmannschaft);
			menuStartSchleife();
			break;
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
			menuSpiel(LogikHelper.menuEingabe(3));
		}
	}

	public static void menuInteraktion() {
		titelAnzeigen();
		menuStartSchleife();
	}
}