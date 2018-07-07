package paint;

import java.awt.Color;
import java.awt.Graphics;

public class PaintCell {

	private Graphics g;
	private int[][] cell;
	private boolean flag_color_change;

	public PaintCell(Graphics g, int[][] cell, boolean flag_color_change) {
		this.g = g;
		this.cell = cell;
		this.flag_color_change = flag_color_change;

		paint();
	}

	public void paint() {

		// Random rnd = new Random();
		// Color rndColor[] = {Color.black, Color.gray, Color.lightGray};
		// Color rndColor[] = {Color.black, Color.gray, Color.lightGray,
		// Color.green, Color.blue, Color.red, Color.magenta, Color.yellow,
		// Color.cyan};

		for (int i = 0; i < cell.length; ++i) {
			for (int j = 0; j < cell[i].length; ++j) {
				if (cell[i][j] == 0) {
					// dead
					g.setColor(Color.white);
				} else if (cell[i][j] == 1) {
					// alive
					// g.setColor(rndColor[rnd.nextInt(rndColor.length)]);
					g.setColor(Color.black);
				} else if (cell[i][j] == 2){
					// killer
					if(flag_color_change){
						g.setColor(Color.RED);
					} else {
						g.setColor(Color.white);
					}
				}
				g.fillRect(j * 5 + 25 + 1, i * 5 + 25 + 1, 3, 3);

			}
		}
	}

}
