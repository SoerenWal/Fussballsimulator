package b_entitaeten;

import java.io.Serializable;

public final class Ball implements Serializable {

	private static Ball INSTANCE;

	public int spieldauer;
	private int zeile;
	private int spalte;

	/**
	 * Erzeugt eine neue Instanz der Singleton-Klasse Ball.
	 * 
	 * @param zeile
	 * @param spalte
	 */
	private Ball(int zeile, int spalte) {
		this.zeile = zeile;
		this.spalte = spalte;
	};

	/**
	 * Setzt die Position des Balls auf die Koordinaten des übergebenen Stürmers.
	 *
	 * @param s
	 */
	public void setKoordinatenAnstoß(Stuermer s) {
		this.zeile = s.getZeile();
		this.spalte = s.getSpalte();
	}

	/**
	 * Ruft den Konstruktor der Singleton-Klasse Ball auf.
	 * 
	 * @param zeile
	 * @param spalte
	 * @return Ein Ball
	 */
	public static Ball getInstance(int zeile, int spalte) {
		if (INSTANCE == null) {
			INSTANCE = new Ball(zeile, spalte);
		}
		return INSTANCE;
	}

	/**
	 * Gibt die aktuelle Zeilenposition des Balls zurück.
	 *
	 * @return Die Zeilenposition des Balls.
	 */

	public int getZeile() {
		return zeile;
	}

	/**
	 * Setzt die Zeilenposition des Balls.
	 *
	 * @param zeile
	 */

	public void setZeile(int zeile) {
		this.zeile = zeile;
	}

	/**
	 * Gibt die aktuelle Spaltenposition des Balls zurück.
	 *
	 * @return Die Spaltenposition des Balls.
	 */

	public int getSpalte() {
		return spalte;
	}

	/**
	 * Setzt die Spaltenposition des Balls.
	 *
	 * @param spalte
	 */

	public void setSpalte(int spalte) {
		this.spalte = spalte;
	}
}
