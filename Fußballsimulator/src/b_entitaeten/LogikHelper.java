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
					System.out.println("Bitte geben Sie eine Ganzzahl zwischen 0 und " + maxMenuOption + " an.");
				}
			} catch (InputMismatchException ime) {
				// Der Scanner entfernt bei Auftreten der Exception nicht den gültigen Wert,
				// sondern liest diesen erneut ein, wodurch eine Endlosschleife entsteht.
				sc.next();
				System.out.println("Bitte geben Sie eine Ganzzahl zwischen 0 und " + maxMenuOption + " an.");
			}
		}
		return eingabe;
	}

	public static void ballbesitzSetzen(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		if (heimmannschaft.spieler.isEmpty() && gastmannschaft.spieler.isEmpty()) {
			System.out.println("\nBitte legen Sie zunächst beide Mannschaften an.");
		} else {
			System.out.println("\nMannschaft mit initalem Ballbesitz:");
			System.out.println("0.Heimmannschaft\n1.Gastmannschaft\n2.Zurück");
			switch (LogikHelper.menuEingabe(2)) {
			case 0:
				heimmannschaft.spieler.get("Stürmer").setBallBesitz(true);
				break;
			case 1:
				gastmannschaft.spieler.get("Stürmer").setBallBesitz(true);
				break;
			case 2:
				break;
			}
		}

	}

	public static void siegerAnzeigen(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		// prüfen ob spiel gestartet
		if (heimmannschaft.tore > gastmannschaft.tore) {
			System.out.print(heimmannschaft.name + " hat die Fußball-Partie gegen " + gastmannschaft.name
					+ " mit einem Sieg (" + heimmannschaft.tore + ":" + gastmannschaft.tore + ") beendet.");
		} else if (heimmannschaft.tore < gastmannschaft.tore) {
			System.out.print(gastmannschaft.name + " hat die Fußball-Partie gegen " + heimmannschaft.name
					+ " mit einer Niederlage (" + gastmannschaft.tore + ":" + heimmannschaft.tore + ") beendet.");
		} else {
			System.out.println(heimmannschaft.name + " hat die Fußball-Partie gegen " + gastmannschaft.name
					+ " mit einem Unentschieden (" + heimmannschaft.tore + ":" + gastmannschaft.tore + ") beendet.");
		}
	}
}
