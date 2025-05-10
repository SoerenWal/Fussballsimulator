package a__gui;
import javax.swing.*;

import b_entitaeten.Spielfeld;
import b_schiedsrichter.Schiedsrichter;

import java.awt.*;

public class Fußballfeld2 extends JPanel {
	
	Schiedsrichter schiedsrichter = new Schiedsrichter();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Set background color
		g2d.setColor(new Color(34, 139, 34)); // Green color for the field
		g2d.fillRect(0, 0, getWidth(), getHeight());

		// Draw the outer boundary
		g2d.setColor(Color.WHITE);
		g2d.drawRect(Spielfeld.x, Spielfeld.y, Spielfeld.width, Spielfeld.height); // 50 50 700 400

		// Draw the center circle
		g2d.drawOval(350, 200, 100, 100);

		// Draw the center line
		g2d.drawLine(400, 50, 400, 450);

		// Draw the penalty areas
		g2d.drawRect(50, 150, 100, 200);
		g2d.drawRect(650, 150, 100, 200);

		// Draw the goals
		g2d.drawRect(50, 225, 20, 50);
		g2d.drawRect(730, 225, 20, 50);

		// Draw the penalty spots
		g2d.fillOval(130, 245, 10, 10);
		g2d.fillOval(660, 245, 10, 10);

		// Draw the center spot
		g2d.fillOval(395, 245, 10, 10);
		
		// Draw the ball
		g2d.setColor(Color.BLACK);
		g2d.fillOval(/*schiedsrichter.ball.getX()*/396, /*schiedsrichter.ball.getY()*/246, 8, 8); // Ball muss als Attribut dem Schiedsrichter zugeordnet werden
	}

	public static void fußballfeldAnzeigen() {
		JFrame frame = new JFrame("Fußballfeld");
		Fußballfeld2 fußballfeld = new Fußballfeld2();
		frame.add(fußballfeld);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
