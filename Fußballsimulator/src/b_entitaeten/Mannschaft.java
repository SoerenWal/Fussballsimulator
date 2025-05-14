package b_entitaeten;

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
		System.out.println("Name des Stürmers: ");
		name = sc.nextLine();
		this.spieler.put("Stürmer", new Stuermer(name));
		System.out.println("Name des linken Mittelfeldspielers: ");
		name = sc.nextLine();
		this.spieler.put("Mittelfeldspieler", new Mittelfeldspieler(name));
		System.out.println("Name des rechten Mittelfeldspielers: ");
		name = sc.nextLine();
		this.spieler.put("Mittelfeldspieler2", new Mittelfeldspieler(name));
		System.out.println("Name des Verteidigers: ");
		name = sc.nextLine();
		this.spieler.put("Verteidiger", new Verteidiger(name));
		System.out.println("Name des Torwarts: ");
		name = sc.nextLine();
		this.spieler.put("Torwart", new Torwart(name));
	}
}
