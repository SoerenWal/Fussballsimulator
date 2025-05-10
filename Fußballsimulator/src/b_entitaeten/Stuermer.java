package b_entitaeten;

public class Stuermer extends Roboter {

	public Stuermer(String name, int id, double geschwindigkeit, double praezision, int energie) {
        this.setName(name);
        this.setId(id); 
        this.setGeschwindigkeit(geschwindigkeit);
        this.setPraezisionPass(praezision);
        this.setPraezisionSchuss(praezision);
        this.setEnergie(energie);
    }

}
