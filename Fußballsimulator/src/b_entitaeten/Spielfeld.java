package b_entitaeten;

import java.util.Collection;

public class Spielfeld {

	static String[][] feld = new String[35][81];

	public static void feldVorbereiten(Ball ball, Collection<Roboter> spielerArrayList) {
		for(int i = 0; i < feld.length; i++) {
			for(int j = 0; j < feld[i].length; j++) {
				feld[i][j] = "..";
			}
		feld[ball.getX()][ball.getY()] = "⚽";
		for(Roboter spieler : spielerArrayList) {
			feld[spieler.getX()][spieler.getY()] = "🏃‍♂️";
		}
		}
	}

	public static void feldAnzeigen() {
		for(int i = 0; i < feld.length; i++) {
			for(int j = 0; j < feld[i].length; j++) {
				System.out.print(feld[i][j]);
			}
			System.out.println();
		}
	}

}
