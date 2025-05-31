package c_datenhaltung;

import java.io.Serializable;
import b_entitaeten.Ball;
import b_entitaeten.Mannschaft;

public final class Speicher implements Serializable {
	
	private static Speicher INSTANCE;
	public Mannschaft heimmannschaft;
	public Mannschaft gastmannschaft;
	public Ball ball;
	
	/**
	 * Erzeugt eine neue Instanz der Singleton-Klasse Speicher.
	 * @param heimmannschaft
	 * @param gastmannschaft
	 * @param ball
	 */
	private Speicher(Mannschaft heimmannschaft, Mannschaft gastmannschaft, Ball ball) {
		this.heimmannschaft = heimmannschaft;
		this.gastmannschaft = gastmannschaft;
		this.ball = ball;
	}
	
	/**
	 * Ruft den Konstruktor der Singleton-Klasse Speicher auf.
	 * @param heimmannschaft
	 * @param gastmannschaft
	 * @param ball
	 * @return Eine Speicher-Instanz
	 */
	public static Speicher getInstance(Mannschaft heimmannschaft, Mannschaft gastmannschaft, Ball ball) {
		if (INSTANCE == null) {
			INSTANCE = new Speicher(heimmannschaft, gastmannschaft, ball);
		}
		return INSTANCE;
	}

}
