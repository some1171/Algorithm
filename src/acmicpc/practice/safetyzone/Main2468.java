package acmicpc.practice.safetyzone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2468 {
	public static int N, maxH = 0, maxSafetyZone = 1;
	public static int[] dr = { -1, 0, 0, 1 };
	public static int[] dc = { 0, -1, 1, 0 };
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, map[i][j]);
			}
		}

		for (int rain = 1; rain <= maxH; rain++) {
			boolean[][] visit = new boolean[N][N];
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j] && map[i][j] > rain) {
						bfs(i, j, rain, visit);
						count++;
					}
				}
			}
			maxSafetyZone = Math.max(maxSafetyZone, count);
		}

		bw.write(maxSafetyZone + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

	public static void bfs(int r, int c, int rain, boolean[][] visit) {
		Queue<Point> queue = new LinkedList<Point>();
		visit[r][c] = true;
		queue.add(new Point(r, c));
		
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int cr = p.r;
			int cc = p.c;
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc] && map[nr][nc] > rain) {
					visit[nr][nc] = true;
					queue.add(new Point(nr, nc));
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