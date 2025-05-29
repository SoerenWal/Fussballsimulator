package a_gui2;

import java.util.Collection;
import java.util.HashMap;
import b_entitaeten.Ball;
import b_entitaeten.LogikHelper;
import b_entitaeten.Mannschaft;
import b_entitaeten.Roboter;
import b_entitaeten.RoboterHelper;
import b_entitaeten.Spielfeld;
import b_entitaeten.Tor;

public class Navigation {

	public static void main(String[] args) {
		Mannschaft heimmannschaft = new Mannschaft(new HashMap<String, Roboter>());
		Mannschaft gastmannschaft = new Mannschaft(new HashMap<String, Roboter>());
		Ball ball = Ball.getInstance(Spielfeld.spielfeldZeilen / 2, Spielfeld.spielfeldSpalten / 2);
		Tor[] tore = { new Tor(Spielfeld.spielfeldZeilen / 2, 0),
				new Tor(Spielfeld.spielfeldZeilen / 2, Spielfeld.spielfeldSpalten - 1) };
		Collection<Roboter> spieler = heimmannschaft.spieler.values();
		menuInteraktion(tore, ball, spieler, heimmannschaft, gastmannschaft);

	}

	private static String menuStartText = "0. Spiel laden\n1. Mannschaften anlegen\n2. Spiel starten\n3.Programm beenden";
	private static String menuSpielText = "0. Spielrunde ausführen\n1. Spielstand anzeigen\n2. Spiel speichern\n3. Spiel beenden";

	private static void menuStart(Tor[] tore, Ball ball, Collection<Roboter> spieler, Mannschaft heimmannschaft,
			Mannschaft gastmannschaft, int menuOption) {
		switch (menuOption) {
		case 0:
			// Spiel laden
			// methodenaufruf
			break;
		case 1:
			// Mannschaft anlegen
			Mannschaft.mannschaftAnlegen(tore, heimmannschaft, gastmannschaft);
			break;
		case 2:
			// Spiel starten
			if(LogikHelper.ballbesitzSetzen(heimmannschaft, gastmannschaft) && LogikHelper.initialePositionenSetzen(tore, heimmannschaft, gastmannschaft)) {
				menuSpielSchleife(tore, ball, spieler, heimmannschaft, gastmannschaft);
			}
			break;
		case 3:		
			// Programm beenden
			System.out.println("\nDas Programm wurde erfolgreich beendet.\nBis zum nächsten Mal!");
			System.exit(0);
			break;
		}
	}

	private static void menuSpiel(Tor[] tore, Ball ball, Collection<Roboter> spieler, Mannschaft heimmannschaft,
			Mannschaft gastmannschaft, int menuOption) {
		switch (menuOption) {
		case 0:
			// Spielrunde ausführen
			Spielfeld.spielfeldMalen(tore, ball, heimmannschaft, gastmannschaft);
			Spielfeld.spielfeldAnzeigen();
			RoboterHelper.spielzugAusführen(heimmannschaft, gastmannschaft);
			break;
		case 1:
			// Spielstand anzeigen
			heimmannschaft.gegenueberstellen(gastmannschaft);
			break;
		case 2:
			// Spiel speichern
			// methode ausführen
			break;
		case 3:
			// Spiel beenden
			LogikHelper.siegerAnzeigen(heimmannschaft, gastmannschaft);
			menuStartSchleife(tore, ball, spieler, heimmannschaft, gastmannschaft);
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
		System.out.println("\nBitte geben Sie eine Ganzzahl ein, um im Menu zu navigieren.");
	}
	
	private static void menuStartSchleife(Tor[] tore, Ball ball, Collection<Roboter> spieler, Mannschaft heimmannschaft,
			Mannschaft gastmannschaft) {
		while (true) {
			System.out.println("\n" + menuStartText);
			menuStart(tore, ball, spieler, heimmannschaft, gastmannschaft, LogikHelper.menuEingabe(3));
		}
	}
	
	private static void menuSpielSchleife(Tor[] tore, Ball ball, Collection<Roboter> spieler, Mannschaft heimmannschaft,
			Mannschaft gastmannschaft) {
		while (true) {
			System.out.println("\n" + menuSpielText);
			menuSpiel(tore, ball, spieler, heimmannschaft, gastmannschaft, LogikHelper.menuEingabe(3));
		}
	}

	public static void menuInteraktion(Tor[] tore, Ball ball, Collection<Roboter> spieler, Mannschaft heimmannschaft,
			Mannschaft gastmannschaft) {
		titelAnzeigen();
		menuStartSchleife(tore, ball, spieler, heimmannschaft, gastmannschaft);
	}
}