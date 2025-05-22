package a_gui2;
 
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
 
import b_entitaeten.Ball;
import b_entitaeten.Mannschaft;
import b_entitaeten.Roboter;
import b_entitaeten.Spielfeld;
 
public class Navigation {
	
	public static void main(String[] args) {
		Mannschaft heimmannschaft = new Mannschaft("Frankfurt", new HashMap<String, Roboter>());
		Mannschaft gastmannschaft = new Mannschaft("Darmstadt", new HashMap<String, Roboter>());
		Ball ball = Ball.getInstance(18, 41);
		Collection<Roboter> spielerArrayList = heimmannschaft.spieler.values();
		menuInteraktion(heimmannschaft, gastmannschaft, ball, spielerArrayList);
		
	}
 
	private static String menuText = "0. Spiel laden\n1. Mannschaften anlegen\n2. Spiel starten\n3. Spielrunde anzeigen\n4. Spielstand anzeigen\n5. Spiel beenden";
 
	private static void menu(Mannschaft heimmannschaft, Mannschaft gastmannschaft, Ball ball, Collection<Roboter> spielerArrayList, int menuOption) {
		switch (menuOption) {
		case 1:
			System.out.println("Heimmannschaft anlegen: ");
			heimmannschaft.mannschaftAnlegen();
			System.out.println("Gastmannschaft anlegen: ");
			gastmannschaft.mannschaftAnlegen();
			System.out.println(heimmannschaft.spieler.toString() + "\n" + gastmannschaft.spieler.toString());
			break;
		case 2:
			System.out.println("Spiel starten: ");
			ball.setX(ball.getX());
			ball.setY(ball.getY()-5);
			break;
		case 3:
			System.out.println("Spielrunde anzeigen: ");
			Spielfeld.feldVorbereiten(ball, spielerArrayList);
			Spielfeld.feldAnzeigen();
			break;
		case 4:
			System.out.println("Spielstand:");
			zeigeSpielstand(heimmannschaft, gastmannschaft);
			heimmannschaft.zeigeEnergieBericht();
			gastmannschaft.zeigeEnergieBericht();  
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
		// https://www.asciiart.eu/text-to-ascii-art
		System.out.println(" _____       ___ _           _ _     _                 _       _             \r\n"
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
			if (eingabe > 0 && eingabe < 6) {
				break;
			} else {
				System.out.println("Bitte geben Sie eine Ganzzahl zwischen 1 und 5 an.");
			}
		}
		return eingabe;
	}
 
	public static void menuInteraktion(Mannschaft heimmannschaft, Mannschaft gastmannschaft, Ball ball, Collection<Roboter> spielerArrayList) {
		titelAnzeigen();
		menuAnzeigen();
		while (true) {
			menu(heimmannschaft, gastmannschaft, ball, spielerArrayList, menuEingabe());
		}
	}
 
	public static void zeigeSpielstand(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("\nMannschaft | Tore | Ballbesitz");
		System.out.println(heimmannschaft.name + " | " + heimmannschaft.tore + " | " + heimmannschaft.ballBesitz);
		System.out.println(gastmannschaft.name + " | " + gastmannschaft.tore + " | " + gastmannschaft.ballBesitz);
		System.out.println();
	}
	
}