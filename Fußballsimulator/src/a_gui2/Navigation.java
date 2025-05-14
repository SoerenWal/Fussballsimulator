package a_gui2;

import java.util.Scanner;

public class Navigation {

	static Scanner sc = new Scanner(System.in);
	private static String menuText = "1. Mannschaft anlegen\n2. Spiel starten\n3. Spielrunde anzeigen\n4. Spielstand anzeigen\n5. Spiel beenden";

	private static void menu(int menuOption) {
		switch (menuOption) {
		case 1:
			System.out.println("Mannschaft anlegen: ");
			// mannschaftAnlegen()
			break;
		case 2:
			System.out.println("Spiel starten: ");
			// spielStarten();
			break;
		case 3:
			System.out.println("Spielrunde anzeigen: ");
			// spielrundeAnzeigen();
			break;
		case 4:
			System.out.println("Spielstand anzeigen: ");
			// spielstandAnzeigen();
			break;
		case 5:
			System.out.println("Spiel beendet.");
			System.exit(0);
			break;
		default:
			break;
		}
	}
	
	private static void titelAnzeigen() {
		System.out.println(
				" _____       ___ _           _ _     _                 _       _             \r\n"
				+ "|  ___|   _ / _ \\ |__   __ _| | |___(_)_ __ ___  _   _| | __ _| |_ ___  _ __ \r\n"
				+ "| |_ | | | | |/ / '_ \\ / _` | | / __| | '_ ` _ \\| | | | |/ _` | __/ _ \\| '__|\r\n"
				+ "|  _|| |_| | |\\ \\ |_) | (_| | | \\__ \\ | | | | | | |_| | | (_| | || (_) | |   \r\n"
				+ "|_|   \\__,_| ||_/_.__/ \\__,_|_|_|___/_|_| |_| |_|\\__,_|_|\\__,_|\\__\\___/|_|   \r\n"
				+ "           |_|                                                               ");
	}

	private static void menuAnzeigen() {
		System.out.println(menuText);
		System.out.println("Bitte geben Sie eine Ganzzahl ein, um im Menu zu navigieren.");
	}

	private static int menuEingabe() {
		System.out.print("Eingabe: ");
		int eingabe = sc.nextInt();
		return eingabe;
	}

	public static void menuInteraktion() {
		titelAnzeigen();
		menuAnzeigen();
		do {
			menu(menuEingabe());
		} while (true);
	}
	
}
