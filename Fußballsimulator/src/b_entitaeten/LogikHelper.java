package b_entitaeten;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import c_datenhaltung.Datenhaltung;

public class LogikHelper {

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

	public static int menuEingabe(int minMenuOption, int maxMenuOption) {
		Scanner sc = new Scanner(System.in);
		int eingabe = 0;
		while (true) {
			System.out.print("\nEingabe: ");
			try {
				eingabe = sc.nextInt();
				if (eingabe >= minMenuOption && eingabe <= maxMenuOption) {
					break;
				} else {
					System.out.println("\nBitte geben Sie eine Ganzzahl zwischen " + minMenuOption + " und "
							+ maxMenuOption + " an.");
				}
			} catch (InputMismatchException ime) {
				// Der Scanner entfernt bei Auftreten der Exception nicht den gültigen Wert,
				// sondern liest diesen erneut ein, wodurch eine Endlosschleife entsteht.
				sc.next();
				System.out.println(
						"\nBitte geben Sie eine Ganzzahl zwischen " + minMenuOption + " und " + maxMenuOption + " an.");
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
		String[] heimmannschaft_praefix = { "S|Stürmer", "Ṁ|Mittelfeldspieler", "Ṃ|Mittelfeldspieler", "V|Verteidiger",
				"T|Torwart" };
		String[] gastmannschaft_praefix = { "s|Stürmer", "ṁ|Mittelfeldspieler", "ṃ|Mittelfeldspieler", "v|Verteidiger",
				"t|Torwart" };
		heimmannschaft.anlegen(heimmannschaft_praefix);
		gastmannschaft.anlegen(gastmannschaft_praefix);
	}

	public static void ballbesitzSetzen(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("\nWelche Mannschaft darf die Partie mit Ballbesitz beginnen?");
		System.out.println("0. " + heimmannschaft.name + "\n1. " + gastmannschaft.name);
		switch (LogikHelper.menuEingabe(2)) {
		case 0:
			heimmannschaft.spieler.get("Stürmer").setBallBesitz(true);
			heimmannschaft.pruefeBallbesitz(ball);
			break;
		case 1:
			gastmannschaft.spieler.get("Stürmer").setBallBesitz(true);
			heimmannschaft.pruefeBallbesitz(ball);
			break;
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
			System.out.println("\nDas Spiel konnte nicht geladen werden.\nBitte beginnen Sie ein ein neues Spiel.");
		}
		return null;
	}

	public static void abfragenSpieldauer(Ball ball) {
		System.out.println("\nWie viele Runden möchten Sie spielen?");
		ball.spieldauer = menuEingabe(2, 50);
	}

	public static void aktualisiereBallbesitz(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("\n" + heimmannschaft.pruefeBallbesitz(ball));
		System.out.println(gastmannschaft.pruefeBallbesitz(ball));
	}

	public static void stelleMannschaftenAuf(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		heimmannschaft.aufstellen();
		gastmannschaft.aufstellen();
	}

}
