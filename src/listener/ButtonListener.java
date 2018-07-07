package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import time.CountTime;
import time.LifeGameTime;

public class ButtonListener implements ActionListener {

	private int[][] cell;
	private JFrame frame;
	private Timer countTimer, lifeGameTimer;
	private CountTime countTime;
	private LifeGameTime lifeGameTime;
	private boolean flag_color_change;

	public ButtonListener(JFrame frame, JLabel label, int[][] cell) {
		this.cell = cell;
		this.frame = frame;

		countTimer = new Timer();
		countTime = new CountTime(label);
		countTime.setStartFlag(false);
		countTimer.schedule(countTime, 100, 150);

		lifeGameTimer = new Timer();
		lifeGameTime = new LifeGameTime(cell, frame);
		lifeGameTime.setStartFlag(false);
		lifeGameTimer.schedule(lifeGameTime, 100, 150);

		flag_color_change = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = ((JButton) e.getSource()).getText();
		// System.out.println(code);

		if (code.equals("Start")) {

			countTime.setStartFlag(true);
			lifeGameTime.setStartFlag(true);

		} else if (code.equals("Stop")) {

			countTime.setStartFlag(false);
			lifeGameTime.setStartFlag(false);

		} else if (code.equals("Reset")) {

			for (int i = 0; i < cell.length; ++i) {
				for (int j = 0; j < cell[i].length; ++j) {
					cell[i][j] = 0;
				}
			}
			/*
			int centerHeight = cell.length / 2;
			int centerWidth = cell[0].length / 2;
			PointSetLogic psl = new PointSetLogic();
			cell = psl.circleLeft(centerHeight - 0, centerWidth + 1, 4, cell);
			cell = psl.circleRight(centerHeight + 0, centerWidth + 1, 4, cell);

			cell = psl.circleRight(centerHeight + 8, centerWidth + 5, 4, cell);
			cell = psl.circleLeft(centerHeight - 8, centerWidth - 3, 4, cell);

			for(int i = centerHeight - 13, j = centerWidth + 13; i < centerHeight + 14; ++i,--j){
					cell[i][j] = 1;
			}
			*/
			frame.update(null);
		} else if (code.equals("TimeReset")) {

			countTime.reset();

		} else if (code.equals("ColorChange")){
			flag_color_change = !flag_color_change;
		}

	}

	public boolean getColorChange(){
		return flag_color_change;
	}

}
