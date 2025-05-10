package b_entitaeten;

import java.util.Random;

public abstract class Roboter {
	private String name;
	private int id;
	private double geschwindigkeit;
	private int energie;
	private boolean hatBallBesitz;
	private double praezisionPass;
	private double praezisionSchuss;
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
    public double getPraezisionPass(){
    	return this.praezisionPass;
    }
    public double getPraezisionSchuss(){
    	return this.praezisionSchuss;
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
    public void setPraezisionPass(double praezisionPass) {
    	this.praezisionPass = praezisionPass;
    }
    public void setPraezisionSchuss(double praezisionSchuss) {
    	this.praezisionSchuss = praezisionSchuss;
    }

    public void laufen(int xSchritte, int ySchritte) {
    	this.x = this.x + xSchritte;
    	this.y = this.y + ySchritte;
    }
    public void passen(Roboter roboter) {
    	if(this.hatBallBesitz == true) {
    		
    		Random random = new Random();
        	double zufallswert = random.nextDouble();
        
    		if(this.praezisionPass >= zufallswert) {
        		this.hatBallBesitz = false;
        		roboter.hatBallBesitz = true;
        	}else {
        		this.hatBallBesitz = false;
        		System.out.println("Fehlpass"); // Eine möglichkeit
        	}
    	}else {
    		System.out.println("Der Spieler hat den ball nicht");
    	}
    }
    
    public void schiessen() {
    
    }
    public boolean blocken() {return true;}
    public void energieAufladen() {}
    public void ausfallen() {
    
    }
}
