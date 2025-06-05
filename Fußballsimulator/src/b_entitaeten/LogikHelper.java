package b_entitaeten;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import c_datenhaltung.Datenhaltung;

public class LogikHelper {

	/**
	 *  Liest eine Ganzzahl-Eingabe vom Nutzer ein, die zwischen 0 und einer Obergrenze liegt.
	 * @param maxMenuOption
	 * @return Nutzereingabe
	 */
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
	 * * Liest eine Ganzzahl-Eingabe vom Nutzer ein.
	 * @param minMenuOption
	 * @param maxMenuOption
	 * @return Nutzereingabe
	 */
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
	 * Durch diese Methode wird eine gastmanschaft und eine Heimmanschaft angelegt.
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

	/**
	 * Fragt vom Nutzer ab, welches Team mit dem Ballbesitz starten soll.
	 * @param ball
	 * @param heimmannschaft
	 * @param gastmannschaft
	 */
	public static void ballbesitzSetzen(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("\nWelche Mannschaft darf die Partie mit Ballbesitz beginnen?");
		System.out.println("0. " + heimmannschaft.getName() + "\n1. " + gastmannschaft.getName());
		switch (LogikHelper.menuEingabe(2)) {
		case 0:
			heimmannschaft.getSpieler().get("Stürmer").setBallBesitz(true);
			heimmannschaft.pruefeBallbesitz(ball);
			break;
		case 1:
			gastmannschaft.getSpieler().get("Stürmer").setBallBesitz(true);
			heimmannschaft.pruefeBallbesitz(ball);
			break;
		}
	}
	
	/**
	 * Gibt das Spielergebnis aus.
	 * @param heimmannschaft
	 * @param gastmannschaft
	 */
	public static void siegerAnzeigen(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		if (heimmannschaft.getTore() > gastmannschaft.getTore()) {
			System.out.print("\n" + heimmannschaft.getName() + " hat die Fußball-Partie gegen " + gastmannschaft.getName()
					+ " mit einem Sieg (" + heimmannschaft.getTore() + ":" + gastmannschaft.getTore() + ") beendet.");
		} else if (heimmannschaft.getTore() < gastmannschaft.getTore()) {
			System.out.print("\n" + gastmannschaft.getName() + " hat die Fußball-Partie gegen " + heimmannschaft.getName()
					+ " mit einer Niederlage (" + gastmannschaft.getTore() + ":" + heimmannschaft.getTore() + ") beendet.");
		} else {
			System.out.println("\n" + heimmannschaft.getName() + " hat die Fußball-Partie gegen " + gastmannschaft.getName()
					+ " mit einem Unentschieden (" + heimmannschaft.getTore() + ":" + gastmannschaft.getTore() + ") beendet.");
		}
	}

	/**
	 * Legt die initialen Positionen der Spieler beider Teams auf dem Spielfeld fest.
	 * @param ball
	 * @param heimmannschaft
	 * @param gastmannschaft
	 * @return true, wenn die Initialisierung erfolgreich war
	 */
	public static boolean initialePositionenSetzen(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("\nFür welche Mannschaft möchten Sie zuerst die intiale Aufstellung festlegen?");
		System.out.println("0. " + heimmannschaft.getName() + "\n1. " + gastmannschaft.getName());
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

	/**
	 * Speichert die aktuellen Objekte des Spiels.
	 * @param entitaeten
	 */
	public static void speichern(ArrayList<Object> entitaeten) {
		Datenhaltung.schreibeInDatei(entitaeten);
	}

	/**
	 * Fragt den Nutzer, wie viele Runden das Spiel dauern soll.
	 * @return
	 */
	public static ArrayList<Object> laden() {
		try {
			return Datenhaltung.leseAusDatei();
		} catch (ClassNotFoundException e) {
			System.out.println("\nDas Spiel konnte nicht geladen werden.\nBitte beginnen Sie ein ein neues Spiel.");
		}
		return null;
	}

	/**
	 * Fragt den Nutzer, wie viele Runden das Spiel dauern soll
	 * @param ball
	 */
	public static void abfragenSpieldauer(Ball ball) {
		System.out.println("\nWie viele Runden möchten Sie spielen?");
		ball.spieldauer = menuEingabe(2, 50);
	}

	/**
	 * Gibt in der Konsole aus, welches Team aktuell Ballbesitz hat.
	 * @param ball
	 * @param heimmannschaft
	 * @param gastmannschaft
	 */
	public static void aktualisiereBallbesitz(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("\n" + heimmannschaft.druckeBallbesitz(ball));
		System.out.println(gastmannschaft.druckeBallbesitz(ball));
	}

	/**
	 * Positioniert alle Spieler beider Mannschaften gemäß ihrer festgelegten Startkoordinaten.
	 * @param heimmannschaft
	 * @param gastmannschaft
	 */
	public static void stelleMannschaftenAuf(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		heimmannschaft.aufstellen();
		gastmannschaft.aufstellen();
	}

}
