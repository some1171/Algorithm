package acmicpc.practice.population;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16234 {
	public static int N, L, R, sum, unionIndex, moveCount, unionCount;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, -1, 0, 1 };
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		Main16234 main = new Main16234();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		moveCount = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int maxUnionIndex = N * N + 1;
		boolean isUpdated = true;
		while (isUpdated) {
			isUpdated = false;
			int[][] union = new int[N][N];
			int[] avgs = new int[maxUnionIndex];
			unionIndex = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (union[i][j] == 0) {
						sum = 0;
						unionCount = 0;
						main.bfs(i, j, union);
						int avg = sum / unionCount;
						avgs[unionIndex] = avg;
						unionIndex++;
					}
				}
			}
			
			if (unionIndex < maxUnionIndex) {
				isUpdated = true;
				moveCount++;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						map[i][j] = avgs[union[i][j]];
					}
				}
			}
		}

		bw.write(moveCount + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

	public void bfs(int startR, int startC, int[][] union) {
		Queue<Point> queue = new LinkedList<Point>();
		union[startR][startC] = unionIndex;
		queue.add(new Point(startR, startC));
		sum += map[startR][startC];
		unionCount++;

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int r = p.r;
			int c = p.c;
			int population = map[r][c];
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && union[nr][nc] == 0) {
					int nextPopulation = map[nr][nc];
					int diff = Math.abs(population - nextPopulation);
					if (diff >= L && diff <= R) {
						union[nr][nc] = unionIndex;
						queue.add(new Point(nr, nc));
						sum += map[nr][nc];
						unionCount++;
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
