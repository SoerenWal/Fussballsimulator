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
	public Roboter[] spieler = new Roboter[2];
	public Ball ball;
	
	private Schiedsrichter(Ball ball, Mittelfeldspieler s, Mittelfeldspieler b2) {
		this.ball = ball;
		spieler[0] = s;
		spieler[1] = b2;
		
	}
	
	public static Schiedsrichter getInstance(Ball b, Mittelfeldspieler s, Mittelfeldspieler b2) {
		if(INSTANCE == null) {
			INSTANCE = new Schiedsrichter(b, s, b2);
		}
		return INSTANCE;
	}
}
