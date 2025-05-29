package b_entitaeten;

public class Spielfeld {

	public static int spielfeldSpalten = 81;
	public static int spielfeldZeilen = 17;

	private static String[][] field = new String[spielfeldSpalten][spielfeldZeilen];

	public static void spielfeldMalen(Tor[] tore, Ball ball, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		rasenMalen();
		toreMalen(tore);
		ballMalen(ball);
		spielerMalen(heimmannschaft);
		spielerMalen(gastmannschaft);
	}

	public static void rasenMalen() {
		String G1 = ConsoleColors.GREEN_BACKGROUND;
        String G2 = ConsoleColors.GREEN_BACKGROUND_BRIGHT;
        String W = ConsoleColors.WHITE_BACKGROUND;
        String RESET = ConsoleColors.RESET;

        final int rows = spielfeldZeilen;
        final int cols = spielfeldSpalten;

        int centerRow = rows / 2;
        int centerCol = cols / 2;
        int circleRadiusSquared = 9; // radius ~3
        int pbxMin = centerRow - 4;
        int pbxMax = centerRow + 4;
        int gap = 1;
	
		// Build the field into the array
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String grass = ((row + col) & 1) == 0 ? G1 : G2;

                boolean inCenterCircle = ((row - centerRow) * (row - centerRow) + (col - centerCol) * (col - centerCol)) <= circleRadiusSquared;

                boolean inOuterBoxLeft = (col == gap || col == 17) && row >= pbxMin && row <= pbxMax;
                boolean inOuterBoxTopLeft = (row == pbxMin || row == pbxMax) && col >= gap && col <= 17;
                boolean inOuterBoxRight = (col == cols - 1 - gap || col == cols - 18) && row >= pbxMin && row <= pbxMax;
                boolean inOuterBoxTopRight = (row == pbxMin || row == pbxMax) && col >= cols - 18 && col <= cols - 1 - gap;

                boolean inInnerBoxLeft = (col == gap || col == 7) && row >= centerRow - 2 && row <= centerRow + 2;
                boolean inInnerBoxTopLeft = (row == centerRow - 2 || row == centerRow + 2) && col >= gap && col <= 7;
                boolean inInnerBoxRight = (col == cols - 1 - gap || col == cols - 8) && row >= centerRow - 2 && row <= centerRow + 2;
                boolean inInnerBoxTopRight = (row == centerRow - 2 || row == centerRow + 2) && col >= cols - 8 && col <= cols - 1 - gap;

                boolean isPenaltySpotLeft = (col == 11 && row == centerRow);
                boolean isPenaltySpotRight = (col == cols - 12 && row == centerRow);

                boolean isGoalLeft = (col == 0 && row >= centerRow - 1 && row <= centerRow + 1);
                boolean isGoalRight = (col == cols - 1 && row >= centerRow - 1 && row <= centerRow + 1);

                boolean isLine = row == 0 || row == rows - 1 || col == 0 || col == cols - 1
                        || col == centerCol
                        || inCenterCircle
                        || inOuterBoxLeft || inOuterBoxTopLeft
                        || inOuterBoxRight || inOuterBoxTopRight
                        || inInnerBoxLeft || inInnerBoxTopLeft
                        || inInnerBoxRight || inInnerBoxTopRight
                        || isPenaltySpotLeft || isPenaltySpotRight
                        || isGoalLeft || isGoalRight;

                // Store either line or grass with color
                field[row][col] = (isLine ? W : grass) + " " + RESET;
            }
        }
	}

	public static void toreMalen(Tor[] tore) {
		for (int i = -1; i < 2; i++) {
			field[tore[0].yTor + i][tore[0].xTor] = "T";
			field[tore[1].yTor + i][tore[1].xTor] = "T";
		}
	}

	public static void ballMalen(Ball ball) {
		field[ball.getX() - 1][ball.getY()] = "B";
	}

	public static void spielerMalen(Mannschaft mannschaft) {
		for (Roboter s : mannschaft.spieler.values()) {
			field[s.getX()][s.getY()] = String.valueOf(s.getName().charAt(0));
		}
	}

	public static void spielfeldAnzeigen() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
	}

}
