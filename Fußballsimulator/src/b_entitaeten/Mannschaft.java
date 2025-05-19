package b_entitaeten;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Mannschaft {

	public String name;
	public boolean ballBesitz = false;
	public HashMap<String, Roboter> spieler;
	Scanner sc = new Scanner(System.in);

	public Mannschaft(String name, HashMap<String, Roboter> spieler) {
		this.name = name;
		this.spieler = spieler;
	}

	public void mannschaftAnlegen() {
		String name;
		System.out.print("Name des Stürmers: ");
		name = sc.nextLine();
		this.spieler.put("Stürmer", new Stuermer(name));
		System.out.println("\nName des linken Mittelfeldspielers: ");
		name = sc.nextLine();
		this.spieler.put("Mittelfeldspieler", new Mittelfeldspieler(name));
		System.out.print("\nName des rechten Mittelfeldspielers: ");
		name = sc.nextLine();
		this.spieler.put("Mittelfeldspieler2", new Mittelfeldspieler(name));
		System.out.print("\nName des Verteidigers: ");
		name = sc.nextLine();
		this.spieler.put("Verteidiger", new Verteidiger(name));
		System.out.print("\nName des Torwarts: ");
		name = sc.nextLine();
		this.spieler.put("Torwart", new Torwart(name));
	}

	public void zeigeEnergieBericht() {
		Collection<Roboter> values = spieler.values();
		System.out.println("Energiebericht von " + this.name);
		for (Roboter r : values) {
			System.out.println(r.getName() + " hat noch " + r.getEnergie() + " Energie.");
		}
	}
	
	public boolean getBallBesitz() {
		return this.ballBesitz;
	}
}
