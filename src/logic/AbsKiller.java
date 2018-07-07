package logic;

public class AbsKiller {
	private int[][] cell;

	public int[][] killerPuts(int[][] cell) {
		int centerHeight = cell.length / 2;
		int centerWidth = cell[0].length / 2;

		boolean[] flag = { true, true, true, true, true};
		int flag_count = -1;
		if (flag[++flag_count]) {
			for (int i = centerHeight - 27; i < centerHeight - 17; ++i) {
				for (int j = centerWidth + 18; j < centerWidth + 28; ++j) {
					cell[i][j] = 2;
				}
			}
			for (int i = centerHeight + 18; i < centerHeight + 28; ++i) {
				for (int j = centerWidth - 27; j < centerWidth - 17; ++j) {
					cell[i][j] = 2;
				}
			}
		}

		if (flag[++flag_count]) {
			for (int i = centerHeight - 44; i < centerHeight - 29; ++i) {
				for (int j = centerWidth - 44; j < centerWidth - 29; ++j) {
					cell[i][j] = 2;
				}
			}
			for (int i = centerHeight + 30; i < centerHeight + 45; ++i) {
				for (int j = centerWidth + 30; j < centerWidth + 45; ++j) {
					cell[i][j] = 2;
				}
			}
		}

		if (flag[++flag_count]) {
			for (int i = centerHeight - 14; i < centerHeight - 10; ++i) {
				for (int j = centerWidth - 14; j < centerWidth - 10; ++j) {
					cell[i][j] = 2;
				}
			}
			for (int i = centerHeight + 11; i < centerHeight + 15; ++i) {
				for (int j = centerWidth + 11; j < centerWidth + 15; ++j) {
					cell[i][j] = 2;
				}
			}
		}

		if (flag[++flag_count]) {
			for (int i = centerHeight - 13; i < centerHeight - 10; ++i) {
				for (int j = centerWidth + 7; j < centerWidth + 10; ++j) {
					cell[i][j] = 2;
				}
			}
			for (int i = centerHeight + 11; i < centerHeight + 14; ++i) {
				for (int j = centerWidth - 9; j < centerWidth - 6; ++j) {
					cell[i][j] = 2;
				}
			}
		}

		if (flag[++flag_count]) {
			cell[centerHeight - 3][centerWidth - 3] = 2;
			cell[centerHeight + 3][centerWidth + 3] = 2;
		}
		return cell;
	}
}
