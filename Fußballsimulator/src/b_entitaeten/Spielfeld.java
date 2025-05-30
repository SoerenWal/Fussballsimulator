package b_entitaeten;

public class Spielfeld {

	public static int zeilen = 17;
	public static int spalten = 81;

	private static String[][] spielfeld = new String[zeilen][spalten];

	public static void spielfeldMalen(Tor[] tore, Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		spielerMalen(heimmannschaft);
		spielerMalen(gastmannschaft);
		ballMalen(ball);
		rasenMalen();
	}

	private static void markiereMitHintergrundfarbe(int i, int j, String farbe) {
		String zeichen = " ";

		if (spielfeld[i][j] != null) {
			// Extrahiere das sichtbare Zeichen ohne Farben
			zeichen = spielfeld[i][j].replaceAll("\u001B\\[[;\\d]*m", "").trim();
			if (zeichen.isEmpty()) {
				zeichen = " ";
			}
		}
		spielfeld[i][j] = farbe + zeichen + ConsoleColors.RESET;
	}

	/**
	 * Diese Methode wurde von ChatGPT implementiert und stellt keine Eigenleistung
	 * dar. Diese Methode befüllt das zwei-dimensionale Array feld mit
	 * Spielfeldmarkierungen und einem quarierten zweifarbigen Rasen
	 */
	public static void rasenMalen() {

		// Aufbau des Rasenmusters (Schachbrett)
		for (int i = 0; i < zeilen; i++) {
			for (int j = 0; j < spalten; j++) {
				boolean isBright = (i + j) % 2 == 0;
				markiereMitHintergrundfarbe(i, j,
						isBright ? ConsoleColors.GREEN_BACKGROUND_BRIGHT : ConsoleColors.GREEN_BACKGROUND);
			}
		}

		// Seitenlinien (oben und unten)
		for (int j = 0; j < spalten; j++) {
			spielfeld[0][j] = ConsoleColors.WHITE_BACKGROUND + " ";
			spielfeld[zeilen - 1][j] = ConsoleColors.WHITE_BACKGROUND + " ";
		}

		// Seitenlinien (links und rechts)
		for (int i = 0; i < zeilen; i++) {
			spielfeld[i][0] = ConsoleColors.WHITE_BACKGROUND + " ";
			spielfeld[i][spalten - 1] = ConsoleColors.WHITE_BACKGROUND + " ";
		}

		// Mittellinie
		int middle = zeilen / 2;
		for (int j = 0; j < spalten; j++) {
			markiereMitHintergrundfarbe(middle, j, ConsoleColors.WHITE_BACKGROUND);
		}

		// Mittelkreis (nur ein einfacher + oben/unten/links/rechts)
		int centerCol = spalten / 2;
		markiereMitHintergrundfarbe(middle - 1, centerCol, ConsoleColors.WHITE_BACKGROUND);
		markiereMitHintergrundfarbe(middle + 1, centerCol, ConsoleColors.WHITE_BACKGROUND);
		markiereMitHintergrundfarbe(middle, centerCol - 1, ConsoleColors.WHITE_BACKGROUND);
		markiereMitHintergrundfarbe(middle, centerCol + 1, ConsoleColors.WHITE_BACKGROUND);

		// Tore (links und rechts, je 3 Zeilen hoch und 2 Zeichen breit)
		int goalStart = middle - 1;
		for (int i = goalStart; i <= goalStart + 2; i++) {
			spielfeld[i][1] = ConsoleColors.BLACK_BACKGROUND + " ";
			spielfeld[i][2] = ConsoleColors.BLACK_BACKGROUND + " ";
			spielfeld[i][spalten - 3] = ConsoleColors.BLACK_BACKGROUND + " ";
			spielfeld[i][spalten - 2] = ConsoleColors.BLACK_BACKGROUND + " ";
		}
	}

	public static void ballMalen(Ball ball) {
		spielfeld[ball.zeile][ball.spalte] = "B";
	}

	public static void spielerMalen(Mannschaft mannschaft) {
		for (Roboter s : mannschaft.spieler.values()) {
			spielfeld[s.getInitialZeile()][s.getInitialSpalte()] = String.valueOf(s.getName().charAt(0));
		}
	}

	public static void spielfeldAnzeigen() {
		System.out.println();
		for (int i = 0; i < spielfeld.length; i++) {
			for (int j = 0; j < spielfeld[i].length; j++) {
				System.out.print(spielfeld[i][j]);
			}
			System.out.println(ConsoleColors.RESET);
		}
	}

}
