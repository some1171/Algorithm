package acmicpc.practice.cubing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main5373 {
	public static int T, N;
	public static char[] cmd;
	public static char[] cmdDirection;
	public static char[][] up = { { 'w', 'w', 'w' }, { 'w', 'w', 'w' }, { 'w', 'w', 'w' } };
	public static char[][] down = { { 'y', 'y', 'y' }, { 'y', 'y', 'y' }, { 'y', 'y', 'y' } };
	public static char[][] front = { { 'r', 'r', 'r' }, { 'r', 'r', 'r' }, { 'r', 'r', 'r' } };
	public static char[][] back = { { 'o', 'o', 'o' }, { 'o', 'o', 'o' }, { 'o', 'o', 'o' } };
	public static char[][] left = { { 'g', 'g', 'g' }, { 'g', 'g', 'g' }, { 'g', 'g', 'g' } };
	public static char[][] right = { { 'b', 'b', 'b' }, { 'b', 'b', 'b' }, { 'b', 'b', 'b' } };

	public static void main(String[] args) throws IOException {
		Main5373 main = new Main5373();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			cmd = new char[N];
			cmdDirection = new char[N];
			st = new StringTokenizer(br.readLine().trim());
			for (int n = 0; n < N; n++) {
				String str = st.nextToken();
				cmd[n] = str.charAt(0);
				cmdDirection[n] = str.charAt(1);
			}

			// ÃÊ±âÈ­
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					up[i][j] = 'w';
				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					down[i][j] = 'y';
				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					front[i][j] = 'r';
				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					back[i][j] = 'o';
				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					left[i][j] = 'g';
				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					right[i][j] = 'b';
				}
			}
			
			for (int i = 0; i < N; i++) {
				if (cmd[i] == 'L') {
					main.rotateLeft(cmdDirection[i]);
				} else if (cmd[i] == 'R') {
					main.rotateRight(cmdDirection[i]);
				} else if (cmd[i] == 'U') {
					main.rotateUp(cmdDirection[i]);
				} else if (cmd[i] == 'D') {
					main.rotateDown(cmdDirection[i]);
				} else if (cmd[i] == 'F') {
					main.rotateFront(cmdDirection[i]);
				} else if (cmd[i] == 'B') {
					main.rotateBack(cmdDirection[i]);
				}
			}
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					bw.write(up[i][j]);
				}
				bw.write("\n");
				bw.flush();
			}
		}
		br.close();
		bw.close();
	}

	public void rotateUp(char d) {
		char[] temp = new char[3];
		for (int i = 0; i < 3; i++) {
			temp[i] = back[0][i];
		}
		if (d == '+') {
			rotatePlus(up);
			for (int i = 0; i < 3; i++) {
				back[0][i] = left[2 - i][0];
			}
			for (int i = 0; i < 3; i++) {
				left[i][0] = front[0][i];
			}
			for (int i = 0; i < 3; i++) {
				front[0][i] = right[2 - i][0];
			}
			for (int i = 0; i < 3; i++) {
				right[i][0] = temp[i];
			}
		} else {
			rotateMinus(up);
			for (int i = 0; i < 3; i++) {
				back[0][i] = right[i][0];
			}
			for (int i = 0; i < 3; i++) {
				right[i][0] = front[0][2 - i];
			}
			for (int i = 0; i < 3; i++) {
				front[0][i] = left[i][0];
			}
			for (int i = 0; i < 3; i++) {
				left[i][0] = temp[2 - i];
			}
		}
	}

	public void rotateDown(char d) {
		char[] temp = new char[3];
		for (int i = 0; i < 3; i++) {
			temp[i] = back[2][i];
		}
		if (d == '+') {
			down = rotatePlusLeftAndDown(down);
			for (int i = 0; i < 3; i++) {
				back[2][i] = right[i][2];
			}
			for (int i = 0; i < 3; i++) {
				right[i][2] = front[2][2 - i];
			}
			for (int i = 0; i < 3; i++) {
				front[2][i] = left[i][2];
			}
			for (int i = 0; i < 3; i++) {
				left[i][2] = temp[2 - i];
			}
		} else {
			down = rotateMinusLeftAndDown(down);
			for (int i = 0; i < 3; i++) {
				back[2][i] = left[2 - i][2];
			}
			for (int i = 0; i < 3; i++) {
				left[i][2] = front[2][i];
			}
			for (int i = 0; i < 3; i++) {
				front[2][i] = right[2 - i][2];
			}
			for (int i = 0; i < 3; i++) {
				right[i][2] = temp[i];
			}
		}
	}

	public void rotateFront(char d) {
		char[] temp = new char[3];
		for (int i = 0; i < 3; i++) {
			temp[i] = up[2][i];
		}
		if (d == '+') {
			rotatePlus(front);
			for (int i = 0; i < 3; i++) {
				up[2][i] = left[2][2 - i];
			}
			for (int i = 0; i < 3; i++) {
				left[2][2 - i] = down[2][2 - i];
			}
			for (int i = 0; i < 3; i++) {
				down[2][2 - i] = right[2][i];
			}
			for (int i = 0; i < 3; i++) {
				right[2][i] = temp[i];
			}
		} else {
			rotateMinus(front);
			for (int i = 0; i < 3; i++) {
				up[2][i] = right[2][i];
			}
			for (int i = 0; i < 3; i++) {
				right[2][i] = down[2][2 - i];
			}
			for (int i = 0; i < 3; i++) {
				down[2][2 - i] = left[2][2 - i];
			}
			for (int i = 0; i < 3; i++) {
				left[2][2 - i] = temp[i];
			}
		}
	}

	public void rotateBack(char d) {
		char[] temp = new char[3];
		for (int i = 0; i < 3; i++) {
			temp[i] = up[0][i];
		}
		if (d == '+') {
			back = rotatePlusBack(back);
			for (int i = 0; i < 3; i++) {
				up[0][i] = right[0][i];
			}
			for (int i = 0; i < 3; i++) {
				right[0][i] = down[0][2 - i];
			}
			for (int i = 0; i < 3; i++) {
				down[0][2 - i] = left[0][2 - i];
			}
			for (int i = 0; i < 3; i++) {
				left[0][2 - i] = temp[i];
			}
		} else {
			back = rotateMinusBack(back);
			for (int i = 0; i < 3; i++) {
				up[0][i] = left[0][2 - i];
			}
			for (int i = 0; i < 3; i++) {
				left[0][2 - i] = down[0][2 - i];
			}
			for (int i = 0; i < 3; i++) {
				down[0][2 - i] = right[0][i];
			}
			for (int i = 0; i < 3; i++) {
				right[0][i] = temp[i];
			}
		}
	}

	public void rotateLeft(char d) {
		char[] temp = new char[3];
		for (int i = 0; i < 3; i++) {
			temp[i] = up[i][0];
		}
		if (d == '+') {
			left = rotatePlusLeftAndDown(left);
			for (int i = 0; i < 3; i++) {
				up[i][0] = back[2 - i][0];
			}
			for (int i = 0; i < 3; i++) {
				back[2 - i][0] = down[2 - i][0];
			}
			for (int i = 0; i < 3; i++) {
				down[2 - i][0] = front[i][0];
			}
			for (int i = 0; i < 3; i++) {
				front[i][0] = temp[i];
			}
		} else {
			left = rotateMinusLeftAndDown(left);
			for (int i = 0; i < 3; i++) {
				up[i][0] = front[i][0];
			}
			for (int i = 0; i < 3; i++) {
				front[i][0] = down[2 - i][0];
			}
			for (int i = 0; i < 3; i++) {
				down[2 - i][0] = back[2 - i][0];
			}
			for (int i = 0; i < 3; i++) {
				back[2 - i][0] = temp[i];
			}
		}
	}

	public void rotateRight(char d) {
		char[] temp = new char[3];
		for (int i = 0; i < 3; i++) {
			temp[i] = up[i][2];
		}
		if (d == '+') {
			rotatePlus(right);
			for (int i = 0; i < 3; i++) {
				up[i][2] = front[i][2];
			}
			for (int i = 0; i < 3; i++) {
				front[i][2] = down[2 - i][2];
			}
			for (int i = 0; i < 3; i++) {
				down[2 - i][2] = back[2 - i][2];
			}
			for (int i = 0; i < 3; i++) {
				back[2 - i][2] = temp[i];
			}
		} else {
			rotateMinus(right);
			for (int i = 0; i < 3; i++) {
				up[i][2] = back[2 - i][2];
			}
			for (int i = 0; i < 3; i++) {
				back[2 - i][2] = down[2 - i][2];
			}
			for (int i = 0; i < 3; i++) {
				down[2 - i][2] = front[i][2];
			}
			for (int i = 0; i < 3; i++) {
				front[i][2] = temp[i];
			}
		}
	}

	public void rotatePlus(char[][] face) {
		char[] right = new char[3];
		char[] left = new char[3];
		char[] mid = new char[3];
		right[0] = face[0][0];
		right[1] = face[0][1];
		right[2] = face[0][2];
		left[0] = face[2][0];
		left[1] = face[2][1];
		left[2] = face[2][2];
		mid[0] = face[1][0];
		mid[1] = face[1][1];
		mid[2] = face[1][2];
		face[0][0] = left[0];
		face[1][0] = left[1];
		face[2][0] = left[2];
		face[0][1] = mid[0];
		face[1][1] = mid[1];
		face[2][1] = mid[2];
		face[0][2] = right[0];
		face[1][2] = right[1];
		face[2][2] = right[2];
	}

	public void rotateMinus(char[][] face) {
		char[] right = new char[3];
		char[] left = new char[3];
		char[] mid = new char[3];
		right[0] = face[2][2];
		right[1] = face[2][1];
		right[2] = face[2][0];
		left[0] = face[0][2];
		left[1] = face[0][1];
		left[2] = face[0][0];
		mid[0] = face[1][2];
		mid[1] = face[1][1];
		mid[2] = face[1][0];
		face[0][0] = left[0];
		face[1][0] = left[1];
		face[2][0] = left[2];
		face[0][1] = mid[0];
		face[1][1] = mid[1];
		face[2][1] = mid[2];
		face[0][2] = right[0];
		face[1][2] = right[1];
		face[2][2] = right[2];
	}

	public char[][] rotatePlusLeftAndDown(char[][] face) {
		char[][] next = new char[3][3];
		next[0][2] = face[2][2];
		next[0][1] = face[1][2];
		next[0][0] = face[0][2];
		next[1][2] = face[2][1];
		next[1][1] = face[1][1];
		next[1][0] = face[0][1];
		next[2][2] = face[2][0];
		next[2][1] = face[1][0];
		next[2][0] = face[0][0];
		return next;
	}

	public char[][] rotateMinusLeftAndDown(char[][] face) {
		char[][] next = new char[3][3];
		next[0][2] = face[0][0];
		next[0][1] = face[1][0];
		next[0][0] = face[2][0];
		next[1][2] = face[0][1];
		next[1][1] = face[1][1];
		next[1][0] = face[2][1];
		next[2][2] = face[0][2];
		next[2][1] = face[1][2];
		next[2][0] = face[2][2];
		return next;
	}
	
	public char[][] rotatePlusBack(char[][] face) {
		char[][] next = new char[3][3];
		next[2][0] = face[0][0];
		next[2][1] = face[1][0];
		next[2][2] = face[2][0];
		next[1][0] = face[0][1];
		next[1][1] = face[1][1];
		next[1][2] = face[2][1];
		next[0][0] = face[0][2];
		next[0][1] = face[1][2];
		next[0][2] = face[2][2];
		return next;
	}
	
	public char[][] rotateMinusBack(char[][] face) {
		char[][] next = new char[3][3];
		next[2][0] = face[2][2];
		next[2][1] = face[1][2];
		next[2][2] = face[0][2];
		next[1][0] = face[2][1];
		next[1][1] = face[1][1];
		next[1][2] = face[0][1];
		next[0][0] = face[2][0];
		next[0][1] = face[1][0];
		next[0][2] = face[0][0];
		return next;
	}
}
