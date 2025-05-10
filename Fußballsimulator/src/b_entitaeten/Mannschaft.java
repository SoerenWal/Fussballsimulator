package b_entitaeten;

public class Mannschaft {
	
	public String name;
	Roboter[] spieler = new Roboter[5];
	public boolean ballBesitz;
	
	public Mannschaft(String name) {
		this.name = name;
	}
	
}
