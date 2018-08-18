package listener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ClickListener implements MouseListener, MouseMotionListener {

	private int[][] cell;
	private Graphics g;
	// マウス押下時のセルの状態を保持
	private int mouse_point_cell = 0;

	// X, Y軸の調整用
	// 33 = X軸のフレーム + 25 , 56 = Y 軸のフレーム + 25
	private final int X_ADJUST = 33;
	private final int Y_ADJUST = 56;

	// セルの大きさ
	private final int CELL_SIZE = 5;

	public ClickListener(Graphics g, int[][] cell) {
		this.g = g;
		this.cell = cell;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// System.out.println("ENTERED");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

		// System.out.println("EXITED");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// マウス押下時のX, Y軸の位置を取得
		int x = arg0.getX();
		int y = arg0.getY();

		// System.out.println(": " + x + " " + y);
		// System.out.println("- " + (x - 33) + " " + (y - 56));
		// System.out.println("$ " + ((x - 33) / 5) + " " + ((y - 56) / 5));
		// System.out.println("* " + (((x - 33) / 5) * 5 + 33) + " " + (((y -
		// 56) / 5 * 5) + 56));

		// X軸調整後
		int x_adjust_ed = x - X_ADJUST;
		// Y軸調整後
		int y_adjust_ed = y - Y_ADJUST;

		// X軸調整後をセルの大きさで割る
		int x_adjust_ed_cell_devide = x_adjust_ed / CELL_SIZE;
		// Y軸調整後をセルの大きさで割る
		int y_adjust_ed_cell_devide = y_adjust_ed / CELL_SIZE;

		/*
		 * マウスの押下ポイントが範囲内であるか
		 *
		 * x軸のマウス押下ポイント < x軸のセルの最大値 &&
		 * y軸のマウス押下ポイント < y軸のセルの最大値 &&
		 * x軸のマウス押下ポイント >= 0 &&
		 * y軸のマウス押下ポイント >= 0
		 */
		if (x_adjust_ed_cell_devide < cell[0].length && y_adjust_ed_cell_devide < cell.length && x_adjust_ed_cell_devide >= 0 && y_adjust_ed_cell_devide >= 0) {

			// マウス押下されたセルの生死(色の切り替え)
			if (cell[y_adjust_ed_cell_devide][x_adjust_ed_cell_devide] == 0) {
				// セルが「死」の場合
				// 黒に設定(色の反転)
				g.setColor(Color.black);
			} else {
				// セルが「生」の場合
				// 白に設定(色の反転)
				g.setColor(Color.white);
			}
			// 四角形を描写(セルの色塗)
			g.fillRect((x_adjust_ed_cell_devide * 5 + 25 + 1), ((y_adjust_ed_cell_devide * 5) + 25 + 1), 3, 3);
			// cell[(y - 56) / 5][(x - 33) / 5] = cell[(y - 56) / 5][(x - 33) /
			// 5] == 0 ? 1 : 0;

			// マウス押下されたセルの生死(生死の切り替え)
			if (cell[y_adjust_ed_cell_devide][x_adjust_ed_cell_devide] == 0) {
				// セルが「死」の場合
				// セルを「生」に設定
				cell[y_adjust_ed_cell_devide][x_adjust_ed_cell_devide] = 1;
			} else if (cell[y_adjust_ed_cell_devide][x_adjust_ed_cell_devide] == 1) {
				// セルが「生」の場合
				// セルを「死」に設定
				cell[y_adjust_ed_cell_devide][x_adjust_ed_cell_devide] = 0;
			}
			System.out.println("y: " + (y_adjust_ed_cell_devide + 1) + " x:" + (x_adjust_ed_cell_devide + 1));

			// 現在押下されているセルの生死を保持する
			mouse_point_cell = cell[y_adjust_ed_cell_devide][x_adjust_ed_cell_devide];
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		// System.out.println("RELEASED");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// ドラッグ時のX, Y軸の位置を取得
		int x = e.getX();
		int y = e.getY();
		// X軸調整後
		int x_adjust_ed = x - X_ADJUST;
		// Y軸調整後
		int y_adjust_ed = y - Y_ADJUST;

		// X軸調整後をセルの大きさで割る
		int x_adjust_ed_cell_devide = x_adjust_ed / CELL_SIZE;
		// Y軸調整後をセルの大きさで割る
		int y_adjust_ed_cell_devide = y_adjust_ed / CELL_SIZE;

		/*
		 * マウスの押下ポイントが範囲内であるか
		 *
		 * x軸のマウス押下ポイント < x軸のセルの最大値 &&
		 * y軸のマウス押下ポイント < y軸のセルの最大値 &&
		 * x軸のマウス押下ポイント >= 0 &&
		 * y軸のマウス押下ポイント >= 0
		 */
		if (x_adjust_ed_cell_devide < cell[0].length && y_adjust_ed_cell_devide < cell.length && x_adjust_ed_cell_devide >= 0 && y_adjust_ed_cell_devide >= 0) {
			// 四角形を描写(セルの色塗)
			g.fillRect((x_adjust_ed_cell_devide * 5 + 25 + 1), ((y_adjust_ed_cell_devide * 5) + 25 + 1), 3, 3);

			// マウスが押下されたときのセルの生死状態をドラッグされたセルにも反映
			cell[y_adjust_ed_cell_devide][x_adjust_ed_cell_devide] = mouse_point_cell;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
