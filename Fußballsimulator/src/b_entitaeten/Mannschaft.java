package b_entitaeten;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Mannschaft {

	public String name;
	public int tore = 0;
	public boolean ballBesitz = false;
	public HashMap<String, Roboter> spieler;
	static Scanner sc = new Scanner(System.in);

	public Mannschaft(HashMap<String, Roboter> spieler) {
		this.spieler = spieler;
	}

	public static void mannschaftAnlegen(Tor[] tore, Mannschaft heimmannschaft, Mannschaft gastmannschaft) {
		System.out.println("0.Heimmannschaft\n1.Gastmannschaft\n2.Zurück");
		switch (LogikHelper.menuEingabe(2)) {
		case 0:
			heimmannschaftAnlegen(heimmannschaft, tore[0]);
			break;
		case 1:
			gastmannschaftAnlegen(gastmannschaft, tore[1]);
			break;
		case 2:
			break;
		}
	}

	private static void heimmannschaftAnlegen(Mannschaft heimmannschaft, Tor tor) {
		System.out.print("Name der Heimmannschaft: ");
		heimmannschaft.name = sc.nextLine();
		String name;
		System.out.print("Name des Stürmers: ");
		name = sc.nextLine();
		heimmannschaft.spieler.put("Stürmer", new Stuermer(name));
		System.out.print("Name des linken Mittelfeldspielers: ");
		name = sc.nextLine();
		heimmannschaft.spieler.put("Mittelfeldspieler", new Mittelfeldspieler(name));
		System.out.print("Name des rechten Mittelfeldspielers: ");
		name = sc.nextLine();
		heimmannschaft.spieler.put("Mittelfeldspieler2", new Mittelfeldspieler(name));
		System.out.print("Name des Verteidigers: ");
		name = sc.nextLine();
		heimmannschaft.spieler.put("Verteidiger", new Verteidiger(name));
		System.out.print("Name des Torwarts: ");
		name = sc.nextLine();
		heimmannschaft.spieler.put("Torwart", new Torwart(name, tor.yTor, tor.xTor + 1));
		System.out.println();
	}

	private static void gastmannschaftAnlegen(Mannschaft gastmannschaft, Tor tor) {
		System.out.print("Name der Heimmannschaft: ");
		gastmannschaft.name = sc.nextLine();
		String name;
		System.out.print("Name des Stürmers: ");
		name = sc.nextLine();
		gastmannschaft.spieler.put("Stürmer", new Stuermer(name));
		System.out.print("Name des linken Mittelfeldspielers: ");
		name = sc.nextLine();
		gastmannschaft.spieler.put("Mittelfeldspieler", new Mittelfeldspieler(name));
		System.out.print("Name des rechten Mittelfeldspielers: ");
		name = sc.nextLine();
		gastmannschaft.spieler.put("Mittelfeldspieler2", new Mittelfeldspieler(name));
		System.out.print("Name des Verteidigers: ");
		name = sc.nextLine();
		gastmannschaft.spieler.put("Verteidiger", new Verteidiger(name));
		System.out.print("Name des Torwarts: ");
		name = sc.nextLine();
		gastmannschaft.spieler.put("Torwart", new Torwart(name, tor.yTor, tor.xTor - 1));
		System.out.println();
		gastmannschaft.spieler.get("Stürmer");
	}

	private void ballbesitzAnzeigen(Mannschaft mannschaft) {
		if (this.ballBesitz) {
			System.out.println(this.name + " hat derzeit Ballbesitz.");
		} else {
			System.out.println(mannschaft.name + " hat derzeit Ballbesitz.");
		}
	}

	private void energieAnzeigen() {
		Collection<Roboter> values = spieler.values();
		System.out.println("Energiebericht von " + this.name);
		for (Roboter r : values) {
			System.out.println(r.getName() + " hat noch " + r.getEnergie() + " Energie.");
			}
	}

	public void gegenueberstellen(Mannschaft mannschaft) {
		if (this.spieler != null && mannschaft.spieler != null) {
			System.out.println(this.name + " " + this.tore + " - " + mannschaft.tore + " " + mannschaft.name);
			ballbesitzAnzeigen(mannschaft);
		} else {
			System.out.println("\nBitte legen Sie zunächst beide Mannschaften an.");
		}
	}
	
	public void standardAufstellung() {
		this.spieler.get("Mittelfeldspieler").setX(6);
		System.out.println("Hallo");
		
	}
}
