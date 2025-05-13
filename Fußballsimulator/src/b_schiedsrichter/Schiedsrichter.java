package b_schiedsrichter;

import b_entitaeten.Ball;
import b_entitaeten.Mannschaft;
import b_entitaeten.Mittelfeldspieler;
import b_entitaeten.Roboter;
import b_entitaeten.Spielfeld;

public final class Schiedsrichter {
	
	private static Schiedsrichter INSTANCE;

	public Mannschaft heimmannschaft;
	public Mannschaft gastmannschaft;
	public Ball ball;
	
	private Schiedsrichter(Ball ball, Mannschaft heimgastmannschaft, Mannschaft gastmannschaft) {
		this.ball = ball;
		
	}
	
	public Schiedsrichter() {}

	public static Schiedsrichter getInstance(Ball ball, Mannschaft heimgastmannschaft, Mannschaft gastmannschaft) {
		if(INSTANCE == null) {
			INSTANCE = new Schiedsrichter(ball, heimgastmannschaft, gastmannschaft);
		}
		return INSTANCE;
	}
}
