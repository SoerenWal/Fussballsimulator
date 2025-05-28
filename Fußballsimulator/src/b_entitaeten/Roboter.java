package b_entitaeten;

import java.io.Serializable;

public abstract class Roboter implements Serializable {

	public static int anzahlRoboter = 0;

	private String name;
	private int id;
	private double geschwindigkeit;
	private int energie = 20;
	private boolean hatBallBesitz = false;
	private double praezisionPass;
	private double praezisionSchuss;
	private int initialX;
	private int initialY;
	private int x;
	private int y;
	protected double faktorPass;
	protected double faktorSchuss;

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

	public double getPraezisionPass() {
		return this.praezisionPass;
	}

	public double getPraezisionSchuss() {
		return this.praezisionSchuss;
	}
	
	public int getInitialX() {
		return this.initialX;
	}

	public int getInitialY() {
		return this.initialY;
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
		this.y = y;
	}
	
	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}
	
	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}

	public void setPraezisionPass(double praezisionPass) {
		this.praezisionPass = praezisionPass;
	}

	public void setPraezisionSchuss(double praezisionSchuss) {
		this.praezisionSchuss = praezisionSchuss;
	}

	public void laufen(int xSchritte, int ySchritte) {
		verbraucheEnergie(1);
		this.x = this.x + xSchritte;
		this.y = this.y + ySchritte;
	}
	
	public int verbraucheEnergie(int energiekosten) {
		if(this.energie >= energiekosten) {
			this.energie -= energiekosten;
			return 1;
		} else if(energie < energiekosten && energie != 0) {
			int energieDif = energiekosten - this.energie;
			this.energie = 0;
			return energieDif;
			
		}
		return 1;
	}

	public void passen(Roboter roboter) {
		if (this.hatBallBesitz && this.energie != 0) {
			if ((this.praezisionPass / verbraucheEnergie(5)) >= RoboterHelper.randomZahl()) {
				this.hatBallBesitz = false;
				roboter.hatBallBesitz = true;
				System.out.println("Erfolgreicher Pass");
			} else {
				this.hatBallBesitz = false;
				System.out.println("Fehlpass");
				}
		} else {
			System.out.println("Der Spieler hat den Ball nicht");
		}
	}

	public void schiessen() {
		// prüfeDistanz() if this.getX mit Linie
		if(this.x >= 15 || this.x <= 65) {
			if((this.praezisionSchuss / verbraucheEnergie(8)) >= RoboterHelper.randomZahl()) {
				
			}
		}
		
	}

	public boolean blocken() {
		return true;
	}

	public void energieAufladen() {
		this.energie = Math.min(this.energie + 2, 20);
	}

	public void ausfallen() {
		
	}

	public String toString() {
		return this.name;
	}
}
