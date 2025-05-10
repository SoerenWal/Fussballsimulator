package b_schiedsrichter;

import b_entitaeten.Ball;
import b_entitaeten.Mannschaft;
import b_entitaeten.Spielfeld;

public class Schiedsrichter {

	public Mannschaft heimmannschaft;
	public Mannschaft gastmannschaft;
	public Ball ball;
	
	public Schiedsrichter(Ball ball) {
		this.ball = ball;
	}
}
