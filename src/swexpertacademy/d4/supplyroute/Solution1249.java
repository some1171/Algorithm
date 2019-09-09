package swexpertacademy.d4.supplyroute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1249 {
	public static final int INF = Integer.MAX_VALUE;
	public static int T, N;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, -1, 0, 1 };
	public static int[][] map, distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			distance = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine().trim();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) -'0';
					distance[i][j] = INF;
				}
			}
			
			// bfs
			Queue<Point> queue = new LinkedList<Point>();
			distance[0][0] = 0;
			queue.add(new Point(0, 0, 0));
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				int r = p.r;
				int c = p.c;
				int d = p.d;
				
				for (int dir = 0; dir < 4; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
						int nd = d + map[nr][nc];
						if (nd >= distance[nr][nc]) {
							continue;
						}
						distance[nr][nc] = nd;
						queue.add(new Point(nr, nc, nd));
					}
				}
			}
			
			bw.write("#" + tc + " " + distance[N - 1][N - 1] + "\n");
			bw.flush();
		}

		br.close();
		bw.close();
	}
}

class Point {
	int r, c, d;

	public Point(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}
