package b_entitaeten;

import java.io.Serializable;
import java.util.Collection;
import java.util.Scanner;

public abstract class Roboter implements Serializable {

	/** Gesamtanzahl erstellter Roboter (statisch) */
	public static int anzahlRoboter = 0;

	private String name;
	private int id;
	private double geschwindigkeit;
	private int energie = 50;
	private boolean ballbesitz = false;
	private double praezisionPass;
	private double praezisionSchuss;
	private int initialZeile;
	private int initialSpalte;
	private int zeile;
	private int spalte;
	protected double faktorPass;
	protected double faktorSchuss;
	private boolean ausgefallen = false;

	/**
	 * Gibt den Namen des Roboters zurück.
	 * 
	 * @return Name des Roboters
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gibt die ID des Roboters zurück.
	 * 
	 * @return Roboter-ID
	 */

	public int getId() {
		return this.id;
	}

	/**
	 * Gibt die Bewegungsgeschwindigkeit zurück.
	 * 
	 * @return Geschwindigkeit des Roboters
	 */
	public double getGeschwindigkeit() {
		return this.geschwindigkeit;
	}

	/**
	 * Gibt die aktuelle Energie zurück.
	 * 
	 * @return Energie (0–20)
	 */
	public int getEnergie() {
		return this.energie;
	}

	/**
	 * Gibt zurück ob ein Spieler ausgefallen ist oder nicht.
	 * 
	 * @return boolean
	 */
	public boolean getAusgefallen() {
		return ausgefallen;
	}

	/**
	 * Prüft, ob der Roboter den Ball besitzt.
	 * 
	 * @return true, wenn Ballbesitz vorliegt
	 */

	public boolean getBallbesitz() {
		return this.ballbesitz;
	}

	/**
	 * Gibt die aktuelle Zeilenposition zurück.
	 * 
	 * @return Zeilenindex
	 */
	public int getZeile() {
		return this.zeile;
	}

	/**
	 * Gibt die aktuelle Spaltenposition zurück.
	 * 
	 * @return Spaltenindex
	 */
	public int getSpalte() {
		return this.spalte;
	}

	/**
	 * Gibt die Passpräzision zurück.
	 * 
	 * @return Wert zwischen 0 und 1
	 */
	public double getPraezisionPass() {
		return this.praezisionPass;
	}

	/**
	 * Gibt die Schusspräzision zurück.
	 * 
	 * @return Wert zwischen 0 und 1
	 */

	public double getPraezisionSchuss() {
		return this.praezisionSchuss;
	}

	/**
	 * Gibt die initiale Zeilenposition zurück.
	 * 
	 * @return Start-Zeile
	 */

	public int getInitialZeile() {
		return this.initialZeile;
	}

	/**
	 * Gibt die initiale Spaltenposition zurück.
	 * 
	 * @return Start-Spalte
	 */
	public int getInitialSpalte() {
		return this.initialSpalte;
	}

	/**
	 * Setzt den Namen des Roboters.
	 * 
	 * @param name
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setzt die ID.
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Setzt die Geschwindigkeit.
	 * 
	 * @param geschwindigkeit
	 */
	public void setGeschwindigkeit(double geschwindigkeit) {
		this.geschwindigkeit = geschwindigkeit;
	}

	/**
	 * Setzt die Energie des Roboters.
	 * 
	 * @param energie
	 */
	public void setEnergie(int energie) {
		this.energie = energie;
	}

	/**
	 * Setzt den Ballbesitzstatus.
	 * 
	 * @param ballbesitz true, wenn Ballbesitz
	 */
	public void setBallBesitz(boolean ballbesitz) {
		this.ballbesitz = ballbesitz;
	}

	/**
	 * Setzt die Zeilenposition.
	 * 
	 * @param zeile
	 */
	public void setZeile(int zeile) {
		this.zeile = zeile;
	}

	/**
	 * Setzt die Spaltenposition.
	 * 
	 * @param spalte
	 */

	public void setSpalte(int spalte) {
		this.spalte = spalte;
	}

	/**
	 * Setzt die Start-Zeile.
	 * 
	 * @param initialZeile
	 */

	public void setInitialZeile(int initialZeile) {
		this.initialZeile = initialZeile;
	}

	/**
	 * Setzt boolischen Wert ausgefallen.
	 * 
	 * @param ausgefallen
	 */
	public void setAusgefallen(boolean ausgefallen) {
		this.ausgefallen = ausgefallen;
	}

	/**
	 * Setzt die Start-Spalte.
	 * 
	 * @param initialSpalte Startwert
	 */
	public void setInitialSpalte(int initialSpalte) {
		this.initialSpalte = initialSpalte;
	}

	/**
	 * Setzt die Passpräzision.
	 * 
	 * @param praezisionPass
	 */

	public void setPraezisionPass(double praezisionPass) {
		this.praezisionPass = praezisionPass;
	}

	/**
	 * Setzt die Schusspräzision.
	 * 
	 * @param praezisionSchuss
	 */

	public void setPraezisionSchuss(double praezisionSchuss) {
		this.praezisionSchuss = praezisionSchuss;
	}

	/**
	 * Bewegt den Roboter relativ zur aktuellen Position.
	 * 
	 * @param zeile
	 * @param spalte
	 */

	public void laufen() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nWohin soll " + this.name + " laufen?");
		System.out.println("\nZeilen nach unten (X).\nZeilen nach oben (-X).");
		int zeile = LogikHelper.menuEingabe(-17, 17);
		System.out.println("\nSpalten nach rechts (X).\nSpalten nach links (-X).");
		int spalte = LogikHelper.menuEingabe(-81, 81);
		if (this.zeile + zeile > 0 && this.zeile + zeile < 17 && this.spalte + spalte > 0 && this.spalte + spalte < 81
				&& Spielfeld.istFeldFrei(this.zeile + zeile, this.spalte + spalte)) {
			verbraucheEnergie((int) (Math.abs(zeile + spalte) * this.geschwindigkeit));
			this.zeile = this.zeile + zeile;
			this.spalte = this.spalte + spalte;
		} else {
			System.out.println("\n" + this.name + " darf das Spielfeld nicht verlassen.");
			laufen();
		}
	}

	/**
	 * Verbraucht eine bestimmte Menge an Energie.
	 * 
	 * @param energiekosten gewünschte Energieeinheiten
	 * @return true bei ausreichender Energie, Differenz wenn unzureichend
	 */

	public int verbraucheEnergie(int energiekosten) {
		if (this.energie >= energiekosten) {
			this.energie -= energiekosten;
			return 1;
		} else if (energie < energiekosten && energie != 0) {
			int energieDif = energiekosten - this.energie;
			this.energie = 0;
			return energieDif;

		}
		return 1;
	}

	/**
	 * Führt einen Passversuch zu einem anderen Roboter aus.
	 * 
	 * @param roboter Zielspieler
	 */

	public void passen(Roboter roboter, Ball ball) {
		double abstand = Math.sqrt(
				Math.pow(this.getSpalte() - roboter.getSpalte(), 2) + Math.pow(this.zeile - roboter.getZeile(), 2));
		int radius = 15;
		if (radius <= abstand) {

			if (this.ballbesitz) {
				if (this.energie != 0) {
					if ((this.praezisionPass / verbraucheEnergie(5)) >= RoboterHelper.randomZahl()) {
						this.ballbesitz = false;
						roboter.ballbesitz = true;
						System.out.println("\nDer Pass von " + this.name + " war erfolgreich.");
					} else {
						this.ballbesitz = false;
						ball.setSpalte(
								this.spalte + (int) ((roboter.getSpalte() - this.spalte) * RoboterHelper.randomZahl()));
						ball.setZeile(
								this.zeile + (int) ((roboter.getZeile() - this.zeile) * RoboterHelper.randomZahl()));
						System.out.println(
								"\nDer Pass ist leider fehlgeschlagen und der Ball liegt nun frei auf dem Spielfeld.");
					}
				} else {
					System.out.println("\n" + this.name + " hat nicht genug Energie, um einen Pass durchzuführen.");
				}
			} else {
				System.out.println("\n" + this.name + " hat den Ball nicht.");
			}
		}else {
			System.out.println("\nDer Spieler " + roboter.name + " ist zu weit entfernt");
		}
	}

	/**
	 * Führt einen Schussversuch auf das Tor aus.
	 */

	public void schießen(Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		if (this.ballbesitz) {
			if (((this.spalte <= 16 && this.spalte >= 63) && (this.zeile >= 3 && this.zeile <= 14))) {
				if ((this.praezisionSchuss / verbraucheEnergie(8)) >= RoboterHelper.randomZahl()) {
					System.out.println("\n" + this.name + " hat ein Tor geschossen.");
					this.ballbesitz = false;
					gastmannschaft.getSpieler().get("Stürmer").ballbesitz = true;
					LogikHelper.stelleMannschaftenAuf(heimmannschaft, gastmannschaft);
				} else {
					System.out.println("\n" + this.name + " hat am Tor vorbeigeschossen.");
					gastmannschaft.getSpieler().get("Torwart").ballbesitz = true;
				}
			} else {
				System.out.println(
						"\n" + this.name + " steht außerhalb der Torreichweite, um einen Torschuss durchzuführen.");
			}
		} else {
			System.out.println("\n" + this.name + " besitzt den Ball nicht.");
		}
	}

	/**
	 * Führt einen Blockversuch aus.
	 * 
	 * @return true, wenn Block ausgelöst wird
	 */
	public boolean blocken(Ball ball, Roboter r) {
		verbraucheEnergie(4);
		if (0.75 > RoboterHelper.randomZahl()) {
			double abstand = Math
					.sqrt(Math.pow(this.getSpalte() - ball.getSpalte(), 2) + Math.pow(this.zeile - ball.getZeile(), 2));
			final double maxAbstand = 3;

			if (maxAbstand >= abstand) {
				this.ballbesitz = true;
				r.ballbesitz = false;
				System.out.println("\nDer Block von " + this.name + " war erfolgreich.\nDer Ballbesitz wechselte von "
						+ r.getName() + " zu " + this.name + ".");
				return true;
			} else {
				System.out.println(
						"\n" + this.name + " konnte " + r.getName() + " nicht blocken.\nDie Distanz war zu groß.");
				return false;
			}
		}
		System.out.println("\n" + this.name + " ist der Block von  misslungen.");
		return false;
	}

	/**
	 * Lädt die Energie des Roboters auf.
	 */

	public void energieAufladen() {
		this.energie = Math.min(this.energie + 20, 50);
	}

	/**
	 * Setzt den Roboter außer Gefecht.
	 */
	public void ausfallen() {
		if (this.getAusgefallen()) {
			this.energie++;
		}
		if (this.energie == 0) {
			this.setAusgefallen(true);
			System.out.println("\n" + this.name + " fällt für 2 Runden aus, da dieser keine Energie mehr hat.");
		}
		if (this.energie >= 2) {
			this.setAusgefallen(false);
		}
	}

	/**
	 * Gibt den Namen des Roboters als String zurück.
	 * 
	 * @return Name des Roboters
	 */

	public String toString() {
		return this.name;
	}

	/**
	 * Die Methode prüft ob sich der Ball in der Nähe des Spielers befindet und
	 * setzt den Ballbesitz auf true, wenn dies erfüllt ist.
	 *
	 * @return true, wenn der Ball sich im Radius befindet und false, wenn der Ball
	 *         zu weit weg ist
	 */

	public boolean ballAufheben(Ball ball) {
		double abstand = Math
				.sqrt(Math.pow(this.getSpalte() - ball.getSpalte(), 2) + Math.pow(this.zeile - ball.getZeile(), 2));
		final double maxAbstand = 3;

		if (maxAbstand >= abstand) {
			this.ballbesitz = true;
			System.out.println("\n" + this.name + " hat den Ball aufgehoben ");
			return true;
		} else {
			System.out.println("\n" + this.name + " versuchte den Ball aus zu größer Distanz aufzuheben.");
			return false;
		}
	}
}
