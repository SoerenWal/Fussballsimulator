package b_entitaeten;

public final class Ball {

	private static Ball INSTANCE;
	
	private int x;
	private int y;
	private boolean amSpielFeldRand;
	private boolean imTor;
	private int durchmesser = 8;
	
	private Ball(int x, int y) {
		this.x = x;
		this.y = y;
	};
	
	public void setKoordinatenAnstoß(Stuermer s) {
		this.x = s.getX();
		this.y = s.getY();
	}
	
	public static Ball getInstance(int x, int y) {
		if(INSTANCE == null) {
			INSTANCE = new Ball(x, y);
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
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isAmSpielFeldRand() {
		return amSpielFeldRand;
	}
	
	public int getDurchmesser() {
		return durchmesser;
	}
	
}
