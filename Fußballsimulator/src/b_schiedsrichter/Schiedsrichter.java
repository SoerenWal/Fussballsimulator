package b_schiedsrichter;

import b_entitaeten.Ball;
import b_entitaeten.Mannschaft;
import b_entitaeten.Spielfeld;

public final class Schiedsrichter {
	
	private static Schiedsrichter INSTANCE;

	public Mannschaft heimmannschaft;
	public Mannschaft gastmannschaft;
	public Ball ball;
	
	private Schiedsrichter(Ball ball) {
		this.ball = ball;
	}
	
	public static Schiedsrichter getInstance(Ball b) {
		if(INSTANCE == null) {
			INSTANCE = new Schiedsrichter(b);
		}
		return INSTANCE;
	}
}
