package b_schiedsrichter;

import b_entitaeten.Ball;
import b_entitaeten.Mannschaft;

public final class Schiedsrichter {

	private static Schiedsrichter INSTANCE;

	public Mannschaft heimmannschaft;
	public Mannschaft gastmannschaft;
	public Ball ball;

	private Schiedsrichter(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		this.ball = ball;
		this.heimmannschaft = heimmannschaft;
		this.gastmannschaft = gastmannschaft;

	}

	private Schiedsrichter() {}

	public static Schiedsrichter getInstance(Ball ball, Mannschaft heimgastmannschaft, Mannschaft gastmannschaft) {
		if (INSTANCE == null) {
			INSTANCE = new Schiedsrichter(ball, heimgastmannschaft, gastmannschaft);
		}
		return INSTANCE;
	}
	
	public void zeigeSpielstand() {
		System.out.println("\nMannschaft | Tore | Ballbesitz");
		System.out.println(this.heimmannschaft.name + " | " + this.heimmannschaft.tore + " | " + this.heimmannschaft.ballBesitz);
		System.out.println(this.gastmannschaft.name + " | " + this.gastmannschaft.tore + " | " + this.gastmannschaft.ballBesitz);
		System.out.println();
	}
}
