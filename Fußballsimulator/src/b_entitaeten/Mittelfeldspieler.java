package b_entitaeten;

public class Mittelfeldspieler extends Roboter {

	public Mittelfeldspieler(String name, int zeile, int spalte) {
		this.setZeile(zeile);
		this.setSpalte(spalte);
		this.setName(name);
		this.faktorPass = 0.95;
		this.faktorSchuss = 0.85;
		this.setId(Roboter.anzahlRoboter);
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setGeschwindigkeit(0);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}
	
	public Mittelfeldspieler(String name) {
		this.setName(name);
		this.faktorPass = 0.95;
		this.faktorSchuss = 0.85;
		this.setId(Roboter.anzahlRoboter);
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setGeschwindigkeit(0);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}

}
