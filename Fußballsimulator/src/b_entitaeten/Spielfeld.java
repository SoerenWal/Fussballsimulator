package b_entitaeten;

public class Spielfeld {

	static String[][] feld = new String[86][105];

	public static void feldVorbereiten() {
		for(int i = 0; i < feld.length; i++) {
			for(int j = 0; j < feld[i].length; j++) {
				feld[i][j] = "_ ";
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
