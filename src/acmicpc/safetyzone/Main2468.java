package acmicpc.safetyzone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2468 {
	public static int N, safetyZone = 1;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	public static int[][] map = new int[100][100];
	public static boolean[][] visit;
	public static Queue<Point> queue = new LinkedList<Point>();

	public static void main(String[] args) throws IOException {
		Main2468 main = new Main2468();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int rain = 1; rain < 100; rain++) {
			visit = new boolean[N][N];
			int safetyCount = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visit[i][j]) {
						continue;
					}
					if (map[i][j] <= rain) {
						continue;
					}
					main.bfs(i, j, rain);
					safetyCount++;
				}
			}
			safetyZone = Math.max(safetyZone, safetyCount);
		}
		bw.flush();
		bw.write(safetyZone + "\n");
		br.close();
		bw.close();
	}

	public void bfs(int r, int c, int rain) {
		visit[r][c] = true;
		queue.add(new Point(r, c));

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc]) {
					if (map[nr][nc] > rain) {
						visit[nr][nc] = true;
						queue.add(new Point(nr, nc));
					}
				}
			}
		}
	}
}

class Point {
	int r, c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
