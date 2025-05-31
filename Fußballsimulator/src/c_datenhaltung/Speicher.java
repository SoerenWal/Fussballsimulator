package c_datenhaltung;

import java.io.Serializable;
import b_entitaeten.Ball;
import b_entitaeten.Mannschaft;

public class Speicher implements Serializable {

	public Mannschaft heimmannschaft;
	public Mannschaft gastmannschaft;
	public Ball ball;

	/**
	 * Erzeugt eine neue Instanz der Klasse Speicher.
	 * 
	 * @param heimmannschaft
	 * @param gastmannschaft
	 * @param ball
	 */
	public Speicher(Mannschaft heimmannschaft, Mannschaft gastmannschaft, Ball ball) {
		this.heimmannschaft = heimmannschaft;
		this.gastmannschaft = gastmannschaft;
		this.ball = ball;
	}

}
