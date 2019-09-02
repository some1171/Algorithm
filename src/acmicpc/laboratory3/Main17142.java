package acmicpc.laboratory3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17142 {
	public static int N, M, T = Integer.MAX_VALUE, candiCount = 0;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	public static int[][] map;
	public static boolean isSuccess;
	public static Point[] candi = new Point[10];;

	public static void main(String[] args) throws IOException {
		Main17142 main = new Main17142();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					candi[candiCount++] = new Point(i, j, 0);
				}
			}
		}
		
		isSuccess = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					isSuccess = false;
				}
			}
		}
		
		if (!isSuccess) {
			main.pick(0, 0, new boolean[candiCount]);
		} else {
			T = 0;
		}
		
		if (!isSuccess) {
			T = -1;
		}
		
		bw.flush();
		bw.write(T + "\n");
		br.close();
		bw.close();
	}

	public void pick(int pos, int count, boolean[] pick) {
		if (count == M) {
			bfs(pick);
			return;
		}
		if (pos == candiCount) {
			// 끝까지 탐색했으나 다 뽑지 않은 경우 바로 종료
			return;
		}
		pick[pos] = true;
		pick(pos + 1, count + 1, pick);
		pick[pos] = false;
		pick(pos + 1, count, pick);
	}

	public void bfs(boolean[] pick) {
		Queue<Point> queue = new LinkedList<Point>();
		int[][] nmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nmap[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < candiCount; i++) {
			if (pick[i]) {
				Point p = candi[i];
				nmap[p.r][p.c] = 1;
				queue.add(p);
			}
		}
		
		int time = 0;
		boolean isFull = false;
		while (!queue.isEmpty()) {
			if (isFull) {
				break;
			}
			Point p = queue.poll();
			int r = p.r;
			int c = p.c;
			int t = p.t;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				int nt = t + 1;
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && nmap[nr][nc] != 1) {
					time = nt;
					nmap[nr][nc] = 1;
					queue.add(new Point(nr, nc, nt));
				}
			}
			isFull = true;
			for (int i = 0; i < N; i++) {
				if (!isFull) {
					break;
				}
				for (int j = 0; j < N; j++) {
					if (nmap[i][j] == 0) {
						isFull = false;
						break;
					}
				}
			}
			if (isFull) {
				isSuccess = true;
				T = Math.min(T, time);
			}
		}
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
