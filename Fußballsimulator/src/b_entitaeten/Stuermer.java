package b_entitaeten;

public class Stuermer extends Roboter {

	public Stuermer(String name, int x, int y) {
		this.setX(x);
		this.setY(y);
		this.setName(name);
		this.faktorPass = 0.85;
		this.faktorSchuss = 1;
		this.setId(Roboter.anzahlRoboter);
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setGeschwindigkeit(0);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}

	public Stuermer(String name) {
		this.setName(name);
		this.faktorPass = 0.85;
		this.faktorSchuss = 1;
		this.setId(Roboter.anzahlRoboter);
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setGeschwindigkeit(0);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}
}
