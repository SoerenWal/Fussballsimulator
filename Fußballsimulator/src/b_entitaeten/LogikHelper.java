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
			} catch (InputMismatchException ime) {
				// Der Scanner entfernt bei Auftreten der Exception nicht den gültigen Wert, sondern liest diesen erneut ein, wodurch eine Endlosschleife entsteht.
				sc.next();
			}
			if (eingabe >= 0 && eingabe <= maxMenuOption) {
				break;
			} else {
				System.out.println("Bitte geben Sie eine Ganzzahl zwischen 0 und " + maxMenuOption + " an.");
			}
		}
		return eingabe;
	}
}
