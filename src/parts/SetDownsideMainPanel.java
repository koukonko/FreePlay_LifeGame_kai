package parts;

import javax.swing.JFrame;
import javax.swing.JPanel;

import listener.ButtonListener;

public class SetDownsideMainPanel extends JPanel{
	ButtonListener bl = null;

	public SetDownsideMainPanel(JFrame frame, int HEIGHT, int WIDTH, int[][] cell, int radio_num){

		this.setLayout(null);
		this.setBounds(0, HEIGHT - 160, WIDTH, 165);

		SetLabel label = new SetLabel("00 Hour 00 Min 00 Sec", 500, 28, 10, 0);

		bl = new ButtonListener(frame, label, cell);

		// String text, int width, int height, int x, int y
		SetButton start = new SetButton("Start", 80, 28, WIDTH - 120, 0);
		start.addActionListener(bl);

		SetButton stop = new SetButton("Stop", 80, 28, WIDTH - 240, 0);
		stop.addActionListener(bl);

		SetButton reset = new SetButton("Reset", 80, 28, WIDTH - 360, 0);
		reset.addActionListener(bl);

		SetButton timeReset = new SetButton("TimeReset", 125, 28, WIDTH - 525, 0);
		timeReset.addActionListener(bl);

		SetButton colorChange = new SetButton("ColorChange", 125, 28, WIDTH - 685, 0);
		colorChange.addActionListener(bl);

		this.add(start);
		this.add(stop);
		this.add(reset);
		this.add(timeReset);
		this.add(label);
		this.add(colorChange);

		//this.add(new SetSampleList(cell, radio_num));
	}

	public boolean getColorChange(){
		return bl.getColorChange();
	}
}
