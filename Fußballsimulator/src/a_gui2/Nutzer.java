package a_gui2;

import b_schiedsrichter.Schiedsrichter;
import b_entitaeten.*;

public class Nutzer {

	static Schiedsrichter schiedsrichter;

	public static void main(String[] args) {

		Mannschaft heimmannschaft = new Mannschaft("gastmannschaft");
		Stuermer h_s = new Stuermer("Sören");
		Mittelfeldspieler h_m1 = new Mittelfeldspieler("Richard");
		Mittelfeldspieler h_m2 = new Mittelfeldspieler("Richard");
		Verteidiger h_v = new Verteidiger("Noah");
		Torwart h_t = new Torwart("Felix");

		Mannschaft gastmannschaft = new Mannschaft("heimmannschaft");
		Stuermer g_s = new Stuermer("name");
		Mittelfeldspieler g_m1 = new Mittelfeldspieler("Felix");
		Mittelfeldspieler g_m2 = new Mittelfeldspieler("Noah");
		Verteidiger g_v = new Verteidiger("Noah1");
		Torwart g_t = new Torwart("TEster");

		Ball ball = Ball.getInstance(396, 246);
		schiedsrichter = Schiedsrichter.getInstance(ball);
		// Fußballfeld.fußballfeldAnzeigen();

	}
}