package b_entitaeten;

public class Spielfeld {

	// Quelle:
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	// Reset
	public static final String RESET = "\033[0m"; // Text Reset
	// Background
	public static final String GREEN_BACKGROUND = "\033[42m"; // GREEN
	public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
	public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; // CYAN
	public static final String WHITE_BACKGROUND = "\033[47m"; // WHITE

	public static int zeilen = 17;

	public static int spalten = 81;
	private static String[][] spielfeld = new String[zeilen][spalten];
	static int mittelpunktZeile = Spielfeld.zeilen / 2;
	static int mittelpunktSpalte = Spielfeld.spalten / 2;

	/**
	 * Zeichnet das Spielfeld neu, inklusive Rasen, Spielern, Ball und Markierungen.
	 *
	 * @param ball
	 * @param heimmannschaft
	 * @param gastmannschaft
	 */

	public static void maleSpielfeld(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		spielfeld = new String[zeilen][spalten];
		maleSpieler(heimmannschaft);
		maleSpieler(gastmannschaft);
		maleRasen();
		maleMarkierungen(ball, heimmannschaft, gastmannschaft);
		maleTore();
		maleBall(ball, heimmannschaft, gastmannschaft);

	}

	private static void markiereMitHintergrundfarbe(int zeile, int spalte, String kuerzel) {
		String zeichen = " ";
		if (spielfeld[zeile][spalte] != null) {
			// Extrahiere das sichtbare Zeichen
			zeichen = spielfeld[zeile][spalte].replaceAll("\u001B\\[[;\\d]*m", "").trim();
			if (zeichen.isEmpty() || zeichen.equals(kuerzel)) {
				zeichen = " ";
			}
		}
		spielfeld[zeile][spalte] = kuerzel + zeichen + RESET;
	}

	/**
	 * Überprüft, ob ein bestimmtes Feld auf dem Spielfeld frei ist.
	 *
	 * @param zeile
	 * @param spalte
	 * @return true, wenn das Feld leer ist, sonst false
	 */

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
					markiereMitHintergrundfarbe(i, j, GREEN_BACKGROUND_BRIGHT);
				} else {
					markiereMitHintergrundfarbe(i, j, GREEN_BACKGROUND);
				}
			}
		}
	}

	/**
	 * Eine Methode, die Fußballfeld-Markierungen auf dem Spielfeld malt.
	 * @param ball
	 * @param heimmannschaft
	 * @param gastmannschaft
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
				markiereMitHintergrundfarbe(0, j, WHITE_BACKGROUND);
				markiereMitHintergrundfarbe(zeilen - 1, j, WHITE_BACKGROUND);
			}
		}
	}

	private static void maleSeitenlinienLinksRechts() {
		for (int i = 0; i < zeilen; i++) {
			markiereMitHintergrundfarbe(i, 0, WHITE_BACKGROUND);
			markiereMitHintergrundfarbe(i, spalten - 1, WHITE_BACKGROUND);
		}
	}

	/**
	 * Eine Methode die einen Mittelkreis auf das Spielfeld malt.
	 */
	private static void maleMittelkreis() {
		markiereMitHintergrundfarbe(mittelpunktZeile - 1, mittelpunktSpalte, WHITE_BACKGROUND);
		markiereMitHintergrundfarbe(mittelpunktZeile + 1, mittelpunktSpalte, WHITE_BACKGROUND);
		markiereMitHintergrundfarbe(mittelpunktZeile, mittelpunktSpalte - 1, WHITE_BACKGROUND);
		markiereMitHintergrundfarbe(mittelpunktZeile, mittelpunktSpalte + 1, WHITE_BACKGROUND);
	}

	private static void maleStrafraum() {
		for (int i = zeilen / 2 - 5; i < zeilen / 2 + 6; i++) {
			for (int j = 0; j < 16 + 1; j++) {
				// seitliche Strafraum-Linie
				markiereMitHintergrundfarbe(i, 16, WHITE_BACKGROUND);
				markiereMitHintergrundfarbe(i, spalten - 17, WHITE_BACKGROUND);

				// obere Strafraum-Linie
				markiereMitHintergrundfarbe(zeilen / 2 - 5, j, WHITE_BACKGROUND);
				markiereMitHintergrundfarbe(zeilen / 2 - 5, spalten - 17 + j, WHITE_BACKGROUND);

				markiereMitHintergrundfarbe(zeilen / 2 + 5, j, WHITE_BACKGROUND);
				markiereMitHintergrundfarbe(zeilen / 2 + 5, spalten - 17 + j, WHITE_BACKGROUND);
			}
		}
	}

	/**
	 * Eine Methode, die Tore auf das Spielfeld malt.
	 */
	private static void maleTore() {
		// links und rechts, je 3 Zeilen hoch und 2 Zeichen breit
		for (int i = zeilen / 2 - 1; i < zeilen / 2 + 2; i++) {
			markiereMitHintergrundfarbe(i, 1, WHITE_BACKGROUND);
			markiereMitHintergrundfarbe(i, 2, WHITE_BACKGROUND);
			markiereMitHintergrundfarbe(i, spalten - 3, WHITE_BACKGROUND);
			markiereMitHintergrundfarbe(i, spalten - 2, WHITE_BACKGROUND);
		}
	}

	/**
	 * Eine Methode die den Ball auf das Spielfeld malt.
	 * @param ball
	 * @param heimmannschaft
	 * @param gastmannschaft
	 */
	private static void maleBall(Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		markiereMitHintergrundfarbe(ball.getZeile(), ball.getSpalte(), CYAN_BACKGROUND_BRIGHT);
	}

	private static void maleSpieler(Mannschaft mannschaft) {
		String kuerzel;
		for (Roboter s : mannschaft.getSpieler().values()) {
			kuerzel = String.valueOf(s.getName().charAt(0));
			if (mannschaft.getSpieler().get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
				kuerzel = kuerzel.toLowerCase();
			}
			markiereMitHintergrundfarbe(s.getZeile(), s.getSpalte(), kuerzel);
		}
	}

	/**
	 * Gibt das aktuelle Spielfeld auf der Konsole aus. Die Farben und
	 * Spielerpositionen werden visuell dargestellt.
	 */

	public static void spielfeldAnzeigen() {
		System.out.println();
		for (int i = 0; i < spielfeld.length; i++) {
			for (int j = 0; j < spielfeld[i].length; j++) {
				System.out.print(spielfeld[i][j]);
			}
			System.out.println(RESET);
		}
	}

}
