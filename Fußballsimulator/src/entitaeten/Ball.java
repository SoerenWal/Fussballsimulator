package entitaeten;

public final class Ball {

	private static Ball INSTANCE;
	
	private int x;
	private int y;
	private boolean amSpielFeldRand;
	private boolean imTor;
	
	private Ball(int x, int y) {
		this.setX(x);
		this.setY(y);
	};
	
	public static Ball getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Ball(0, 0);
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

	public void setAmSpielFeldRand(boolean amSpielFeldRand) {
		this.amSpielFeldRand = amSpielFeldRand;
	}
	
}
