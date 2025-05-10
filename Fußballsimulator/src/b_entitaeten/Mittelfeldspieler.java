package b_entitaeten;

public class Mittelfeldspieler extends Roboter {

    public Mittelfeldspieler(String name, int id, double geschwindigkeit, double praezisionPass, double prazisionSchuss, int energie) {
        this.setName(name);
        this.setId(id); 
        this.setGeschwindigkeit(geschwindigkeit);
        this.setPraezisionPass(praezisionPass);
        this.setPraezisionSchuss(prazisionSchuss);
        this.setEnergie(energie);
    }
}