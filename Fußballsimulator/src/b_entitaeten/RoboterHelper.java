package b_entitaeten;

public class RoboterHelper {
	
	public static double praeziRechner(double faktor) {
		return 0.7 + Math.random() * (faktor - 0.7);
	}
	
	/**
	 * Eine Methode, die den Zähler für die Anzahl der Roboter-Instanzen erhöht
	 */
	public static void erhoeheAnzahlRoboter() {
		Roboter.anzahlRoboter += 1;
	}
}


