package b_entitaeten;

public class Stuermer extends Roboter {

	public Stuermer(String name) {
		this.setName(name);
		RoboterHelper.erhoeheAnzahlRoboter();
        this.setId(Roboter.anzahlRoboter); 
        this.setGeschwindigkeit(RoboterHelper.praeziPassGenM());
        this.setPraezisionPass(RoboterHelper.praeziPassGenM());
        this.setPraezisionSchuss(RoboterHelper.praeziPassGenM());
    }
}
