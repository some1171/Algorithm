package acmicpc.castledefense;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17135 {
	public static int N, M, D, max = 0;
	public static int[] dr = { 0, 1, 0 };
	public static int[] dc = { -1, 0, 1 };
	public static int[][] imap;
	public static Queue<Point> queue = new LinkedList<Point>();

	public static void main(String[] args) throws IOException {
		Main17135 main = new Main17135();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		imap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				imap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		main.pick(0, 0, new boolean[M]);
		
		bw.flush();
		bw.write(max + "\n");
		br.close();
		bw.close();
	}

	public void pick(int pos, int count, boolean[] pick) {
		if (count == 3) {
			// 궁수 배치 완료, 게임 진행 시작
			int[][] map = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = imap[i][j];
				}
			}
			doGame(pick, map);

			return;
		}
		if (pos == M) {
			// 끝까지 순회했으나 3개 뽑지 못한 경우, 그대로 종료
			return;
		}
		pick[pos] = true;
		pick(pos + 1, count + 1, pick);
		pick[pos] = false;
		pick(pos + 1, count, pick);
	}

	public void doGame(boolean[] pick, int[][] map) {
		int c1 = 0, c2 = 0, c3 = 0, r = N;
		int idx = 0;
		for (int i = 0; i < M; i++) {
			if (pick[i]) {
				if (idx == 0) {
					c1 = i;
					idx++;
				} else if (idx == 1) {
					c2 = i;
					idx++;
				} else {
					c3 = i;
				}
			}
		}
		// 한턴 시작
		int turn = 0;
		int count = 0;
		while (turn < N) {
			boolean[][] attacked = new boolean[N][M];
			// 궁수 한명씩 거리가 D이하 중 가장 가깝고 가장 왼쪽에 있는 적을 선택한다.
			boolean flag = false;
			idx = 0;
			Point[] candi = new Point[300];
			for (int d = 1; d <= D; d++) {
				if (flag) {
					break;
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (map[i][j] == 1 && getDistance(i, j, r, c1) == d) {
							flag = true;
							candi[idx++] = new Point(i, j);
						}
					}
				}
			}
			Arrays.sort(candi, 0, idx);
			if (candi[0] != null) {
				attacked[candi[0].r][candi[0].c] = true;
				count++;
			}
			
			flag = false;
			idx = 0;
			for (int d = 1; d <= D; d++) {
				if (flag) {
					break;
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (map[i][j] == 1 && getDistance(i, j, r, c2) == d) {
							flag = true;
							candi[idx++] = new Point(i, j);
						}
					}
				}
			}
			Arrays.sort(candi, 0, idx);
			if (candi[0] != null && !attacked[candi[0].r][candi[0].c]) {
				attacked[candi[0].r][candi[0].c] = true;
				count++;
			}
			
			flag = false;
			idx = 0;
			for (int d = 1; d <= D; d++) {
				if (flag) {
					break;
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (map[i][j] == 1 && getDistance(i, j, r, c3) == d) {
							flag = true;
							candi[idx++] = new Point(i, j);
						}
					}
				}
			}
			Arrays.sort(candi, 0, idx);
			if (candi[0] != null && !attacked[candi[0].r][candi[0].c]) {
				attacked[candi[0].r][candi[0].c] = true;
				count++;
			}
			
			boolean isExist = false;
			for (int i = N - 1; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (i == 0) {
						map[i][j] = 0;
					} else {
						if (map[i - 1][j] == 1) {
							if (attacked[i - 1][j]) {
								map[i][j] = 0;
							} else {
								map[i][j] = 1;
								isExist = true;
							}
						} else {
							map[i][j] = 0;
						}
					}
				}
			}
			
			if (!isExist) {
				break;
			}
			
			turn++;
		}
		max = Math.max(max, count);
	}

	public int getDistance(int r1, int c1, int r2, int c2) {
		int distance = Math.abs(r1 - r2) + Math.abs(c1 - c2);
		return distance;
	}
}

class Point implements Comparable<Point> {
	int r, c;
	
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public int compareTo(Point p) {
		if (this.c < p.c) {
			return -1;
		} else {
			return 1;
		}
	}
}