package b_entitaeten;

import java.util.InputMismatchException;
import java.util.Scanner;

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

	public static boolean ballbesitzSetzen(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		if (heimmannschaft.spieler.isEmpty() && gastmannschaft.spieler.isEmpty()) {
			System.out.println("\nBitte legen Sie zunächst beide Mannschaften an.");
			return false;
		} else if (!heimmannschaft.spieler.isEmpty() && !gastmannschaft.spieler.isEmpty()) {
			System.out.println("\nWelche Mannschaft darf die Partie mit Ballbesitz beginnen?");
			System.out.println("0." + heimmannschaft.name + "\n1." + gastmannschaft.name + "\n2.Zurück");
			switch (LogikHelper.menuEingabe(2)) {
			case 0:
				heimmannschaft.spieler.get("Stürmer").setBallBesitz(true);
				return true;
			case 1:
				gastmannschaft.spieler.get("Stürmer").setBallBesitz(true);
				return true;
			case 2:
				return false;
			}
		}
		System.out.println("\nBitte legen Sie zunächst beide Mannschaften an.");
		return false;
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
}
