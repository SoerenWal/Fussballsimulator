package b_entitaeten;

public class Verteidiger extends Roboter {
	
	public Verteidiger(String name) {
		this.setName(name);
		RoboterHelper.erhoeheAnzahlRoboter();
        this.setId(Roboter.anzahlRoboter); 
        this.setGeschwindigkeit(RoboterHelper.praeziPassGenM());
        this.setPraezisionPass(RoboterHelper.praeziPassGenM());
        this.setPraezisionSchuss(RoboterHelper.praeziPassGenM());
    }
}
