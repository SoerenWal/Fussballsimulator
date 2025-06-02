package b_entitaeten;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Roboter implements Serializable {

	/** Gesamtanzahl erstellter Roboter (statisch) */
	public static int anzahlRoboter = 0;

	private String name;
	private int id;
	private double geschwindigkeit;
	private int energie = 20;
	private boolean ballBesitz = false;
	private double praezisionPass;
	private double praezisionSchuss;
	private int initialZeile;
	private int initialSpalte;
	private int zeile;
	private int spalte;
	protected double faktorPass;
	protected double faktorSchuss;

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
	 * Prüft, ob der Roboter den Ball besitzt.
	 * 
	 * @return true, wenn Ballbesitz vorliegt
	 */

	public boolean getBallBesitz() {
		return this.ballBesitz;
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
	 * @param ballBesitz true, wenn Ballbesitz
	 */
	public void setBallBesitz(boolean ballBesitz) {
		this.ballBesitz = ballBesitz;
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
		System.out.println("Wohin soll " + this.name + " laufen?");
		System.out.println("Zeilen nach unten (X).\nZeilen nach oben (-X).");
		int zeile = LogikHelper.menuEingabe(17);
		System.out.println("Spalten nach rechts (X).\nSpalten nach links (-X).");
		int spalte = LogikHelper.menuEingabe(81);
		if (this.zeile + zeile > 0 && this.zeile + zeile < 17 && this.spalte + spalte > 0
				&& this.spalte + spalte < 81) {
			verbraucheEnergie(Math.abs(zeile + spalte));
			this.zeile = this.zeile + zeile;
			this.spalte = this.spalte + spalte;
		} else {
			System.out.println("Ihr Spiel darf das Spielfeld nicht verlassen.");
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

	public void passen(Roboter roboter) {
		if (this.ballBesitz && this.energie != 0) {
			if ((this.praezisionPass / verbraucheEnergie(5)) >= RoboterHelper.randomZahl()) {
				this.ballBesitz = false;
				roboter.ballBesitz = true;
				System.out.println("Erfolgreicher Pass");
			} else {
				this.ballBesitz = false;
				System.out.println("Fehlpass");
			}
		} else {
			System.out.println("Der Spieler hat den Ball nicht");
		}
	}

	/**
	 * Führt einen Schussversuch auf das Tor aus.
	 */

	public void schiessen() {
		// prüfeDistanz() if this.getX mit Linie
		if (this.zeile >= 15 || this.zeile <= 65) {
			if ((this.praezisionSchuss / verbraucheEnergie(8)) >= RoboterHelper.randomZahl()) {

			}
		}

	}

	/**
	 * Führt einen Blockversuch aus.
	 * 
	 * @return true, wenn Block ausgelöst wird
	 */

	public boolean blocken() {
		// Beim Aufruf dieser Funktion soll der Ball eines gegners geblocket werden
		return true;
	}

	/**
	 * Lädt die Energie des Roboters um 2 Punkte auf (max. 20).
	 */

	public void energieAufladen() {
		this.energie = Math.min(this.energie + 2, 20);
	}

	/**
	 * Setzt den Roboter außer Gefecht.
	 */

	public void ausfallen() {

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
	 * Methode zur Prüfung des Ballbesitzes.
	 *
	 * @return true, wenn Ballbesitz vorhanden ist
	 */

	public boolean pruefeBallbesitz() {
		return this.getBallBesitz();
	}
}
