package b_entitaeten;

import java.util.Random;
import java.util.Scanner;

public class RoboterHelper {
	/**
	 *  Die Methode generiert die Präzision für den Pass und Schuss.
	 *  
	 * @param faktor
	 * @return 
	 */
	public static double praeziRechner(double faktor) {
		return 0.7 + Math.random() * (faktor - 0.7);
	}

	/**
	 * Diese Funktion gibt einen zufälligen Wert zwischen 0.0-1.0 zurück
	 * 
	 * @return Zufällige Zahl zwischen 0 und 1
	 */
	public static double randomZahl() {
		Random random = new Random();
		return random.nextDouble();
	}

	/**
	 * Eine Methode, die einen Zähler, für die Anzahl der Roboter-Instanzen, erhöht.
	 */
	public static void erhoeheAnzahlRoboter() {
		Roboter.anzahlRoboter += 1;
	}

	/**
	 * Eine Methode die Namen nach validen Namen fragt.
	 * @param Eine Abfrage eines Namens
	 * @return Einen validen Namen
	 */
	public static String erfrageNamen(String abfrage) {
		Scanner sc = new Scanner(System.in);
		System.out.print(abfrage);
		String name = sc.nextLine().trim().toLowerCase();
		char anfangsbuchstabe = name.charAt(0);
		name = Character.toUpperCase(anfangsbuchstabe) + name.substring(1);
		if (validiereNamen(name) == true) {
			return name;
		} else {
			return erfrageNamen(abfrage);
		}
	}
	
	/**
	 * Eine Methode die Namen validiert.
	 * @param name
	 * @return True, sofern der Name nicht leer ist und keine Zahlen besitzt. 
	 */
	public static boolean validiereNamen(String name) {
		if(name.isBlank()) {
			System.out.println("Geben Sie bitte einen Namen ein.");
			return false;
		}
		for(int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if(!Character.isAlphabetic(c)) {
				System.out.println("Bitte nutzen Sie für die Namen ausschließlich Buchstaben.");
				return false;
			}
		}
		return true;
	}
}
