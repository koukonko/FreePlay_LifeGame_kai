package paint;

import java.awt.Color;
import java.awt.Graphics;

public class PaintLine {
	private Graphics g;
	private int width;
	private int height;

	public PaintLine(Graphics g, int width, int height) {
		this.g = g;
		this.width = width;
		this.height = height;

		paint();
	}

	private void paint() {
		g.setColor(Color.LIGHT_GRAY);
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		for (int i = 0; i < (height - 110) / 5; ++i) {
			g.drawLine(25, i * 5 + 25, width - 40, i * 5 + 25);
		}
		for (int i = 0; i < (width - 60) / 5; ++i) {
			g.drawLine(i * 5 + 25, 25, i * 5 + 25, height - 90);
		}
	}
}
