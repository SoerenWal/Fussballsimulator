package b_entitaeten;

public class Torwart extends Roboter {
	
	public Torwart(String name) {
		this.setName(name);
		this.faktorPass = 1;
		this.faktorSchuss = 0.75;
		RoboterHelper.erhoeheAnzahlRoboter();
        this.setId(Roboter.anzahlRoboter); 
        this.setGeschwindigkeit(0);
        this.setPraezisionPass(RoboterHelper.praeziRechner(this.faktorPass));
        this.setPraezisionSchuss(RoboterHelper.praeziRechner(this.faktorSchuss));
    }
}
