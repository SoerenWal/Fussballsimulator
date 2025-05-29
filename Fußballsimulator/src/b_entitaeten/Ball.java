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
 
	public int getZeile() {
		return zeile;
	}
 
	public void setZeile(int zeile) {
		this.zeile = zeile;
	}
 
	public int getSpalte() {
		return spalte;
	}
 
	public void setSpalte(int spalte) {
		this.spalte = spalte;
	}
 
	public boolean isAmSpielFeldRand() {
		return amSpielFeldRand;
	}
 
}
 