package b_entitaeten;

import java.io.Serializable;

public final class Ball implements Serializable {
 
	private static Ball INSTANCE;
	
	public int spieldauer;
	private int zeile;
	private int spalte;
 
	/**
	 * Erzeugt eine neue Instanz der Singleton-Klasse Ball.
	 * @param zeile
	 * @param spalte
	 */
	private Ball(int zeile, int spalte) {
		this.zeile = zeile;
		this.spalte = spalte;
	};
 
	public void setKoordinatenAnstoß(Stuermer s) {
		this.zeile = s.getZeile();
		this.spalte = s.getSpalte();
	}
 
	/**
	 * Ruft den Konstruktor der Singleton-Klasse Ball auf.
	 * @param zeile
	 * @param spalte
	 * @return Eine Ball 
	 */
	public static Ball getInstance(int zeile, int spalte) {
		if (INSTANCE == null) {
			INSTANCE = new Ball(zeile, spalte);
		}
		return INSTANCE;
	}
 
	public int getZeile() {
		return zeile;
	}
 
	public void setZeile(int zeile) {
		this.zeile = zeile;
	}
 
	public int getSpalte() {
		return spalte;
	}
 
	public void setSpalte(int spalte) {
		this.spalte = spalte;
	} 
}
 