package b_entitaeten;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class Mannschaft implements Serializable {

	private String name;
	private int tore = 0;
	private Tor tor;
	private boolean ballBesitz = false;
	private HashMap<String, Roboter> spieler;

	/**
     * Gibt den Namen der Mannschaft zurück.
     *
     * @return Der Name der Mannschaft
     */
	public String getName() {
		return name;
	}

	/**
     * Legt den Namen der Mannschaft fest.
     *
     * @param name 
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * Gibt die Anzahl der Tore zurück, die diese Mannschaft erzielt hat.
     *
     * @return Anzahl der Tore
     */
	public int getTore() {
		return tore;
	}

	/**
     * Legt die Anzahl der erzielten Tore fest.
     *
     * @param tore 
     */
	public void setTore(int tore) {
		this.tore = tore;
	}
	
	/**
     * Gibt alle Spieler der Mannschaft als HashMap zurück.
     *
     * @return Spieler-HashMap 
     */
	public HashMap<String, Roboter> getSpieler() {
		return spieler;
	}

	/**
     * Setzt die Spieler der Mannschaft.
     *
     * @param spieler 
     */
	public void setSpieler(HashMap<String, Roboter> spieler) {
		this.spieler = spieler;
	}
	
	/**
	 * Erzeugt eine Instanz der Klasse Mannschaft.
	 * 
	 * @param spieler
	 * @param tor
	 */
	public Mannschaft(HashMap<String, Roboter> spieler, Tor tor) {
		this.setSpieler(spieler);
		this.tor = tor;
	}

	protected void anlegen(String[] praefix) {
		this.setName(RoboterHelper.erfrageNamen("\nName der Mannschaft: "));
		this.getSpieler().put("Stürmer", new Stuermer(praefix[0] + " " + RoboterHelper.erfrageNamen("Name des Stürmers: ")));
		this.getSpieler().put("Mittelfeldspieler",
				new Mittelfeldspieler(praefix[1] + " " + RoboterHelper.erfrageNamen("Name des ersten Mittelfeldspielers: ")));
		this.getSpieler().put("Mittelfeldspieler2",
				new Mittelfeldspieler(praefix[2] + " " + RoboterHelper.erfrageNamen("Name des zweiten Mittelfeldspielers: ")));
		this.getSpieler().put("Verteidiger", new Verteidiger(praefix[3] + " " + RoboterHelper.erfrageNamen("Name des Verteidigers: ")));
		this.getSpieler().put("Torwart", new Torwart(praefix[4] + " " + RoboterHelper.erfrageNamen("Name des Torwarts: ")));
	}
	
	/**
     * Gibt einen Text zurück, ob die Mannschaft gerade Ballbesitz hat und welcher Spieler.
     *
     * @param ball Der Spielball
     * @return String mit Ballbesitz-Status
     */
	public String druckeBallbesitz(Ball ball) {
		for(Roboter s : this.getSpieler().values()) {
			if(s.getBallbesitz()) {
				this.ballBesitz = true;
				ball.setZeile(s.getZeile());
				ball.setSpalte(s.getSpalte());
				return s.getName() + " (" + this.getName() + ") hat aktuell Ballbesitz.";
			}
		}
		this.ballBesitz = false;
		return this.getName() + " hat aktuell keinen Ballbesitz.";
	}
	
	/**
     * Gibt an, ob ein Spieler dieser Mannschaft aktuell Ballbesitz hat.
     * Setzt gleichzeitig die Position des Balls.
     *
     * @param ball 
     * @return true, wenn ein Spieler Ballbesitz hat
     */
	public boolean pruefeBallbesitz(Ball ball) {
		for(Roboter s : this.getSpieler().values()) {
			if(s.getBallbesitz()) {
				this.ballBesitz = true;
				ball.setZeile(s.getZeile());
				ball.setSpalte(s.getSpalte());
				return true;
			}
		}
		this.ballBesitz = false;
		return false;
	}
	
	/**
     * Gibt den Spieler dieser Mannschaft zurück, der den Ball besitzt.
     *
     * @param ball 
     * @return Roboter mit Ballbesitz oder null
     */
	public Roboter holeSpielerBallbesitz(Ball ball) {
		for(Roboter s : this.getSpieler().values()) {
			if(s.getBallbesitz()) {
				this.ballBesitz = true;
				ball.setZeile(s.getZeile());
				ball.setSpalte(s.getSpalte());
				return s;
			}
		}
		this.ballBesitz = false;
		return null;
	}

	private void energieAnzeigen() {
		System.out.println("\nEnergiebericht von " + this.getName());
		for (Roboter r : this.getSpieler().values()) {
			System.out.println(r.getName() + " hat noch " + r.getEnergie() + " Energie.");
		}
	}

	/**
     * Gibt den aktuellen Spielstand sowie Ballbesitz und Energie aller Spieler beider Mannschaften aus.
     *
     * @param ball 
     * @param mannschaft 
     */
	public void gegenueberstellen(Ball ball, Mannschaft mannschaft) {
		if (!this.getSpieler().isEmpty() && !mannschaft.getSpieler().isEmpty()) {
			System.out.println("\n" + this.getName() + " " + this.getTore() + " - " + mannschaft.getTore() + " " + mannschaft.getName());
			System.out.println(this.druckeBallbesitz(ball));
			System.out.println(mannschaft.druckeBallbesitz(ball));
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
		Collection<Roboter> values = getSpieler().values();
		for (Roboter r : values) {
			r.setZeile(r.getInitialZeile());
			r.setSpalte(r.getInitialSpalte());
		}
	}

	/**
     * Ermöglicht die Auswahl und Zuweisung einer Aufstellung (1-1-1-1, 1-3, 2-2, 3-1, 4).
     * Die Aufstellung hängt von der Spielfeldhälfte ab (Heim oder Gast).
     *
     * @param istHeimmannschaft 
     */
	public void aufstellungWaehlen(boolean istHeimmannschaft) {
		this.getSpieler().get("Torwart").setInitialZeile(this.tor.getZeile());
		if (istHeimmannschaft) {
			this.getSpieler().get("Torwart").setInitialSpalte(this.tor.getSpalte() + 3);
		} else {
			this.getSpieler().get("Torwart").setInitialSpalte(this.tor.getSpalte() - 3);
		}
		System.out.println("\nWählen Sie bitte eine initiale Aufstellung für " + this.getName() + ".");
		System.out.println("0. 1-1-1-1" + "\n1. 1-3" + "\n2. 2-2" + "\n3. 3-1" + "\n4. 4");
		switch (LogikHelper.menuEingabe(5)) {
		case 0:
			int abstandSpieler = 7;
			int faktor = 1;
			for (Roboter s : this.getSpieler().values()) {
				if (s != this.getSpieler().get("Torwart")) {
					s.setInitialZeile(Spielfeld.mittelpunktZeile);
					if (this.getSpieler().get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {
						s.setInitialSpalte(Spielfeld.mittelpunktSpalte - abstandSpieler * faktor);
						faktor++;
					} else if (this.getSpieler().get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
						s.setInitialSpalte(Spielfeld.mittelpunktSpalte + abstandSpieler * faktor);
						faktor++;
					}
				}
			}
			faktor = 0;
			break;
		case 1:
			for (Roboter s : this.getSpieler().values()) {
				if (s != this.getSpieler().get("Torwart")) {
					
					if (this.getSpieler().get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
						this.getSpieler().get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte + 30);
						this.getSpieler().get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile);
						
						if (s != this.getSpieler().get("Verteidiger")) {
							
							s.setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
						}
						this.getSpieler().get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
						this.getSpieler().get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
						this.getSpieler().get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile);

					}
					if (this.getSpieler().get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {
						this.getSpieler().get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte - 30);
						this.getSpieler().get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile);
						if (s != this.getSpieler().get("Verteidiger")) {
							s.setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
						}
						this.getSpieler().get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
						this.getSpieler().get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
						this.getSpieler().get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile);
					}
				}
			}
			break;
		case 2:
			for (Roboter s : this.getSpieler().values()) {
				if (s != this.getSpieler().get("Torwart")) {

					

						if (this.getSpieler().get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
							
							this.getSpieler().get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
							this.getSpieler().get("Mittelfeldspieler").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
							this.getSpieler().get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
							this.getSpieler().get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);

							this.getSpieler().get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.getSpieler().get("Mittelfeldspieler2").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.getSpieler().get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
							this.getSpieler().get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);

						}

							if (this.getSpieler().get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {
								
								this.getSpieler().get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
								this.getSpieler().get("Mittelfeldspieler")
										.setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
								this.getSpieler().get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
								this.getSpieler().get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);

								this.getSpieler().get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.getSpieler().get("Mittelfeldspieler2")
										.setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.getSpieler().get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
								this.getSpieler().get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
							}
						
					
				}
			}
			break;
		case 3:
			for (Roboter s : this.getSpieler().values()) {
				if (s != this.getSpieler().get("Torwart")) {

					if (this.getSpieler().get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
							
						this.getSpieler().get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
						this.getSpieler().get("Mittelfeldspieler").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
						this.getSpieler().get("Mittelfeldspieler2").setInitialSpalte(Spielfeld.mittelpunktSpalte + 20);
						this.getSpieler().get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile);
						this.getSpieler().get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
						this.getSpieler().get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);

						this.getSpieler().get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte + 4);
						this.getSpieler().get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile);

						}

						if (this.getSpieler().get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {

							this.getSpieler().get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
							this.getSpieler().get("Mittelfeldspieler").setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
							this.getSpieler().get("Mittelfeldspieler2").setInitialSpalte(Spielfeld.mittelpunktSpalte - 20);
							this.getSpieler().get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile);
							this.getSpieler().get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile - 4);
							this.getSpieler().get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 4);

							this.getSpieler().get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte - 4);
							this.getSpieler().get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile);
							
						
					}
				}
			}
			break;
		case 4:
			for (Roboter s : this.getSpieler().values()) {
				if (s != this.getSpieler().get("Torwart")) {
					
						if (this.getSpieler().get("Torwart").getInitialSpalte() > Spielfeld.mittelpunktSpalte) {
							
							this.getSpieler().get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.getSpieler().get("Mittelfeldspieler").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.getSpieler().get("Mittelfeldspieler2").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);
							this.getSpieler().get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte + 10);

							this.getSpieler().get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile -4);
							this.getSpieler().get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
							this.getSpieler().get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 1);
							this.getSpieler().get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile - 1);

						}
						
						if (this.getSpieler().get("Torwart").getInitialSpalte() < Spielfeld.mittelpunktSpalte) {
								
								this.getSpieler().get("Verteidiger").setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.getSpieler().get("Mittelfeldspieler").setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.getSpieler().get("Mittelfeldspieler2").setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);
								this.getSpieler().get("Stürmer").setInitialSpalte(Spielfeld.mittelpunktSpalte - 10);

								this.getSpieler().get("Verteidiger").setInitialZeile(Spielfeld.mittelpunktZeile -4);
								this.getSpieler().get("Mittelfeldspieler").setInitialZeile(Spielfeld.mittelpunktZeile + 4);
								this.getSpieler().get("Mittelfeldspieler2").setInitialZeile(Spielfeld.mittelpunktZeile + 1);
								this.getSpieler().get("Stürmer").setInitialZeile(Spielfeld.mittelpunktZeile - 1);	
							}
				
					break;
				}
			}
		}
	}
}
