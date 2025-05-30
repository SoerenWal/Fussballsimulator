package c_datenhaltung;

import java.io.Serializable;
import b_entitaeten.Ball;
import b_entitaeten.Mannschaft;

public class Speicher implements Serializable {
	
	public Mannschaft heimmannschaft;
	public Mannschaft gastmannschaft;
	public Ball ball;

	public Speicher(Mannschaft heimmannschaft, Mannschaft gastmannschaft, Ball ball) {
		this.heimmannschaft = heimmannschaft;
		this.gastmannschaft = gastmannschaft;
		this.ball = ball;
	}

}
