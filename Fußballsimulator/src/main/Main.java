package main;

import java.util.*;
import a_gui2.*;
import b_entitaeten.*;
import b_schiedsrichter.*;

public class Main {



	public static void main(String[] args) {
		Mittelfeldspieler s = new Mittelfeldspieler("S", 1, 10, 0.5,0.5 , 1);
		Mittelfeldspieler b = new Mittelfeldspieler("b", 1, 10, 0.5,0.5 , 1);
		Schiedsrichter schiedsrichter = Schiedsrichter.getInstance(Ball.getInstance(396, 246), s, b);
		schiedsrichter.spieler[0].setBallBesitz(true);
		schiedsrichter.spieler[0].passen(schiedsrichter.spieler[1]);
		
		Fußballfeld.schiedsrichter = schiedsrichter;
		Fußballfeld.fußballfeldAnzeigen();
		
		/*
		Random random = new Random();
		
		double zufallswert = random.nextDouble();
		
		System.out.println();
		
		
		
		System.out.println(s.getHatBallBesitz());

		
		s.passen(b);
		
		System.out.println(s.getHatBallBesitz());
		System.out.println(b.getHatBallBesitz());*/
	}

}
