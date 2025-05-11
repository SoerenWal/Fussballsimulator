package b_entitaeten;

public class Mittelfeldspieler extends Roboter {

    public Mittelfeldspieler(String name, int id, 
    		double geschwindigkeit, double praezisionPass, double prazisionSchuss, int energie) {
    	
        this.setName(name);
        this.setId(id); 
        this.setGeschwindigkeit(geschwindigkeit);
        this.setPraezisionPass(praezisionPass);
        this.setPraezisionSchuss(prazisionSchuss);
        this.setEnergie(energie);
        
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
        

