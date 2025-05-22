package a_gui2;

import b_schiedsrichter.Schiedsrichter;
import b_entitaeten.*;

public class Nutzer {

	static Schiedsrichter schiedsrichter;

	public static void main(String[] args) {
		schiedsrichter = Schiedsrichter.getInstance(Ball.getInstance(0,0), new Mannschaft("heimmannschaft"), new Mannschaft("gastmannschaft"));
		Navigation.menuInteraktion();
		// Fußballfeld.fußballfeldAnzeigen();

	}
}