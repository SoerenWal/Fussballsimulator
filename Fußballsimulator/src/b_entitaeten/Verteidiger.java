package b_entitaeten;

public class Verteidiger extends Roboter {
	
	public Verteidiger(String name) {
		this.setName(name);
		this.faktorPass = 0.95;
		this.faktorSchuss = 0.8;
		RoboterHelper.erhoeheAnzahlRoboter();
        this.setId(Roboter.anzahlRoboter); 
        this.setGeschwindigkeit(0);
        this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
        this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
    }
}
