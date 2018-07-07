package logic;

import java.util.Random;

/*
 * シリアルキラーを生成する
 * 今回は上下対象の位置にキラーを作る
 */

public class SerialKiller {

	private int[][] cell;
	private int member_count;
	private SerialKillerMember[] member;

	// コンストラクタ
	// 初期位置が決まる
	public SerialKiller(int[][] cell) {
		this(1, cell);
	}

	public SerialKiller(int member_count, int[][] cell) {
		this.member_count = member_count;
		this.cell = cell;
		member = new SerialKillerMember[this.member_count];
		for(int i = 0; i < member_count; ++i){
			member[i] = new SerialKillerMember();
		}
	}

	public void move(int[][] nextGene) {
		this.cell = nextGene;
		for(int i = 0; i < member_count; ++i){
			member[i].move(0);
		}
	}

	public int[][] getCell(){
		return cell;
	}

	private class SerialKillerMember {
		private Random rnd;
		private int pos_x_plus;
		private int pos_y_plus;

		private int pos_x_minus;
		private int pos_y_minus;
		int[] counter = new int[8];

		public SerialKillerMember() {
			rnd = new Random();
			int min = 0;

			int centerHeight = cell.length / 2, centerWidth = cell[0].length / 2;
			if(centerHeight < centerHeight){
				// min = centerHeight / 3;
				min = centerHeight / 2;
			} else {
				// min = centerWidth / 3;
				min = centerWidth / 2;
			}

			//System.out.println(min);
			//int pos_x = rnd.nextInt(min) - (min / 2);
			//int pos_x = rnd.nextInt(min) - (min / 1) + 50;
			int pos_x = rnd.nextInt(min) - (min / 1);
			//int pos_x = rnd.nextInt(min);
			//int pos_y = (int)(rnd.nextInt(min) * 1.9);
			//int pos_y = rnd.nextInt(min) - (min / 1) + 45;
			int pos_y = rnd.nextInt(min) - (min / 1);
			//int pos_y = rnd.nextInt(min);

			// pos_x = 1;
			// pos_y = 0;

			// シリアルキラーの正
			pos_x_plus = centerHeight + pos_x;
			pos_y_plus = centerWidth + pos_y;
			// シリアルキラーの負
			pos_x_minus = centerHeight - pos_x;
			pos_y_minus = centerWidth - pos_y;
		}

		/*
		 * シリアルキラーを移動させる
		 */
		public void move(int count) {
			// {x , y} x = 横軸 y = 縦軸
			int[][] move_pos = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

			// 0～7 0が上で右回りに7の左上まで
			int move_pos_plus = rnd.nextInt(8);
			counter[move_pos_plus]++;
			/*for(int i = 0; i < 8; ++i){
				System.out.print(counter[i] + " ");
			}
			System.out.println();
			*/
			// 反対
			int move_pos_minus = (move_pos_plus + 12) % 8;

			int tmp_pos_x_plus = move_pos[move_pos_plus][0] + pos_x_plus;
			int tmp_pos_y_plus = move_pos[move_pos_plus][1] + pos_y_plus;

			int tmp_pos_x_minus = move_pos[move_pos_minus][0] + pos_x_minus;
			int tmp_pos_y_minus = move_pos[move_pos_minus][1] + pos_y_minus;

			//System.out.println("tmp_pos_x_plus: " + Math.abs(cell.length - tmp_pos_x_plus) + " tmp_pos_y_plus: " + Math.abs(cell[0].length - tmp_pos_y_plus) + " tmp_pos_x_minus: " + Math.abs(cell.length - tmp_pos_x_minus) + " tmp_pos_y_minus: " + Math.abs(cell[0].length - tmp_pos_y_minus));

			if (tmp_pos_x_plus < 0 || tmp_pos_x_plus >= cell.length || tmp_pos_y_plus < 0 || tmp_pos_y_plus >= cell[0].length ||
				tmp_pos_x_minus < 0 || tmp_pos_x_minus >= cell.length || tmp_pos_y_minus < 0 || tmp_pos_y_minus >= cell[0].length) {
				//System.out.println("tmp_pos_x_plus: " + tmp_pos_x_plus + " tmp_pos_y_plus: " + tmp_pos_y_plus + " tmp_pos_x_minus: " + tmp_pos_x_minus + " tmp_pos_y_minus: " + tmp_pos_y_minus);
			} else {
				pos_x_plus = tmp_pos_x_plus;
				pos_y_plus = tmp_pos_y_plus;

				pos_x_minus = tmp_pos_x_minus;
				pos_y_minus = tmp_pos_y_minus;
			}

			cell[pos_x_plus][pos_y_plus] = 2;
			cell[pos_x_minus][pos_y_minus] = 2;
		}
	}
}
