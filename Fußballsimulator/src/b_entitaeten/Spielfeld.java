package b_entitaeten;

public class Spielfeld {

	public static int zeilen = 17;
	public static int spalten = 81;
	private static String[][] spielfeld = new String[zeilen][spalten];
	static int mittelpunktZeile = Spielfeld.zeilen / 2;
	static int mittelpunktSpalte = Spielfeld.spalten / 2;

	public static void maleSpielfeld(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		spielfeld = new String[zeilen][spalten];
		maleSpieler(heimmannschaft);
		maleSpieler(gastmannschaft);
		maleRasen();
		maleMarkierungen(ball, heimmannschaft, gastmannschaft);
		maleTore();
		maleBall(ball, heimmannschaft, gastmannschaft);

	}

	/**
	 * Diese Methode wurde von ChatGPT implementiert und stellt keine Eigenleistung
	 * dar. Diese Methode befüllt das zwei-dimensionale Array feld mit
	 * Spielfeldmarkierungen und einem quarierten zweifarbigen Rasen
	 */
	private static void markiereMitHintergrundfarbe(int zeile, int spalte, String kuerzel) {
		String zeichen = " ";
		if (spielfeld[zeile][spalte] != null) {
			// Extrahiere das sichtbare Zeichen
			zeichen = spielfeld[zeile][spalte].replaceAll("\u001B\\[[;\\d]*m", "").trim();
			if (zeichen.isEmpty() || zeichen.equals(kuerzel)) {
				zeichen = " ";
			}
		}
		spielfeld[zeile][spalte] = kuerzel + zeichen + ConsoleColors.RESET;
	}
	
	public static boolean istFeldFrei(int zeile, int spalte) {
		return spielfeld[zeile][spalte].replaceAll("\u001B\\[[;\\d]*m", "").trim().isEmpty();
	}

	/**
	 * Eine Methode, die den Rasen des Spielfelds in einem Schachbrett-Muster malt.
	 */
	public static void maleRasen() {
		for (int i = 0; i < zeilen; i++) {
			for (int j = 0; j < spalten; j++) {
				if ((i + j) % 2 == 0) {
					markiereMitHintergrundfarbe(i, j, ConsoleColors.GREEN_BACKGROUND_BRIGHT);
				} else {
					markiereMitHintergrundfarbe(i, j, ConsoleColors.GREEN_BACKGROUND);
				}
			}
		}
	}

	/**
	 * Eine Methode, die Fußballfeld-Markierungen auf dem Spielfeld malt.
	 */
	public static void maleMarkierungen(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		maleSeitenlinienObenUnten();
		maleSeitenlinienLinksRechts();
		maleMittelkreis();
		maleStrafraum();
	}

	private static void maleSeitenlinienObenUnten() {
		for (int i = 0; i < zeilen; i++) {
			for (int j = 0; j < spalten; j++) {
				markiereMitHintergrundfarbe(0, j, ConsoleColors.WHITE_BACKGROUND);
				markiereMitHintergrundfarbe(zeilen - 1, j, ConsoleColors.WHITE_BACKGROUND);
			}
		}
	}

	private static void maleSeitenlinienLinksRechts() {
		for (int i = 0; i < zeilen; i++) {
			markiereMitHintergrundfarbe(i, 0, ConsoleColors.WHITE_BACKGROUND);
			markiereMitHintergrundfarbe(i, spalten - 1, ConsoleColors.WHITE_BACKGROUND);
		}
	}

	/*
	 * // Mittellinie
	 * 
	 * for (int j = 0; j < spalten; j++) { markiereMitHintergrundfarbe(middle, j,
	 * ConsoleColors.WHITE_BACKGROUND); }
	 */

	// Mittelkreis
	/**
	 * Eine Methode die einen Mittelkreis auf das Spielfeld malt.
	 */
	private static void maleMittelkreis() {
		int middle = zeilen / 2;
		int centerCol = spalten / 2;
		markiereMitHintergrundfarbe(middle - 1, centerCol, ConsoleColors.WHITE_BACKGROUND);
		markiereMitHintergrundfarbe(middle + 1, centerCol, ConsoleColors.WHITE_BACKGROUND);
		markiereMitHintergrundfarbe(middle, centerCol - 1, ConsoleColors.WHITE_BACKGROUND);
		markiereMitHintergrundfarbe(middle, centerCol + 1, ConsoleColors.WHITE_BACKGROUND);
	}

	private static void maleStrafraum() {
		for (int i = zeilen / 2 - 5; i < zeilen / 2 + 6; i++) {
			for (int j = 0; j < 16 + 1; j++) {
				// seitliche Strafraum-Linie
				markiereMitHintergrundfarbe(i, 16, ConsoleColors.WHITE_BACKGROUND);
				markiereMitHintergrundfarbe(i, spalten - 17, ConsoleColors.WHITE_BACKGROUND);

				// obere Strafraum-Linie
				markiereMitHintergrundfarbe(zeilen / 2 - 5, j, ConsoleColors.WHITE_BACKGROUND);
				markiereMitHintergrundfarbe(zeilen / 2 - 5, spalten - 17 + j, ConsoleColors.WHITE_BACKGROUND);

				markiereMitHintergrundfarbe(zeilen / 2 + 5, j, ConsoleColors.WHITE_BACKGROUND);
				markiereMitHintergrundfarbe(zeilen / 2 + 5, spalten - 17 + j, ConsoleColors.WHITE_BACKGROUND);
			}
		}
	}

	/**
	 * Eine Methode, die Tore auf das Spielfeld malt.
	 */
	private static void maleTore() {
		// links und rechts, je 3 Zeilen hoch und 2 Zeichen breit
		for (int i = zeilen / 2 - 1; i < zeilen / 2 + 2; i++) {
			spielfeld[i][1] = ConsoleColors.WHITE_BACKGROUND + " " + ConsoleColors.RESET;
			spielfeld[i][2] = ConsoleColors.WHITE_BACKGROUND + " " + ConsoleColors.RESET;
			spielfeld[i][spalten - 3] = ConsoleColors.WHITE_BACKGROUND + " " + ConsoleColors.RESET;
			spielfeld[i][spalten - 2] = ConsoleColors.WHITE_BACKGROUND + " " + ConsoleColors.RESET;
		}
	}

	/**
	 * Eine Methode die den Ball auf das Spielfeld malt.
	 * 
	 * @param ball
	 */
	private static void maleBall(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		markiereMitHintergrundfarbe(ball.zeile, ball.spalte, ConsoleColors.CYAN_BACKGROUND_BRIGHT);
	}

	private static void maleSpieler(Mannschaft mannschaft) {
		String kuerzel;
		for (Roboter s : mannschaft.spieler.values()) {
			kuerzel = String.valueOf(s.getName().charAt(1));
			if(mannschaft.spieler.get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
				kuerzel = kuerzel.toLowerCase();
			}
			markiereMitHintergrundfarbe(s.getZeile(), s.getSpalte(), kuerzel);
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
