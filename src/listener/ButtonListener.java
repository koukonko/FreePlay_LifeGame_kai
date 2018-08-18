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

	// タイマーの設定
	private Timer countTimer, lifeGameTimer;
	private CountTime countTime;
	private LifeGameTime lifeGameTime;

	// セルの色をランダムにするか
	private boolean flag_color_change;

	// タイマーの遅延
	private final int TASK_DERAY = 150;
	// タイマーの繰り返し時間
	private final int TASK_PERIOD = 100;

	public ButtonListener(JFrame frame, JLabel label, int[][] cell) {
		this.cell = cell;
		this.frame = frame;

		// 時間計測用のタイマー
		countTimer = new Timer();
		countTime = new CountTime(label);
		countTime.setStartFlag(false);
		countTimer.schedule(countTime, TASK_DERAY, TASK_PERIOD);

		// セルの実行用のタイマー
		lifeGameTimer = new Timer();
		lifeGameTime = new LifeGameTime(cell, frame);
		lifeGameTime.setStartFlag(false);
		lifeGameTimer.schedule(lifeGameTime, TASK_DERAY, TASK_PERIOD);

		// カラーの変更フラグを初期化
		flag_color_change = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ボタンのイベントのテキストを取得
		String code = ((JButton) e.getSource()).getText();
		// System.out.println(code);

		// 押下されたボタンの評価
		if (code.equals("Start")) {

			// Start が押下された
			// ライフゲームタイマーの開始
			countTime.setStartFlag(true);
			lifeGameTime.setStartFlag(true);

		} else if (code.equals("Stop")) {

			// Stop が押下された
			// ライフゲームタイマーの停止
			countTime.setStartFlag(false);
			lifeGameTime.setStartFlag(false);

		} else if (code.equals("Reset")) {

			// Reset が押下された
			// 全てのセルの初期化
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
			// 再描画
			frame.update(null);

		} else if (code.equals("TimeReset")) {
			// TimeReset が押下された
			// 時間をリセットする
			countTime.reset();

		} else if (code.equals("ColorChange")){
			// ColorChange が押下された
			// フラグを反転させる
			flag_color_change = !flag_color_change;
		}

	}

	public boolean getColorChange(){
		return flag_color_change;
	}

}
