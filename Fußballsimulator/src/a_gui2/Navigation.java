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
		Ball ball = Ball.getInstance(Spielfeld.spielfeldBreite/2+1, Spielfeld.spielfeldLaenge/2+1);
		Tor[] tore = {new Tor( Spielfeld.spielfeldBreite/2, 0), new Tor(Spielfeld.spielfeldBreite/2, Spielfeld.spielfeldLaenge-1)};
		Collection<Roboter> spieler = heimmannschaft.spieler.values();
		menuInteraktion(tore, ball, spieler, heimmannschaft, gastmannschaft);
		
	}
 
	private static String menuText = "0. Spiel laden\n1. Mannschaften anlegen\n2. Spiel starten\n3. Spielrunde anzeigen\n4. Spielstand anzeigen\n5. Spiel beenden";
 
	private static void menu(Tor[] tore, Ball ball, Collection<Roboter> spieler, Mannschaft heimmannschaft, Mannschaft gastmannschaft, int menuOption) {
		switch (menuOption) {
		case 1:
			Mannschaft.mannschaftAnlegen(tore, heimmannschaft, gastmannschaft);
			break;
		case 2:
			LogikHelper.ballbesitzSetzen(heimmannschaft, gastmannschaft);
			RoboterHelper.spielzugAusführen(heimmannschaft, gastmannschaft);
			
			break;
		case 3:
			System.out.println("Spielrunde anzeigen: ");
			Spielfeld.spielfeldMalen(tore, ball, heimmannschaft, gastmannschaft);
			Spielfeld.spielfeldAnzeigen();
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
		// Quelle: https://www.asciiart.eu/text-to-ascii-art
		System.out.println(" _____       ___ _           _ _     _                 _       _             \r\n"
				+ "|  ___|   _ / _ \\ |__   __ _| | |___(_)_ __ ___  _   _| | __ _| |_ ___  _ __ \r\n"
				+ "| |_ | | | | |/ / '_ \\ / _` | | / __| | '_ ` _ \\| | | | |/ _` | __/ _ \\| '__|\r\n"
				+ "|  _|| |_| | |\\ \\ |_) | (_| | | \\__ \\ | | | | | | |_| | | (_| | || (_) | |   \r\n"
				+ "|_|   \\__,_| ||_/_.__/ \\__,_|_|_|___/_|_| |_| |_|\\__,_|_|\\__,_|\\__\\___/|_|   \r\n"
				+ "           |_|                                                               ");
	}
 
	private static void menuAnzeigen() {
		System.out.println("\n" + menuText);
		System.out.println("Bitte geben Sie eine Ganzzahl ein, um im Menu zu navigieren.");
	}
 
	public static void menuInteraktion(Tor[] tore, Ball ball, Collection<Roboter> spieler, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		titelAnzeigen();
		
		while (true) {
			menuAnzeigen();
			menu(tore, ball, spieler, heimmannschaft, gastmannschaft, LogikHelper.menuEingabe(5));
		}
	}
 
	public static void zeigeSpielstand(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("\nMannschaft | Tore | Ballbesitz");
		System.out.println(heimmannschaft.name + " | " + heimmannschaft.tore + " | " + heimmannschaft.ballBesitz);
		System.out.println(gastmannschaft.name + " | " + gastmannschaft.tore + " | " + gastmannschaft.ballBesitz);
		System.out.println();
	}
}