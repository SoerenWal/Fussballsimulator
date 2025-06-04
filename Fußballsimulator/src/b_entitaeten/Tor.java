package b_entitaeten;

import java.io.Serializable;

public class Tor implements Serializable {

	private int zeile;
	private int spalte;

	public void setZeile(int zeile) {
		this.zeile = zeile;
	}

	public void setSpalte(int spalte) {
		this.spalte = spalte;
	}

	public int getZeile() {
		return zeile;
	}

	public int getSpalte() {
		return spalte;
	}

	/**
	 * Ereugt eine neue Instanz der Klasse Tor
	 * 
	 * @param zeile
	 * @param spalte
	 */
	public Tor(int zeile, int spalte) {
		this.setZeile(zeile);
		this.setSpalte(spalte);
	}
}
