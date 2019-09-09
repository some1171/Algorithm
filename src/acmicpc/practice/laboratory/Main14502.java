package acmicpc.practice.laboratory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14502 {
	public static int N, M, V = 0, maxSafetyZone = 0;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	public static int[][] map;
	public static Point[] viruses = new Point[10];

	public static void main(String[] args) throws IOException {
		Main14502 main = new Main14502();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					viruses[V++] = new Point(i, j);
				}
			}
		}

		int[][] cmap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cmap[i][j] = map[i][j];
			}
		}
		main.pick(0, 0, 0, cmap);

		bw.flush();
		bw.write(maxSafetyZone + "\n");
		br.close();
		bw.close();
	}

	public void pick(int posR, int posC, int pickCount, int[][] cmap) {
		if (pickCount == 3) {
			// 벽 3개 세운 경우, 안전 영역 크기 측정하여 최대값 계산
			bfs(cmap);
			return;
		}
		if (posR == N || posC == M) {
			return;
		}
		// 현재 자리 뽑을 경우
		if (cmap[posR][posC] == 0) {
			cmap[posR][posC] = 1;
			if (posC == M - 1) {
				pick(posR + 1, 0, pickCount + 1, cmap);
			} else {
				pick(posR, posC + 1, pickCount + 1, cmap);
			}

		}
		// 현재 자리 뽑지 않을 경우
		cmap[posR][posC] = map[posR][posC];
		if (posC == M - 1) {
			pick(posR + 1, 0, pickCount, cmap);
		} else {
			pick(posR, posC + 1, pickCount, cmap);
		}
	}

	public void bfs(int[][] cmap) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < V; i++) {
			Point p = viruses[i];
			visit[p.r][p.c] = true;
			queue.add(p);
		}

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int r = p.r;
			int c = p.c;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visit[nr][nc]) {
					if (cmap[nr][nc] == 0) {
						visit[nr][nc] = true;
						queue.add(new Point(nr, nc));
					}
				}
			}
		}
		int safetyZoneCount = getSafetyZoneCount(cmap, visit);
		maxSafetyZone = Math.max(maxSafetyZone, safetyZoneCount);
	}

	public int getSafetyZoneCount(int[][] map, boolean[][] visit) {
		int safetyCount = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && !visit[i][j]) {
					safetyCount++;
				}
			}
		}

		return safetyCount;
	}
}

class Point {
	int r, c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
