package b_entitaeten;

public class Mittelfeldspieler extends Roboter {

	public Mittelfeldspieler(String name) {
		this.setName(name);
		this.faktorPass = 0.95;
		this.faktorSchuss = 0.85;
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setId(Roboter.anzahlRoboter);
		this.setGeschwindigkeit(0);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}

}

/*
 * public String toString() { return "Mittelfeldspieler: " + this.getName() +
 * ", ID: " + this.getId() + ", Geschwindigkeit: " + this.getGeschwindigkeit() +
 * ", Präzision Pass: " + this.getPraezisionPass() + ", Präzision Schuss: " +
 * this.getPraezisionSchuss() + ", Energie: " + this.getEnergie(); }
 */
