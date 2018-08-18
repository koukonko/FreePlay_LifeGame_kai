package frame;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import listener.ClickListener;
import paint.PaintCell;
import paint.PaintLine;
import parts.SetDownsideMainPanel;

public class MainFrame extends JFrame {

	// ウィンドウのサイズ
	final private int WIDTH = 1900; // 1800
	final private int HEIGHT = 1085; // 1085

	// 画面の上下のパネル
	private JPanel upside, downside;
	private Graphics g, paintG;

	// テスト用
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

	// 作成中 テスト用のラジオボタン　選択中の番号を保持
	private static int radio_num = 0;

	// ライフゲームのセル
	private int[][] cell;

	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		super("GAME OF LIFE");

		// y軸のセルの数
		System.out.println("HEIGHT - CELL_SIZE : " + ((HEIGHT - 200) / 5));
		// x軸のセルの数
		System.out.println("WIDHT  - CELL_SIZE : " + ((WIDTH - 60) / 5));

		// セルの作成
		cell = new int[(HEIGHT - 200) / 5 - 1][(WIDTH - 60) / 5 - 1];
		for (int i = 0; i < cell.length; ++i) {
			for (int j = 0; j < cell[0].length; ++j) {
				// 0(死)で初期化する
				cell[i][j] = 0;
			}
		}

		init();
	}

	private void init() {
		// 初期設定
		this.setSize(WIDTH, HEIGHT);
		// 画面の中央表示
		this.setLocationRelativeTo(null);
		// レイアウトの設定はなし
		this.setLayout(null);
		// ×ボタンでプログラム終了
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// パーツの作成
		createParts();
		mainPanelSet();

		// 画面表示
		this.setVisible(true);
		// 画面上部のグラフィックの設定
		this.g = upside.getGraphics();
		new PaintLine(this.g, WIDTH, HEIGHT);

		// クリックリスナー(マウスの監視)
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
		// 画面上部のパネル
		upside = new JPanel();
		upside.setLayout(null);
		upside.setBounds(0, 0, WIDTH, HEIGHT - 170);
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
