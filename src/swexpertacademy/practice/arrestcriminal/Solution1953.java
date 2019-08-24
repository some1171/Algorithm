package swexpertacademy.practice.arrestcriminal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1953 {
	public static int T, N, M, R, C, L;
	public static int[] dr = { -1, 0, 0, 1 };
	public static int[] dc = { 0, -1, 1, 0 };
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Solution1953 solution = new Solution1953();
			bw.flush();
			bw.write("#" + (t + 1) + " " + solution.bfs() + "\n");
		}

		br.close();
		bw.close();
	}

	public int bfs() {
		int count = 1;
		boolean[][] visit = new boolean[N][M];
		Queue<Point> queue = new LinkedList<Point>();

		queue.add(new Point(R, C, 1));
		visit[R][C] = true;

		while (!queue.isEmpty()) {
			Point point = queue.poll();
			int type = map[point.r][point.c];
			int nt = point.t + 1;
			if (nt > L) {
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = point.r + dr[i];
				int nc = point.c + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visit[nr][nc]) {
					int nType = map[nr][nc];
					
					if (type == 1 && isPossible(i, nType)) {
						visit[nr][nc] = true;
						queue.add(new Point(nr, nc, nt));
						count++;
					} else if (type == 2) {
						if (i == 1 || i == 2) {
							continue;
						}
						if (isPossible(i, nType)) {
							visit[nr][nc] = true;
							queue.add(new Point(nr, nc, nt));
							count++;
						}
					} else if (type == 3) {
						if (i == 0 || i == 3) {
							continue;
						}
						if (isPossible(i, nType)) {
							visit[nr][nc] = true;
							queue.add(new Point(nr, nc, nt));
							count++;
						}
					} else if (type == 4) {
						if (i == 1 || i == 3) {
							continue;
						}
						if (isPossible(i, nType)) {
							visit[nr][nc] = true;
							queue.add(new Point(nr, nc, nt));
							count++;
						}
					} else if (type == 5) {
						if (i == 0 || i == 1) {
							continue;
						}
						if (isPossible(i, nType)) {
							visit[nr][nc] = true;
							queue.add(new Point(nr, nc, nt));
							count++;
						}
					} else if (type == 6) {
						if (i == 0 || i == 2) {
							continue;
						}
						if (isPossible(i, nType)) {
							visit[nr][nc] = true;
							queue.add(new Point(nr, nc, nt));
							count++;
						}
					} else if (type == 7) {
						if (i == 2 || i == 3) {
							continue;
						}
						if (isPossible(i, nType)) {
							visit[nr][nc] = true;
							queue.add(new Point(nr, nc, nt));
							count++;
						}
					}
				}
			}
		}

		return count;
	}

	public boolean isPossible(int currentDirection, int nextType) {
		if (currentDirection == 0) {
			if (nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) {
				return true;
			}
		} else if (currentDirection == 1) {
			if (nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) {
				return true;
			}
		} else if (currentDirection == 2) {
			if (nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) {
				return true;
			}
		} else {
			if (nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) {
				return true;
			}
		}

		return false;
	}
}

class Point {
	int r, c, t;

	Point(int r, int c, int t) {
		this.r = r;
		this.c = c;
		this.t = t;
	}
}