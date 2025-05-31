package a_gui2;

import java.util.ArrayList;
import java.util.HashMap;
import b_entitaeten.Ball;
import b_entitaeten.LogikHelper;
import b_entitaeten.Mannschaft;
import b_entitaeten.Roboter;
import b_entitaeten.RoboterHelper;
import b_entitaeten.Spielfeld;
import b_entitaeten.Tor;

public class Navigation {

	static Mannschaft heimmannschaft;
	static Mannschaft gastmannschaft;
	static Ball ball;
	static ArrayList<Object> mannschaften;
	String hallo;

	public static void main(String[] args) {

		heimmannschaft = new Mannschaft(new HashMap<String, Roboter>(), new Tor(Spielfeld.zeilen / 2, 0));
		gastmannschaft = new Mannschaft(new HashMap<String, Roboter>(),
				new Tor(Spielfeld.zeilen / 2, Spielfeld.spalten - 1));
		ball = Ball.getInstance(Spielfeld.zeilen / 2, Spielfeld.spalten / 2);
		menuInteraktion();
	}

	private static String menuStartText = "0. Spiel laden\n1. Mannschaften anlegen\n2. Spiel starten\n3. Programm beenden";
	private static String menuSpielText = "0. Spielrunde ausführen\n1. Spielstand anzeigen\n2. Spiel speichern\n3. Spiel beenden";

	private static void menuStart(int menuOption) {
		switch (menuOption) {
		case 0:
			// Spiel laden
			try {
				ArrayList<Object>entitaeten = LogikHelper.laden();
				heimmannschaft = (Mannschaft) entitaeten.get(0);
				gastmannschaft = (Mannschaft) entitaeten.get(1);
				ball = (Ball) entitaeten.get(2);
				System.out.println("\nDas Spiel wurde erfolgreich geladen.");
				menuSpielSchleife();
			} catch (NullPointerException e) {
				System.out.println("\nDas Spiel konnte nicht geladen werden. Bitte beginnen Sie ein ein neues Spiel.");
			}
			break;
		case 1:
			// Mannschaft anlegen
			LogikHelper.mannschaftAnlegen(heimmannschaft, gastmannschaft);
			break;
		case 2:
			// Spiel starten
			if (heimmannschaft.spieler.isEmpty() && gastmannschaft.spieler.isEmpty()) {
				System.out.println("\nBitte legen Sie zunächst beide Mannschaften an.");
				menuStartSchleife();
			}
			LogikHelper.ballbesitzSetzen(heimmannschaft, gastmannschaft);
			LogikHelper.initialePositionenSetzen(ball, heimmannschaft, gastmannschaft);
			menuSpielSchleife();
			break;
		case 3:
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
			Spielfeld.spielfeldMalen(ball, heimmannschaft, gastmannschaft);
			Spielfeld.spielfeldAnzeigen();
			RoboterHelper.spielzugAusführen(heimmannschaft, gastmannschaft);
			break;
		case 1:
			// Spielstand anzeigen
			heimmannschaft.gegenueberstellen(gastmannschaft);
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
		System.out.println("\nBitte geben Sie eine Ganzzahl ein, um im Menu zu navigieren.");
	}

	private static void menuStartSchleife() {
		while (true) {
			System.out.println("\n" + menuStartText);
			menuStart(LogikHelper.menuEingabe(3));
		}
	}

	private static void menuSpielSchleife() {
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