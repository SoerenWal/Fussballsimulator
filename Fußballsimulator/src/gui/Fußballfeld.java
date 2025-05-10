package gui;
import javax.swing.*;

import entitaeten.Spielfeld;

import java.awt.*;

public class Fußballfeld extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Set background color
		g2d.setColor(new Color(34, 139, 34)); // Green color for the field
		g2d.fillRect(0, 0, getWidth(), getHeight());

		// Draw the outer boundary
		g2d.setColor(Color.WHITE);
		g2d.drawRect(Spielfeld.x, Spielfeld.y, Spielfeld.width, Spielfeld.height);

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
	}

	public static void fußballfeldAnzeigen() {
		JFrame frame = new JFrame("Fußballfeld");
		Fußballfeld fußballfeld = new Fußballfeld();
		frame.add(fußballfeld);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
