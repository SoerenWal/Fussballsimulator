package a_gui2;

import javax.swing.*;

import b_entitaeten.*;
import java.awt.*;

public class Fußballfeld extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Hintergrundfarbe setzen
		g2d.setColor(new Color(34, 139, 34)); // Grüne Farbe für das Spielfeld
		g2d.fillRect(0, 0, getWidth(), getHeight());

		// Außenlinie malen
		g2d.setColor(Color.WHITE);
		g2d.drawRect(Spielfeld.außenlinie_x, Spielfeld.außenlinie_y, Spielfeld.außenlinie_width,
				Spielfeld.außenlinie_height);

		// Mittelpunkt malen
		g2d.drawOval(Spielfeld.mittelpunkt_x, Spielfeld.mittelpunkt_y, Spielfeld.mittelpunkt_durchmesser,
				Spielfeld.mittelpunkt_durchmesser);

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

		// Ball malen
		g2d.setColor(Color.BLACK);
		g2d.fillOval(Nutzer.schiedsrichter.ball.getX(), Nutzer.schiedsrichter.ball.getY(),
				Nutzer.schiedsrichter.ball.getDurchmesser(), Nutzer.schiedsrichter.ball.getDurchmesser());
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
