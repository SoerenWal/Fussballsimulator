package b_entitaeten;

import java.util.Collection;

public class Spielfeld {

	public static int spielfeldLaenge = 15; // 81 // 61
	public static int spielfeldBreite = 5; // 35 // 25

	private static String[][] feld = new String[spielfeldBreite][spielfeldLaenge];

	public static void spielfeldMalen(Tor[] tore, Ball ball, Collection<Roboter> spieler) {
		rasenMalen();
		toreMalen(tore);
		ballMalen(ball);
		spielerMalen(spieler);
	}

	public static void rasenMalen() {
		for (int i = 0; i < spielfeldLaenge; i++) {
			System.out.printf("%02d%s", i, "|");
		}
		System.out.println();
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[i].length; j++) {
				feld[i][j] = "....";
			}
		}
	}

	public static void toreMalen(Tor[] tore) {
		for (int i = -1; i < 2; i++) {
			feld[tore[0].yTor + i][tore[0].xTor] = "🥅..";
			feld[tore[1].yTor + i][tore[1].xTor] = "..🥅";
		}
	}

	public static void ballMalen(Ball ball) {
		feld[ball.getX()-1][ball.getY()] = ".⚽.";
	}

	public static void spielerMalen(Collection<Roboter> spieler) {
		for (Roboter s : spieler) {
			feld[s.getX()][s.getY()] = ".🏃‍♂️.";
		}
	}

	public static void spielfeldAnzeigen() {
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[i].length; j++) {
				System.out.print(feld[i][j]);
			}
			System.out.println();
		}
	}

}
