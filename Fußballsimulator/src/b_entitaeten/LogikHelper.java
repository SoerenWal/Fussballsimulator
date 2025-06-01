package b_entitaeten;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import c_datenhaltung.Datenhaltung;

public class LogikHelper {

	public static int spieldauer;

	public static int menuEingabe(int maxMenuOption) {
		Scanner sc = new Scanner(System.in);
		int eingabe = 0;
		while (true) {
			System.out.print("\nEingabe: ");
			try {
				eingabe = sc.nextInt();
				if (eingabe >= 0 && eingabe <= maxMenuOption) {
					break;
				} else {
					System.out.println("\nBitte geben Sie eine Ganzzahl zwischen 0 und " + maxMenuOption + " an.");
				}
			} catch (InputMismatchException ime) {
				// Der Scanner entfernt bei Auftreten der Exception nicht den gültigen Wert,
				// sondern liest diesen erneut ein, wodurch eine Endlosschleife entsteht.
				sc.next();
				System.out.println("\nBitte geben Sie eine Ganzzahl zwischen 0 und " + maxMenuOption + " an.");
			}
		}
		return eingabe;
	}

	/**
	 * Durch diese Methode wird eine gastmanschaft und eine Heimmanschaft angelegt
	 * 
	 * @param heimmannschaft
	 * @param gastmannschaft
	 */

	public static void mannschaftAnlegen(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("\nWelche Mannschaft möchten Sie zuerst anlegen?");
		System.out.println("0. Heimmannschaft\n1. Gastmannschaft\n2. Zurück");
		switch (LogikHelper.menuEingabe(2)) {
		case 0:
			heimmannschaft.anlegen();
			gastmannschaft.anlegen();
			break;
		case 1:
			gastmannschaft.anlegen();
			heimmannschaft.anlegen();
			break;
		case 2:
			break;
		}
	}

	public static void ballbesitzSetzen(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("\nWelche Mannschaft darf die Partie mit Ballbesitz beginnen?");
		System.out.println("0. " + heimmannschaft.name + "\n1. " + gastmannschaft.name);
		switch (LogikHelper.menuEingabe(2)) {
		case 0:
			heimmannschaft.spieler.get("Stürmer").setBallBesitz(true);
			heimmannschaft.ballBesitz = true;
			ball.zeile = heimmannschaft.spieler.get("Stürmer").getZeile();
			ball.spalte = heimmannschaft.spieler.get("Stürmer").getSpalte();
			
		case 1:
			gastmannschaft.spieler.get("Stürmer").setBallBesitz(true);
			gastmannschaft.ballBesitz = true;
		}
	}

	public static void siegerAnzeigen(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		if (heimmannschaft.tore > gastmannschaft.tore) {
			System.out.print("\n" + heimmannschaft.name + " hat die Fußball-Partie gegen " + gastmannschaft.name
					+ " mit einem Sieg (" + heimmannschaft.tore + ":" + gastmannschaft.tore + ") beendet.");
		} else if (heimmannschaft.tore < gastmannschaft.tore) {
			System.out.print("\n" + gastmannschaft.name + " hat die Fußball-Partie gegen " + heimmannschaft.name
					+ " mit einer Niederlage (" + gastmannschaft.tore + ":" + heimmannschaft.tore + ") beendet.");
		} else {
			System.out.println("\n" + heimmannschaft.name + " hat die Fußball-Partie gegen " + gastmannschaft.name
					+ " mit einem Unentschieden (" + heimmannschaft.tore + ":" + gastmannschaft.tore + ") beendet.");
		}
	}

	public static boolean initialePositionenSetzen(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("\nFür welche Mannschaft möchten Sie zuerst die intiale Aufstellung festlegen?");
		System.out.println("0. " + heimmannschaft.name + "\n1. " + gastmannschaft.name);
		switch (LogikHelper.menuEingabe(2)) {
		case 0:
			heimmannschaft.aufstellungWaehlen(true);
			heimmannschaft.aufstellen();
			gastmannschaft.aufstellungWaehlen(false);
			gastmannschaft.aufstellen();
			return true;
		case 1:
			gastmannschaft.aufstellungWaehlen(false);
			gastmannschaft.aufstellen();
			heimmannschaft.aufstellungWaehlen(true);
			heimmannschaft.aufstellen();
			return true;
		}
		return false;
	}

	public static void speichern(ArrayList<Object> entitaeten) {
		Datenhaltung.schreibeInDatei(entitaeten);
	}

	public static ArrayList<Object> laden() {
		try {
			return Datenhaltung.leseAusDatei();
		} catch (ClassNotFoundException e) {
			System.out.println("\nDas Spiel konnte nicht geladen werden. Bitte beginnen Sie ein ein neues Spiel.");
		}
		return null;

	}

	public static int abfragenSpieldauer() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("\nWie viele Spielzüge möchten Sie insgesamt spielen?\nAnzahl: ");
			try {
				spieldauer = sc.nextInt();
				if (spieldauer > 0 && spieldauer < 51) {
					return spieldauer;
				} else {
					System.out.println("Bitte geben Sie eine Ganzzahl zwischen 1 und 50 ein.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Bitte geben Sie eine Ganzzahl zwischen 1 und 50 ein.");
				sc.next();
			}
		}
	}
	
	public static void spielzugAusführen(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		Collection<Roboter> spieler = heimmannschaft.spieler.values();
	}

	public static void stelleMannschaftenAuf(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		heimmannschaft.aufstellen();
		gastmannschaft.aufstellen();		
	}
	
}
