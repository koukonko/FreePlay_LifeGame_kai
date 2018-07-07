package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * ライフゲームの仕様を変更して、フラクタルな図形を作成する
 *
 */
public class LifeGameLogic {
	// 現世代
	private int[][] prevGene;
	// 次世代
	private int[][] nextGene;
	// alfabet用のBuff
	private BufferedReader read = null;
	private int[][] box = null;
	// シリアルキラー(ランダムに移動する)
	private SerialKiller sk = null;
	// 絶対殺すマン
	private AbsKiller ak = null;

	public LifeGameLogic(int[][] cell) {
		// シリアルキラー爆誕！
		// sk = new SerialKiller(175, cell);
		// 絶対死の範囲
		// ak = new AbsKiller();
	}

	public int[][] nextGene(int[][] prevGene) {
		nextGene = prevGene.clone();
		int[][] tmpGene = new int[ prevGene.length + 2 ][ prevGene[0].length + 2 ];

		// 0 を周りに詰める
		for (int i = 0; i < tmpGene.length; ++i) {
			for (int j = 0; j < tmpGene[i].length; ++j) {
				if (i == 0 || j == 0 || i == tmpGene.length - 1 || j == tmpGene[i].length - 1) {
					tmpGene[i][j] = 0;
				} else {
					tmpGene[i][j] = prevGene[i - 1][j - 1];
				}
			}
		}

		// 次世代のロジック
		for (int i = 1; i < tmpGene.length - 1; ++i) {
			for (int j = 1; j < tmpGene[i].length - 1; ++j) {
				if (tmpGene[i][j] == 1) {
					nextGene[i - 1][j - 1] = aliveCell(tmpGene, i, j);
				} else {
					nextGene[i - 1][j - 1] = deadCell(tmpGene, i, j);
				}
			}
		}

		// シリアルキラーを動かす
		// sk.move(nextGene);

		// 絶対殺すマン
		// ak.killerPuts(nextGene);

		/*
		 * alfabet用のプログラム
		 */

		boolean test_flag = false;
		final int HEIGHT = 81, WIDTH = 364;
		if (read == null) {
			String path = new File(".").getAbsoluteFile().getParent();
			InputStream is = null;
			try {
				is = new FileInputStream(path + "/data/data_ALFABET.txt");
				read = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			if (box == null) {
				box = new int[ HEIGHT ][ WIDTH ];
				for (int i = 0; i < HEIGHT; ++i) {
					String line = null;
					try {
						line = read.readLine();
						for (int j = 0; j < WIDTH; ++j) {
							if (j < line.length() && line.charAt(j) == 'o') {
								box[i][j] = 0;
							} else if (j < line.length() && line.charAt(j) == 'z') {
								box[i][j] = 1;
							} else {
								box[i][j] = 2;
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		for (int i = 0; i < HEIGHT; ++i) {
			for (int j = 0; j < WIDTH; ++j) {
				if (box[i][j] == 0) {
					nextGene[i][j] = test_flag == false ? 0 : 1;
				}
				if (test_flag == false && box[i][j] == 1) {
					nextGene[i][j] = 1;
				}
			}
		}
		this.prevGene = nextGene.clone();

		return nextGene;
	}

	public int aliveCell(int[][] tmpGene, int x, int y) {
		int count = 0;
		double countDouble = 0;

		int[][] circu = { { -1, -1 }, // 左上
				{ 0, -1 }, // 上
				{ 1, -1 }, // 右上
				{ 1, 0 }, // 右
				{ 1, 1 }, // 右下
				{ 0, 1 }, // 下
				{ -1, 1 }, // 左下
				{ -1, 0 } // 左
		};

		double[] circuDouble = { 0.035, // 左上
				0.065, // 上
				0.750, // 右上
				0.015, // 右
				0.035, // 右下
				0.065, // 下
				0.750, // 左下
				0.015 // 左
		};

		for (int i = 0; i < circu.length; ++i) {

			if (tmpGene[x - circu[i][0]][y - circu[i][1]] == 1) {
				count++;
				countDouble += circuDouble[i];
			}

		}

		// if ((count >= 3 || count <= 5) || (countDouble >= 0.75)) {
		if ((count >= 3 && count <= 5)) {
			return 1;
		} else {
			return 0;
		}
	}

	public int deadCell(int[][] box, int x, int y) {
		int count = 0;

		int[][] circu = { { -1, -1 }, // 左上
				{ 0, -1 }, // 上
				{ 1, -1 }, // 右上
				{ 1, 0 }, // 右
				{ 1, 1 }, // 右下
				{ 0, 1 }, // 下
				{ -1, 1 }, // 左下
				{ -1, 0 } // 左
		};

		/*
		 * int[][] circu = { { 0, -1 }, // 上 { 1, 0 }, // 右 { 0, 1 }, // 下 { -1,
		 * 0 } // 左 };
		 */

		for (int i = 0; i < circu.length; ++i) {

			if (box[x - circu[i][0]][y - circu[i][1]] == 1) {
				count++;
			}
		}
		// if (count == 3 || (count >= 5 && count <= 6)) {
		if (count == 3) {
			return 1;
		} else {
			return 0;
		}
	}

	public int[][] getPrevGene() {
		return this.prevGene;
	}
}
