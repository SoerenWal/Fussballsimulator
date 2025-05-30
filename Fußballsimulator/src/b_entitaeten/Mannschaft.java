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

	protected static void heimmannschaftAnlegen(Mannschaft heimmannschaft, Tor tor) {
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
		heimmannschaft.spieler.put("Torwart", new Torwart(name, tor.zeile, tor.spalte + 3));
	}

	protected static void gastmannschaftAnlegen(Mannschaft gastmannschaft, Tor tor) {
		// modularisieren und whitespaces erkennen
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
		gastmannschaft.spieler.put("Torwart", new Torwart(name, tor.zeile, tor.spalte - 3));
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
	 * Beim Aufrufen dieser Methode werden alle Spieler auf ihre Inizialisierte
	 * Position gestellt.
	 */
	public void aufstellen() {
		Collection<Roboter> values = spieler.values();
		for (Roboter r : values) {
			r.setZeile(r.getInitialZeile());
			r.setSpalte(r.getInitialSpalte());
		}
	}

	public void aufstellungWaehlen(Ball ball, Tor[] tore) {
		System.out.println("\nWählen Sie bitte eine initiale Aufstellung für " + this.name + ".");
		System.out.println("0. 1-1-1-1" + "\n1. 1-3" + "\n2. 2-2" + "\n3. 3-1" + "\n4. 4");
		int distanzTorwartZentrum = ball.getSpalte() - this.spieler.get("Torwart").getInitialSpalte();
		int abstandSpieler = 7;
		int abstandBallAußenlinie = ball.getZeile();
		switch (LogikHelper.menuEingabe(5)) {
		case 0:
			int faktor = 1;
			for (Roboter s : this.spieler.values()) {
				if (s != this.spieler.get("Torwart")) {
					s.setInitialZeile(ball.zeile);
					if (this.spieler.get("Torwart").getInitialSpalte() < ball.getSpalte()) {
						s.setInitialSpalte(this.spieler.get("Torwart").getInitialSpalte() + abstandSpieler * faktor);
						faktor++;
					} else if (this.spieler.get("Torwart").getInitialSpalte() > ball.getSpalte()) {
						s.setInitialSpalte(this.spieler.get("Torwart").getInitialSpalte() - abstandSpieler * faktor);
						faktor++;
					}
				}
			}
			faktor = 0;
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}

	}
}
