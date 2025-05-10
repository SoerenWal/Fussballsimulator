package b_entitaeten;

public class Mittelfeldspieler extends Roboter {

    public Mittelfeldspieler(String name, int id, double geschwindigkeit, double praezision, int energie) {
        this.setName(name);
        this.setId(id); 
        this.setGeschwindigkeit(geschwindigkeit);
        this.setPraezision(praezision);
        this.setEnergie(energie);
    }
}