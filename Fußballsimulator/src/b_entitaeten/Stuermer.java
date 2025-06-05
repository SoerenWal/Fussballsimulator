package b_entitaeten;

public class Stuermer extends Roboter {

	/**
	 * Erzeugt eine neue Instanz der Klasse Stuermer
	 * 
	 * @param name
	 */
	public Stuermer(String name) {
		this.setName(name);
		this.faktorPass = 0.85;
		this.faktorSchuss = 1;
		this.setId(Roboter.anzahlRoboter);
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setGeschwindigkeit(0.5);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}
}
