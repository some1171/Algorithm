package acmicpc.practice.laboratory3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17142 {
	public static int N, M, C, min = -1;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	public static int[][] map;
	public static Point[] candi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		candi = new Point[10];
		C = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					candi[C++] = new Point(i, j, 0);
				}
			}
		}

		pick(0, 0, new boolean[C]);

		bw.write(min + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

	public static void pick(int pos, int count, boolean[] isPick) {
		if (count == M) {
			// bfs Å½»ö ½ÃÀÛ
			bfs(isPick);
			return;
		}
		if (pos == C) {
			return;
		}
		// »ÌÀ» °æ¿ì
		isPick[pos] = true;
		pick(pos + 1, count + 1, isPick);
		isPick[pos] = false;
		// »ÌÁö ¾ÊÀ» °æ¿ì
		pick(pos + 1, count, isPick);
	}

	public static void bfs(boolean[] isPick) {
		Queue<Point> queue = new LinkedList<Point>();
		int[][] cmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, cmap[i], 0, N);
		}
		for (int i = 0; i < C; i++) {
			if (isPick[i]) {
				Point p = candi[i];
				int r = p.r;
				int c = p.c;
				cmap[r][c] = 3;
				queue.add(new Point(r, c, 0));
			}
		}
		int time = 0;
		while (!queue.isEmpty()) {
			if (isDone(cmap)) {
				if (min == -1) {
					min = time;
				} else {
					min = Math.min(min, time);
				}
				break;
			}
			Point p = queue.poll();
			int r = p.r;
			int c = p.c;
			int t = p.t;
			if (min != -1 && min <= t + 1) {
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && cmap[nr][nc] != 1 && cmap[nr][nc] != 3) {
					cmap[nr][nc] = 3;
					queue.add(new Point(nr, nc, t + 1));
					time = t + 1;
				}
			}
		}
	}

	public static boolean isDone(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}

class Point {
	int r, c, t;

	public Point(int r, int c, int t) {
		this.r = r;
		this.c = c;
		this.t = t;
	}
}