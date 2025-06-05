package b_entitaeten;

public class Verteidiger extends Roboter {

	/**
	 * Erzeugt eine neue Instanz der Klasse Verteidiger.
	 * 
	 * @param name
	 */
	public Verteidiger(String name) {
		this.setName(name);
		this.faktorPass = 0.95;
		this.faktorSchuss = 0.8;
		this.setId(Roboter.anzahlRoboter);
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setGeschwindigkeit(1.5);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}
}
