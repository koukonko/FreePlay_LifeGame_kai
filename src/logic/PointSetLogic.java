package logic;

public class PointSetLogic {

	public int[][] circle(int centerHeight, int centerWidth, int r, int cell[][]){
		int x = r;
		int y = 0;
		int f = -2 * r + 3;

		while(x >= y){
			cell[centerHeight + x][centerWidth - 1 + y] = 1;
			cell[centerHeight - x][centerWidth - 1 + y] = 1;
			cell[centerHeight + x][centerWidth - 1 - y] = 1;
			cell[centerHeight - x][centerWidth - 1 - y] = 1;
			cell[centerHeight + y][centerWidth - 1 + x] = 1;
			cell[centerHeight - y][centerWidth - 1 + x] = 1;
			cell[centerHeight + y][centerWidth - 1 - x] = 1;
			cell[centerHeight - y][centerWidth - 1 - x] = 1;

			if(f >= 0){
				x--;
				f -= 4 * x;
			}
			++y;
			f += 4 * y + 2;
		}

		return cell;
	}

	public int[][] circleRight(int centerHeight, int centerWidth, int r, int cell[][]){
		int x = r;
		int y = 0;
		int f = -2 * r + 3;

		while(x >= y){
			cell[centerHeight + x][centerWidth - 1 + y] = 1;
			cell[centerHeight - x][centerWidth - 1 + y] = 1;
			cell[centerHeight + y][centerWidth - 1 + x] = 1;
			cell[centerHeight - y][centerWidth - 1 + x] = 1;

			if(f >= 0){
				x--;
				f -= 4 * x;
			}
			++y;
			f += 4 * y + 2;
		}

		return cell;
	}

	public int[][] circleLeft(int centerHeight, int centerWidth, int r, int cell[][]){
		int x = r;
		int y = 0;
		int f = -2 * r + 3;

		while(x >= y){
			cell[centerHeight + x][centerWidth - 1 - y] = 1;
			cell[centerHeight - x][centerWidth - 1 - y] = 1;
			cell[centerHeight + y][centerWidth - 1 - x] = 1;
			cell[centerHeight - y][centerWidth - 1 - x] = 1;

			if(f >= 0){
				x--;
				f -= 4 * x;
			}
			++y;
			f += 4 * y + 2;
		}

		return cell;
	}
}
