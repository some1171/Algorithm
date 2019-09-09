package acmicpc.practice.baseball;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main17281 {
	public static int N, maxPoint = 0;
	public static int[][] innings;

	public static void main(String[] args) throws IOException {
		Main17281 main = new Main17281();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		innings = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < 9; j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] pick = { 2, 3, 4, 1, 5, 6, 7, 8, 9 };
		main.pick(0, 9, 9, pick);

		bw.flush();
		bw.write(maxPoint + "\n");
		br.close();
		bw.close();
	}

	public void pick(int depth, int n, int k, int[] pick) {
		if (depth == k) {
			// 다 뽑은 경우 게임 진행
			doGame(pick);
			return;
		}

		if (depth == 3) {
			pick(depth + 1, n, k, pick);
			return;
		}

		for (int i = depth; i < n; i++) {
			if (i == 3) {
				continue;
			} else {
				swap(i, depth, pick);
				pick(depth + 1, n, k, pick);
				swap(i, depth, pick);
			}
		}
	}

	public void swap(int i, int j, int[] pick) {
		int temp = pick[i];
		pick[i] = pick[j];
		pick[j] = temp;
	}

	public void doGame(int[] pick) {
		int[] base;
		int inning = 0, point = 0, idx = 0, out;
		while (inning < N) {
			out = 0;
			base = new int[3];
			while (true) {
				if (idx == 9) {
					idx = 0;
				}
				int ta = innings[inning][pick[idx++] - 1];
				if (ta == 0) {
					out++;
					if (out == 3) {
						break;
					}
				} else if (ta == 1) {
					if (base[2] == 1) {
						point++;
					}
					if (base[1] == 1) {
						base[2] = 1;
					} else {
						base[2] = 0;
					}
					if (base[0] == 1) {
						base[1] = 1;
					} else {
						base[1] = 0;
					}
					base[0] = 1;
				} else if (ta == 2) {
					for (int b = 1; b < 3; b++) {
						if (base[b] == 1) {
							base[b] = 0;
							point++;
						}
					}
					if (base[0] == 1) {
						base[0] = 0;
						base[2] = 1;
					}
					base[1] = 1;
				} else if (ta == 3) {
					for (int b = 0; b < 3; b++) {
						if (base[b] == 1) {
							base[b] = 0;
							point++;
						}
					}
					base[2] = 1;
				} else if (ta == 4) {
					for (int b = 0; b < 3; b++) {
						if (base[b] == 1) {
							base[b] = 0;
							point++;
						}
					}
					point++;
				}
			}
			inning++;
		}
		maxPoint = Math.max(maxPoint, point);
	}
}
