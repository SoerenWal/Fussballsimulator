package entitaeten;

public abstract class Roboter {
	private String name;
	private int id;
	private double geschwindigkeit;
	private int energie;
	private boolean hatBallBesitz;
	private int x;
	private int y;
    
	
    public String getName() {
    	return this.name;
    }
    public int getId() {
    	return this.id;
    	}
    public double getGeschwindigkeit() {
    	return this.geschwindigkeit;
    }
    public int getEnergie() {
    	return this.energie;
    }
    public boolean getHatBallBesitz() {
    	return this.hatBallBesitz;
    }
    public int getX() {
    	return this.x;
    }
    public int getY() {
    	return this.y;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    public void setId(int id) {
    	this.id = id;
    }
    public void setGeschwindigkeit(double geschwindigkeit) {
    	this.geschwindigkeit = geschwindigkeit;
    }
    public void setEnergie(int energie) {
    	this.energie = energie;
    }
    public void setBallBesitz(boolean ballBesitz) {
    	this.hatBallBesitz = ballBesitz;
    }
    public void setX(int x) {
    	this.x = x;
    }
    public void setY(int y) {
    	this.x = y;
    }

    public void laufen() {}
    public void passen(Roboter roboter) {}
    public void schiessen() {}
    public boolean blocken() {}
    public void energieAufladen() {}
    public void ausfallen() {
    
    }
}
