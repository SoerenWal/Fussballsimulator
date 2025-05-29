package b_entitaeten;

public class Spielfeld {

	public static int spielfeldZeilen = 17;
	public static int spielfeldSpalten = 81;

	private static String[][] field = new String[spielfeldZeilen][spielfeldSpalten];

	public static void spielfeldMalen(Tor[] tore, Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		spielerMalen(heimmannschaft);
		spielerMalen(gastmannschaft);
		ballMalen(ball);
		rasenMalen();
		toreMalen(tore);
	}

	public static void rasenMalen() {
		String G1 = ConsoleColors.GREEN_BACKGROUND;
		String G2 = ConsoleColors.GREEN_BACKGROUND_BRIGHT;
		String W = ConsoleColors.WHITE_BACKGROUND;
		String RESET = ConsoleColors.RESET;

		final int rows = spielfeldZeilen; // Zeilen: 17
		final int cols = spielfeldSpalten; // Spalten: 81

		int centerRow = rows / 2;
		int centerCol = cols / 2;
		int circleRadiusSquared = 9; // radius ~3
		int pbxMin = centerRow - 4;
		int pbxMax = centerRow + 4;
		int gap = 1;

		// Spielfeld füllen
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				String grass = ((row + col) & 1) == 0 ? G1 : G2;

				boolean inCenterCircle = ((row - centerRow) * (row - centerRow)
						+ (col - centerCol) * (col - centerCol)) <= circleRadiusSquared;

				boolean inOuterBoxLeft = (col == gap || col == 17) && row >= pbxMin && row <= pbxMax;
				boolean inOuterBoxTopLeft = (row == pbxMin || row == pbxMax) && col >= gap && col <= 17;
				boolean inOuterBoxRight = (col == cols - 1 - gap || col == cols - 18) && row >= pbxMin && row <= pbxMax;
				boolean inOuterBoxTopRight = (row == pbxMin || row == pbxMax) && col >= cols - 18
						&& col <= cols - 1 - gap;

				boolean inInnerBoxLeft = (col == gap || col == 7) && row >= centerRow - 2 && row <= centerRow + 2;
				boolean inInnerBoxTopLeft = (row == centerRow - 2 || row == centerRow + 2) && col >= gap && col <= 7;
				boolean inInnerBoxRight = (col == cols - 1 - gap || col == cols - 8) && row >= centerRow - 2
						&& row <= centerRow + 2;
				boolean inInnerBoxTopRight = (row == centerRow - 2 || row == centerRow + 2) && col >= cols - 8
						&& col <= cols - 1 - gap;

				boolean isPenaltySpotLeft = (col == 11 && row == centerRow);
				boolean isPenaltySpotRight = (col == cols - 12 && row == centerRow);

				boolean isGoalLeft = (col == 0 && row >= centerRow - 1 && row <= centerRow + 1);
				boolean isGoalRight = (col == cols - 1 && row >= centerRow - 1 && row <= centerRow + 1);

				boolean isLine = row == 0 || row == rows - 1 || col == 0 || col == cols - 1 || col == centerCol
						|| inCenterCircle || inOuterBoxLeft || inOuterBoxTopLeft || inOuterBoxRight
						|| inOuterBoxTopRight || inInnerBoxLeft || inInnerBoxTopLeft || inInnerBoxRight
						|| inInnerBoxTopRight || isPenaltySpotLeft || isPenaltySpotRight || isGoalLeft || isGoalRight;

				// Setze Linie oder farbiges Gras
				if(field[row][col] != null) {
				field[row][col] = (isLine ? W : grass) + field[row][col] + RESET;
				} else if(field[row][col] == null) {
					field[row][col] = (isLine ? W : grass) + " " + RESET;
				}
			}
		}
	}

	public static void toreMalen(Tor[] tore) {
		for (int i = -1; i < 2; i++) { // 3 Zeilen hoch
			for (int j = 0; j < 2; j++) { // 2 Spalten breit
				// Linkes Tor
				field[tore[0].zeile + i][tore[0].spalte + j] = field[tore[0].zeile + i][tore[0].spalte + j] + ConsoleColors.BLACK_BACKGROUND + " " + ConsoleColors.RESET;

				// Rechtes Tor
				field[tore[1].zeile + i][tore[1].spalte - j] = field[tore[0].zeile + i][tore[0].spalte + j] + ConsoleColors.BLACK_BACKGROUND + " " + ConsoleColors.RESET;
			}
		}
	}

	public static void ballMalen(Ball ball) {
		field[ball.zeile][ball.spalte] = "B";
	}

	public static void spielerMalen(Mannschaft mannschaft) {
		for (Roboter s : mannschaft.spieler.values()) {
			field[s.getZeile()][s.getSpalte()] = String.valueOf(s.getName().charAt(0));
		}
	}

	public static void spielfeldAnzeigen() {
		System.out.println();
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
	}

}
