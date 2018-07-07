package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonListener implements ActionListener{

	private int[][] cell;
	private int radio_num;
	public RadioButtonListener(int[][] cell2, int radio_num) {
		this.cell = cell2;
		this.radio_num = radio_num;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action_command = arg0.getActionCommand();
		//System.out.println(action_command);
		if(action_command.equals("None")){
			radio_num = 999;
		} else {
			radio_num = Integer.parseInt(action_command);
		}
	}
}
