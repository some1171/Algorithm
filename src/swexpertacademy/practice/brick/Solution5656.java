package swexpertacademy.practice.brick;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution5656 {
	public static int T, N, W, H, answer;
	public static int[] dr = { -1, 0, 0, 1 };
	public static int[] dc = { 0, -1, 1, 0 };
	public static int[] balls;
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		Solution5656 solution = new Solution5656();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			answer = Integer.MAX_VALUE;
			balls = new int[N];
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			solution.selectBalls(0);

			bw.flush();
			bw.write("#" + (tc + 1) + " " + answer + "\n");
		}

		br.close();
		bw.close();
	}

	public void temp(int pos) {
		if (pos == N) {
			int[][] testMap = new int[H][W];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					testMap[i][j] = map[i][j];
				}
			}
			startShoot(0, testMap);
			int count = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (testMap[i][j] != 0) {
						count++;
					}
				}
			}
			answer = Math.min(answer, count);
			return;
		}
	}

	public void selectBalls(int pos) {
		if (pos == N) {
			int[][] testMap = new int[H][W];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					testMap[i][j] = map[i][j];
				}
			}
			startShoot(0, testMap);
			int count = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (testMap[i][j] != 0) {
						count++;
					}
				}
			}
			answer = Math.min(answer, count);
			return;
		}
		for (int i = 0; i < W; i++) {
			balls[pos] = i;
			selectBalls(pos + 1);
		}
	}

	public void startShoot(int pos, int[][] map) {
		if (pos == N) {
			return;
		}
		int r = -1, c = balls[pos];
		for (int i = 0; i < H; i++) {
			if (map[i][c] == 0) {
				continue;
			} else {
				r = i;
				break;
			}
		}
		if (r != -1) {
			doBreak(r, c, map);
			updateMap(map);
		}
		startShoot(pos + 1, map);
	}

	public void doBreak(int r, int c, int[][] map) {
		int length = map[r][c];
		map[r][c] = 0;
		if (length > 1) {
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < length; j++) {
					int nr = r + dr[i] * j;
					int nc = c + dc[i] * j;
					if (nr >= 0 && nr < H && nc >= 0 && nc < W) {
						if (map[nr][nc] != 0) {
							doBreak(nr, nc, map);
						}
					}
				}
			}
		}
	}

	public void updateMap(int[][] map) {
		for (int i = 0; i < W; i++) {
			int index = 0;
			int[] line = new int[H];
			for (int j = H - 1; j >= 0; j--) {
				if (map[j][i] != 0) {
					line[index] = map[j][i];
					index++;
				}
			}
			for (int j = H - 1; j >= 0; j--) {
				map[j][i] = line[H - 1 - j];
			}
		}
	}
}
