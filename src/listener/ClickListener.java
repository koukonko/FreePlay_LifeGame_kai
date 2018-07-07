package listener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ClickListener implements MouseListener, MouseMotionListener {

	private int[][] cell;
	private Graphics g;
	private int pos = 0;

	public ClickListener(Graphics g, int[][] cell) {
		this.g = g;
		this.cell = cell;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("ENTERED");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

		//System.out.println("EXITED");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// マウス押下時
		int x = arg0.getX();
		int y = arg0.getY();

		// System.out.println(": " + x + " " + y);
		// System.out.println("- " + (x - 33) + " " + (y - 56));
		// System.out.println("$ " + ((x - 33) / 5) + " " + ((y - 56) / 5));
		// System.out.println("* " + (((x - 33) / 5) * 5 + 33) + " " + (((y - 56) / 5 * 5) + 56));

		// 33 = X軸のフレーム + 25 , 56 = Y 軸のフレーム + 25

		if (((x - 33) / 5) < cell[0].length && ((y - 56) / 5) < cell.length && ((x - 33) / 5) >= 0 && ((y - 56) / 5) >= 0) {
			if (cell[(y - 56) / 5][(x - 33) / 5] == 0) {
				g.setColor(Color.black);
			} else {
				g.setColor(Color.white);
			}
			g.fillRect((((x - 33) / 5) * 5 + 25 + 1), (((y - 56) / 5 * 5) + 25 + 1), 3, 3);
			//cell[(y - 56) / 5][(x - 33) / 5] = cell[(y - 56) / 5][(x - 33) / 5] == 0 ? 1 : 0;
			if(cell[(y - 56) / 5][(x - 33) / 5] == 0){
				cell[(y - 56) / 5][(x - 33) / 5] = 1;
			} else if (cell[(y - 56) / 5][(x - 33) / 5] == 1){
				cell[(y - 56) / 5][(x - 33) / 5] = 0;
			}
			System.out.println("y: " + (((y - 56) / 5) + 1) + " x:" + (((x - 33) / 5) + 1));
			pos = cell[(y - 56) / 5][(x - 33) / 5];
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		//System.out.println("RELEASED");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		// System.out.println(x + " " + y);
		if (((x - 33) / 5) < cell[0].length && ((y - 56) / 5) < cell.length && ((x - 33) / 5) >= 0 && ((y - 56) / 5) >= 0) {
			g.fillRect((((x - 33) / 5) * 5 + 25 + 1), (((y - 56) / 5 * 5) + 25 + 1), 3, 3);
			cell[(y - 56) / 5][(x - 33) / 5] = pos;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
