package b_entitaeten;

public class Verteidiger extends Roboter {

	public Verteidiger(String name, int x, int y) {
		this.setX(x);
		this.setY(y);
		this.setName(name);
		this.faktorPass = 0.95;
		this.faktorSchuss = 0.8;
		this.setId(Roboter.anzahlRoboter);
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setGeschwindigkeit(0);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}
	
	public Verteidiger(String name) {
		this.setName(name);
		this.faktorPass = 0.95;
		this.faktorSchuss = 0.8;
		this.setId(Roboter.anzahlRoboter);
		RoboterHelper.erhoeheAnzahlRoboter();
		this.setGeschwindigkeit(0);
		this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
		this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
	}
}
