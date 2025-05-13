package b_entitaeten;
import java.util.Random;
public class RoboterHelper {
	/**
	 * Die Mathode generiert die Präzision für den Pass und Schuss
	 * @param faktor
	 * @return
	 */
	
	public static double praeziRechner(double faktor) {
		return 0.7 + Math.random() * (faktor - 0.7);
	}
	
	/**
	 * Diese Funktion gibt einen zufälligen Wert zwischen 0.0-1.0 zurück
	 * @return
	 */
	public static double randomZahl() {
		Random random = new Random();
		return random.nextDouble();
	}
	
	/**
	 * Eine Methode, die den Zähler für die Anzahl der Roboter-Instanzen erhöht
	 */
	public static void erhoeheAnzahlRoboter() {
		Roboter.anzahlRoboter += 1;
	}
}


