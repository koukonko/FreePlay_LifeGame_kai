package parts;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import listener.RadioButtonListener;

public class SetSampleList extends JScrollPane{
	public SetSampleList(int[][] cell, int radio_num){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 32, 500, 35);

		ButtonGroup group = new ButtonGroup();
		JRadioButton[] radio_box = new JRadioButton[15];
		RadioButtonListener listener = new RadioButtonListener(cell, radio_num);

		for(int i = 0; i < radio_box.length - 1; ++i){
			//System.out.println("width:" + 100 + " height:" + 28 + " x:" + (100 * (i % 5) + 10 + " y:" + (10 * (i / 5) + 5)));
			radio_box[i] = new SetRadioButton("Sample " + (i + 1), 100, 28, 100 * (i % 5) + 10, 28 * (i / 5));
			radio_box[i].setActionCommand("" + i);
			radio_box[i].addActionListener(listener);
			group.add(radio_box[i]);
			panel.add(radio_box[i]);
		}
		radio_box[radio_box.length - 1] = new SetRadioButton("None", 100, 28, 100 * ((radio_box.length - 1) % 5) + 10, 28 * ((radio_box.length - 1) / 5));
		radio_box[radio_box.length - 1].setSelected(true);
		radio_box[radio_box.length - 1].setActionCommand("None");
		radio_box[radio_box.length - 1].addActionListener(listener);
		group.add(radio_box[radio_box.length - 1]);
		panel.add(radio_box[radio_box.length - 1]);

		// this.setBackground(Color.RED);
		this.setBounds(10, 32, 550, 80);
		this.setViewportView(panel);
	}
}
