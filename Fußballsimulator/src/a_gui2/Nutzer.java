package a_gui2;

import b_schiedsrichter.Schiedsrichter;
import b_entitaeten.*;

public class Nutzer {

	static Schiedsrichter schiedsrichter;

	public static void main(String[] args) {

		Mannschaft heimmannschaft = new Mannschaft("gastmannschaft");
		Stuermer h_s = new Stuermer("Sören");
		Mittelfeldspieler h_m1 = new Mittelfeldspieler();
		Mittelfeldspieler h_m2 = new Mittelfeldspieler();
		Verteidiger h_v = new Verteidiger();
		Torwart h_t = new Torwart();

		Mannschaft gastmannschaft = new Mannschaft("heimmannschaft");
		Stuermer g_s = new Stuermer();
		Mittelfeldspieler g_m1 = new Mittelfeldspieler();
		Mittelfeldspieler g_m2 = new Mittelfeldspieler();
		Verteidiger g_v = new Verteidiger();
		Torwart g_t = new Torwart();

		Ball ball = Ball.getInstance(396, 246);
		schiedsrichter = Schiedsrichter.getInstance(ball);
		// Fußballfeld.fußballfeldAnzeigen();

	}
}