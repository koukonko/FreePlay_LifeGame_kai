package time;

import java.util.TimerTask;

import javax.swing.JLabel;

public class CountTime extends TimerTask {

	private int hour;
	private int min;
	private int sec;
	private JLabel label;
	private boolean start_flag;

	public CountTime(JLabel label) {
		hour = 0;
		min = 0;
		sec = 0;
		this.label = label;
		start_flag = true;
	}

	@Override
	public void run() {
		// System.out.println(start_flag);
		if (start_flag) {
			++sec;
			try {
				calc();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void calc() throws InterruptedException {
		if (sec >= 60) {
			min++;
			sec = 0;
			if (min >= 60) {
				hour++;
				min = 0;
			}
		}

		String strHour = (hour < 10) ? "0" + hour : hour + "";
		String strMin = (min < 10) ? "0" + min : min + "";
		String strSec = (sec < 10) ? "0" + sec : sec + "";

		label.setText(strHour + " Hour " + strMin + " Min " + strSec + " Sec");

	}

	public void setStartFlag(boolean start_flag) {
		this.start_flag = start_flag;
	}

	public void reset() {
		hour = 0;
		min = 0;
		sec = 0;

		label.setText("00 Hour 00 Min 00 Sec");
	}

}
