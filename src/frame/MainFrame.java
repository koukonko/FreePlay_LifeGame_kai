package frame;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import listener.ClickListener;
import paint.PaintCell;
import paint.PaintLine;
import parts.SetDownsideMainPanel;

public class MainFrame extends JFrame {

	final private int WIDTH = 1900; // 1800
	final private int HEIGHT = 1085; // 1085

	private JPanel upside, downside;
	private Graphics g, paintG;

	final private double[][] num_box =
		{
				// LeftUp, CenterUp, RightUp,
				// Left, Center, Right, LeftDown,
				// CenterDown, RightDown, Alive, Dead
				{1, 1, 1,
				 1, 1, 1,
				 1, 1, 1, 2, 3}, // Sample 1
				{1, 1, 1,
				 1, 1, 1,
				 1, 1, 1, 3, 3}, // Sample 2
		};

	private static int radio_num = 0;

	private int[][] cell;

	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		super("GAME OF LIFE");

		System.out.println("HEIGHT - CELL_SIZE : " + ((HEIGHT - 110) / 5));
		System.out.println("WIDHT  - CELL_SIZE : " + ((WIDTH - 60) / 5));
		// 現世代と次世代の二つの配列が必要になると思われる
		cell = new int[(HEIGHT - 200) / 5 - 1][(WIDTH - 60) / 5 - 1];
		for (int i = 0; i < cell.length; ++i) {
			for (int j = 0; j < cell[0].length; ++j) {
				cell[i][j] = 0;
			}
		}


		// 初期配置

		int centerHeight = cell.length / 2;
		int centerWidth = cell[0].length / 2;
		System.out.println("centerHeight:" + (cell.length / 2) + " centerWidth:" + (cell[0].length / 2));
		/*
		for(int i = centerHeight - 7; i < centerHeight + 8; ++i){
			if(i % 2 == 1 || i % 3 == 0){
				cell[i][centerWidth - 13] = true;
				cell[i][centerWidth - 10] = true;
				cell[i][centerWidth - 7] = true;
			}
		}
		for(int i = centerWidth - 17; i < centerWidth - 2; ++i){
			if(i % 2 == 0 || i % 3 == 1){
				cell[centerHeight - 3][i] = true;
				cell[centerHeight][i] = true;
				cell[centerHeight + 3][i] = true;
			}
		}
		//cell[centerHeight][centerWidth] = true;
		//cell[centerHeight][centerWidth - 2] = true;
*/
		/*
		PointSetLogic psl = new PointSetLogic();
		//cell = psl.circle(centerHeight, centerWidth - 1, 7, cell);
		//cell = psl.circle(centerHeight, centerWidth - 1, 5, cell);
		cell = psl.circleLeft(centerHeight - 0, centerWidth + 1, 4, cell);
		cell = psl.circleRight(centerHeight + 0, centerWidth + 1, 4, cell);

		cell = psl.circleRight(centerHeight + 8, centerWidth + 5, 4, cell);
		cell = psl.circleLeft(centerHeight - 8, centerWidth - 3, 4, cell);


		for(int i = centerHeight - 13, j = centerWidth + 13; i < centerHeight + 14; ++i,--j){
				cell[i][j] = 1;
		}
		 */

		// 絶対死の範囲
		//new AbsKiller().killerPuts(cell);
/*
		for(int i = - 4; i < 5; ++i){
				cell[centerHeight + i][centerWidth + i] = true;
				cell[centerHeight + i][centerWidth + i - 4] = true;
				cell[centerHeight + i][centerWidth + (i * -1) - 4] = true;
				cell[centerHeight + i][centerWidth + (i * -1)] = true;
		}
/*
		for(int i = centerHeight - 8; i < centerHeight + 8; ++i){
			for(int j = centerWidth - 8; j < centerWidth + 7; ++j){
				if((i % 2 == 0 && i % 3 == 0) || (j % 2 == 0 && j % 3 == 0)){
					cell[i][j - 2] = true;
					cell[i][j] = true;
					cell[i][j + 2] = true;
				}
			}
		}
		for(int i = centerWidth - 8; i < centerWidth + 8; ++i){
			for(int j = centerHeight - 8; j < centerHeight + 8; ++j){
				if((i % 2 == 1 && i % 3 == 0) && (j % 2 == 1 && j % 3 == 0)){
					cell[j - 2][i] = true;
					cell[j][i] = true;
					cell[j + 2][i] = true;
				}
			}
		}

		/*
		cell[centerHeight + 4][centerWidth + 7] = true;
		cell[centerHeight - 7][centerWidth - 4] = true;
		cell[centerHeight + 7][centerWidth + 4] = true;
		cell[centerHeight - 4][centerWidth - 7] = true;

		cell[centerHeight + 5][centerWidth + 8] = true;
		cell[centerHeight - 8][centerWidth - 5] = true;
		cell[centerHeight + 8][centerWidth + 5] = true;
		cell[centerHeight - 5][centerWidth - 8] = true;
		*/
		init();
	}

	private void init() {
		// 初期設定
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createParts();
		mainPanelSet();

		this.setVisible(true);
		this.g = upside.getGraphics();
		new PaintLine(this.g, WIDTH, HEIGHT);

		ClickListener cl = new ClickListener(this.g, cell);
		this.addMouseListener(cl);
		this.addMouseMotionListener(cl);
	}

	private void createParts() {
		// 画面下部のパネル
		downside = new SetDownsideMainPanel(this, HEIGHT, WIDTH, cell, radio_num);

		this.add(downside);
	}

	private void mainPanelSet() {
		upside = new JPanel();
		upside.setLayout(null);
		upside.setBounds(0, 0, WIDTH, HEIGHT - 170);
		// upside.setBackground(Color.RED);

		this.add(upside);

	}

	@Override
	public void paint(Graphics g) {
		// 重要 記述でボタンなどもすべて描画される
		super.paint(g);
		this.paintG = g;
		new PaintLine(this.g, WIDTH, HEIGHT);
		new PaintCell(this.g, cell, ((SetDownsideMainPanel) downside).getColorChange());
	}

	@Override
	public void update(Graphics g) {
		super.paint(this.paintG);
		new PaintLine(this.g, WIDTH, HEIGHT);
		new PaintCell(this.g, cell, ((SetDownsideMainPanel) downside).getColorChange());
	}

}
