package b_entitaeten;

public class Mittelfeldspieler extends Roboter {

	public Mittelfeldspieler(String name) {
		this.setName(name);
		this.faktorPass = 0.5;
		RoboterHelper.erhoeheAnzahlRoboter();
        this.setId(Roboter.anzahlRoboter); 
        this.setGeschwindigkeit(RoboterHelper.praeziPassGenM());
        this.setPraezisionPass(RoboterHelper.praeziPassGenM());
        this.setPraezisionSchuss(RoboterHelper.praeziPassGenM());
    }
        
        
        public void passeSpielen() {
            if (this.getEnergie() > 0) {
                System.out.println(this.getName() + " spielt einen Pass mit Präzision " + this.getPraezisionPass());
                this.setEnergie(this.getEnergie() - 5); // Energieverbrauch simulieren
            } else {
                System.out.println(this.getName() + " hat keine Energie mehr und kann keinen Pass spielen.");
            }
        }
        
        public String toString() {
            return "Mittelfeldspieler: " + this.getName() +
                   ", ID: " + this.getId() +
                   ", Geschwindigkeit: " + this.getGeschwindigkeit() +
                   ", Präzision Pass: " + this.getPraezisionPass() +
                   ", Präzision Schuss: " + this.getPraezisionSchuss() +
                   ", Energie: " + this.getEnergie();
        }
    }
        

