package b_entitaeten;

public class RoboterHelper {

	public static double praeziPassGenM() {
		return 0.7 + Math.random() * 0.3;
	}
	
	public static double praeziSchussGenM() { // damit der Bug weg ist ;)
		return 0.0;
	}
	
	/**
	 * Eine Methode, die den Zähler für die Anzahl der Roboter-Instanzen erhöht
	 */
	public static void erhoeheAnzahlRoboter() {
		Roboter.anzahlRoboter += 1;
	}
}


