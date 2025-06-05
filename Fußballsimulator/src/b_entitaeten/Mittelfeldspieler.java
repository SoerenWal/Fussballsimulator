package b_entitaeten;

public class Mittelfeldspieler extends Roboter {


	/**
	 * Erzeugt eine neue Instanz der Klasse Mittelfeldspieler
	 * @param name
	 */
	public Mittelfeldspieler(String name) {
		this.setName(name);
		this.faktorPass = 0.95;
		this.faktorSchuss = 0.85;
		this.setId(Roboter.anzahlRoboter);
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setGeschwindigkeit(1);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}

}
