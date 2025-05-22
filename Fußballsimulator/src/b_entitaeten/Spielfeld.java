package b_entitaeten;

public class Spielfeld {

	static String[][] feld = new String[35][81];

	public static void feldVorbereiten(Ball ball) {
		for(int i = 0; i < feld.length; i++) {
			for(int j = 0; j < feld[i].length; j++) {
				feld[i][j] = "_ ";
			}
		feld[ball.getX()][ball.getY()] = "⚽🏃‍♂️";
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
