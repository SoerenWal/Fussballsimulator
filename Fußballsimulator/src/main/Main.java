package main;

import java.util.*;
import a_gui2.*;
import b_entitaeten.*;
import b_schiedsrichter.*;
import c_datenhaltung.*;

public class Main {



	public static void main(String[] args) {
		
		Ball ball = Ball.getInstance(396, 246);
		Schiedsrichter schiedsrichter = new Schiedsrichter(ball); // Schiedsrichter noch als Singleton implementieren
		
		Fußballfeld.schiedsrichter = schiedsrichter;
		
		Fußballfeld.fußballfeldAnzeigen();
		
		/*Random random = new Random();
		
		double zufallswert = random.nextDouble();
		
		System.out.println(); */
		
		Mittelfeldspieler s = new Mittelfeldspieler("S", 1, 10, 0.5,0.5 , 1);
		Mittelfeldspieler b = new Mittelfeldspieler("b", 1, 10, 0.5,0.5 , 1);
		
		System.out.println(s.getHatBallBesitz());

		
		s.passen(b);
		
		System.out.println(s.getHatBallBesitz());
		System.out.println(b.getHatBallBesitz());
	}

}
