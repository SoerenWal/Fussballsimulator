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
		System.out.println("\nWelche Mannschaft möchten Sie anlegen?");
		System.out.println("\n0.Heimmannschaft\n1.Gastmannschaft\n2.Zurück");
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
		System.out.print("\nName der Heimmannschaft: ");
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
	}

	private static void gastmannschaftAnlegen(Mannschaft gastmannschaft, Tor tor) {
		System.out.print("\nName der Gastmannschaft: ");
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
	}

	private void ballbesitzAnzeigen(Mannschaft mannschaft) {
		if (this.ballBesitz) {
			System.out.println(this.name + " hat derzeit Ballbesitz.");
		} else if (mannschaft.ballBesitz) {
			System.out.println(mannschaft.name + " hat derzeit Ballbesitz.");
		}
	}

	private void energieAnzeigen() {
		Collection<Roboter> values = spieler.values();
		System.out.println("\nEnergiebericht von " + this.name);
		for (Roboter r : values) {
			System.out.println(r.getName() + " hat noch " + r.getEnergie() + " Energie.");
			}
	}

	public void gegenueberstellen(Mannschaft mannschaft) {
		if (!this.spieler.isEmpty() && !mannschaft.spieler.isEmpty()) {
			System.out.println(this.name + " " + this.tore + " - " + mannschaft.tore + " " + mannschaft.name);
			ballbesitzAnzeigen(mannschaft);
			this.energieAnzeigen();
			mannschaft.energieAnzeigen();
		} else {
			System.out.println("\nBitte legen Sie zunächst beide Mannschaften an.");
		}
	}
	
	/**
	 * Beim Aufrufen dieser Methode werden alle Spieler auf ihre Inizialisierte Position gestellt.
	 */
	
	public void standardAufstellung() {
		Collection<Roboter> values = spieler.values();
			for(Roboter r : values) {
				r.setX(r.getInitialX());
				r.setY(r.getInitialY());
			}
	}
}
