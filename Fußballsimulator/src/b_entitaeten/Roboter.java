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
	private int initialZeile;
	private int initialSpalte;
	private int zeile;
	private int spalte;
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

	public int getZeile() {
		return this.zeile;
	}

	public int getSpalte() {
		return this.spalte;
	}

	public double getPraezisionPass() {
		return this.praezisionPass;
	}

	public double getPraezisionSchuss() {
		return this.praezisionSchuss;
	}
	
	public int getInitialZeile() {
		return this.initialZeile;
	}

	public int getInitialSpalte() {
		return this.initialSpalte;
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

	public void setZeile(int zeile) {
		this.zeile = zeile;
	}

	public void setSpalte(int spalte) {
		this.spalte = spalte;
	}
	
	public void setInitialZeile(int initialZeile) {
		this.initialZeile = initialZeile;
	}
	
	public void setInitialSpalte(int initialSpalte) {
		this.initialSpalte = initialSpalte;
	}

	public void setPraezisionPass(double praezisionPass) {
		this.praezisionPass = praezisionPass;
	}

	public void setPraezisionSchuss(double praezisionSchuss) {
		this.praezisionSchuss = praezisionSchuss;
	}

	public void laufen(int xSchritte, int ySchritte) {
		verbraucheEnergie(1);
		this.zeile = this.zeile + xSchritte;
		this.spalte = this.spalte + ySchritte;
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
		if(this.zeile >= 15 || this.zeile <= 65) {
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
