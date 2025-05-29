package b_entitaeten;
 
public final class Ball {
 
	private static Ball INSTANCE;
 
	int zeile;
	int spalte;
	private boolean amSpielFeldRand;
	private boolean imTor;
 
	private Ball(int zeile, int spalte) {
		this.zeile = zeile;
		this.spalte = spalte;
	};
 
	public void setKoordinatenAnstoß(Stuermer s) {
		this.zeile = s.getZeile();
		this.spalte = s.getSpalte();
	}
 
	/**
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public static Ball getInstance(int zeile, int spalte) {
		if (INSTANCE == null) {
			INSTANCE = new Ball(zeile, spalte);
		}
		return INSTANCE;
	}
 
	public boolean IsImTor() {
		return imTor;
	}
 
	public void setImTor(boolean imTor) {
		this.imTor = imTor;
	}
 
	public int getX() {
		return zeile;
	}
 
	public void setX(int x) {
		this.zeile = x;
	}
 
	public int getY() {
		return spalte;
	}
 
	public void setY(int y) {
		this.spalte = y;
	}
 
	public boolean isAmSpielFeldRand() {
		return amSpielFeldRand;
	}
 
}
 