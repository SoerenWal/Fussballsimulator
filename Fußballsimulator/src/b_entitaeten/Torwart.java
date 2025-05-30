package b_entitaeten;

public class Torwart extends Roboter {
	
	/**
	 * Erzeugt eine neue Instanz der Klasse Torwart
	 * @param name
	 */
	public Torwart(String name) {
		this.setName(name);
		this.faktorPass = 1;
		this.faktorSchuss = 0.75;
		this.setId(Roboter.anzahlRoboter);
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setGeschwindigkeit(0);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}
}
