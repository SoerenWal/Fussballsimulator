package b_entitaeten;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Mannschaft implements Serializable {

	public String name;
	public int tore = 0;
	public Tor tor;
	public boolean ballBesitz = false;
	public HashMap<String, Roboter> spieler;
	static Scanner sc = new Scanner(System.in);

	/**
	 * Erzeugt eine Instanz der Klasse Mannschaft.
	 * 
	 * @param spieler
	 * @param tor
	 */
	public Mannschaft(HashMap<String, Roboter> spieler, Tor tor) {
		this.spieler = spieler;
		this.tor = tor;
	}

	protected void anlegen() {
		this.name = RoboterHelper.erfrageNamen("\nName der Mannschaft: ");
		this.spieler.put("Stürmer", new Stuermer(RoboterHelper.erfrageNamen("Name des Stürmers: ")));
		this.spieler.put("Mittelfeldspieler",
				new Mittelfeldspieler(RoboterHelper.erfrageNamen("Name des ersten Mittelfeldspielers: ")));
		this.spieler.put("Mittelfeldspieler2",
				new Mittelfeldspieler(RoboterHelper.erfrageNamen("Name des zweiten Mittelfeldspielers: ")));
		this.spieler.put("Verteidiger", new Verteidiger(RoboterHelper.erfrageNamen("Name des Verteidigers: ")));
		this.spieler.put("Torwart", new Torwart(RoboterHelper.erfrageNamen("Name des Torwarts: ")));
	}
	
	public String pruefeBallbesitz(Ball ball) {
		for(Roboter s : this.spieler.values()) {
			if(s.pruefeBallbesitz()) {
				this.ballBesitz = true;
				ball.zeile = s.getZeile();
				ball.spalte = s.getSpalte();
				return s.getName() + " (" + this.name + ") hat aktuell Ballbesitz.";
			}
		}
		this.ballBesitz = false;
		return this.name + " hat aktuell keinen Ballbesitz.";
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
	 * Beim Aufrufen dieser Methode werden alle Spieler auf ihre inizialisierte
	 * Position gestellt.
	 */
	public void aufstellen() {
		Collection<Roboter> values = spieler.values();
		for (Roboter r : values) {
			r.setZeile(r.getInitialZeile());
			r.setSpalte(r.getInitialSpalte());
		}
	}

	public void aufstellungWaehlen(boolean istHeimmannschaft) {
		this.spieler.get("Torwart").setInitialZeile(this.tor.zeile);
		if (istHeimmannschaft) {
			this.spieler.get("Torwart").setInitialSpalte(tor.spalte + 3);
		} else {
			this.spieler.get("Torwart").setInitialSpalte(tor.spalte - 3);
		}
		System.out.println("\nWählen Sie bitte eine initiale Aufstellung für " + this.name + ".");
		System.out.println("0. 1-1-1-1" + "\n1. 1-3" + "\n2. 2-2" + "\n3. 3-1" + "\n4. 4");
		switch (LogikHelper.menuEingabe(5)) {
		case 0:
			int abstandSpieler = 7;
			int faktor = 1;
			for (Roboter s : this.spieler.values()) {
				if (s != this.spieler.get("Torwart")) {
					s.setInitialZeile(Spielfeld.mittelpunktZeile);
					if (this.spieler.get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {
						s.setInitialSpalte(Spielfeld.mittelpunktSpalte - abstandSpieler * faktor);
						faktor++;
					} else if (this.spieler.get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
						s.setInitialSpalte(Spielfeld.mittelpunktSpalte + abstandSpieler * faktor);
						faktor++;
					}
				}
			}
			faktor = 0;
			break;
		case 1:
			for (Roboter s : this.spieler.values()) {
				if (s != this.spieler.get("Torwart")) {
					if (this.spieler.get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
						this.spieler.get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte + 30);
						this.spieler.get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile);
						if (s != this.spieler.get("Verteidiger")) {
							s.setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
						}
						this.spieler.get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 3);
						this.spieler.get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 3);
						this.spieler.get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile);

					}
					if (this.spieler.get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {
						this.spieler.get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte - 30);
						this.spieler.get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile);
						if (s != this.spieler.get("Verteidiger")) {
							s.setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
						}
						this.spieler.get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 3);
						this.spieler.get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 3);
						this.spieler.get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile);
					}
				}
			}
			break;
		case 2:
			for (Roboter s : this.spieler.values()) {
				if (s != this.spieler.get("Torwart")) {

					if (this.spieler.get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {

						if (this.spieler.get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
							this.spieler.get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
							this.spieler.get("Mittelfeldspieler").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
							this.spieler.get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
							this.spieler.get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);

							this.spieler.get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.spieler.get("Mittelfeldspieler2").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.spieler.get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
							this.spieler.get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);

						}

						if (this.spieler.get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {

							if (this.spieler.get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {
								this.spieler.get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
								this.spieler.get("Mittelfeldspieler")
										.setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
								this.spieler.get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
								this.spieler.get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);

								this.spieler.get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.spieler.get("Mittelfeldspieler2")
										.setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.spieler.get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
								this.spieler.get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
							}
						}
					}
				}
			}
			break;
		case 3:
			for (Roboter s : this.spieler.values()) {
				if (s != this.spieler.get("Torwart")) {

					if (this.spieler.get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {

						if (this.spieler.get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
							this.spieler.get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
							this.spieler.get("Mittelfeldspieler").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
							this.spieler.get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
							this.spieler.get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);

							this.spieler.get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.spieler.get("Mittelfeldspieler2").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.spieler.get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
							this.spieler.get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
						}

						if (this.spieler.get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {

							if (this.spieler.get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {
								this.spieler.get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
								this.spieler.get("Mittelfeldspieler")
										.setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
								this.spieler.get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
								this.spieler.get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);

								this.spieler.get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.spieler.get("Mittelfeldspieler2")
										.setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.spieler.get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
								this.spieler.get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
							}
						}
					}
				}
			}
			break;
		case 4:
			for (Roboter s : this.spieler.values()) {
				if (s != this.spieler.get("Torwart")) {

					if (this.spieler.get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {

						if (this.spieler.get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
							this.spieler.get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
							this.spieler.get("Mittelfeldspieler").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
							this.spieler.get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
							this.spieler.get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);

							this.spieler.get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.spieler.get("Mittelfeldspieler2").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.spieler.get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
							this.spieler.get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);

						}

						if (this.spieler.get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {

							if (this.spieler.get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {
								this.spieler.get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
								this.spieler.get("Mittelfeldspieler")
										.setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
								this.spieler.get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
								this.spieler.get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);

								this.spieler.get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.spieler.get("Mittelfeldspieler2")
										.setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.spieler.get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
								this.spieler.get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
							}
						}
					}
					break;
				}
			}
		}
	}
}
