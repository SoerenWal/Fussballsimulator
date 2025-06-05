package b_entitaeten;

import java.io.Serializable;

public class Tor implements Serializable {

	private int zeile;
	private int spalte;

	/**
	 * Setzt die Zeilenposition des Tors.
	 *
	 * @param zeile
	 */

	public void setZeile(int zeile) {
		this.zeile = zeile;
	}

	/**
	 * Setzt die Spaltenposition des Tors.
	 *
	 * @param spalte
	 */
	public void setSpalte(int spalte) {
		this.spalte = spalte;
	}

	/**
	 * Gibt die aktuelle Zeilenposition des Tors zurück.
	 *
	 * @return Die Zeilenkoordinate des Tors
	 */

	public int getZeile() {
		return zeile;
	}

	/**
	 * Gibt die aktuelle Spaltenposition des Tors zurück.
	 *
	 * @return Die Spaltenkoordinate des Tors
	 */

	public int getSpalte() {
		return spalte;
	}

	/**
	 * Ereugt eine neue Instanz der Klasse Tor.
	 * 
	 * @param zeile
	 * @param spalte
	 */
	public Tor(int zeile, int spalte) {
		this.setZeile(zeile);
		this.setSpalte(spalte);
	}
}
