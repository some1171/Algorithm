package acmicpc.practice.tetromino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, maxSum;
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };
	public static int[][] map;
	public static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		maxSum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = true;
				main.dfs(i, j, map[i][j], 0, visit);
				main.bfs(i, j, map[i][j]);
				visit[i][j] = false;
			}
		}

		bw.flush();
		bw.write(maxSum + "\n");
		br.close();
		bw.close();
	}

	public void dfs(int r, int c, int sum, int count, boolean[][] visit) {
		if (count == 3) {
			maxSum = Math.max(maxSum, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visit[nr][nc]) {
				visit[nr][nc] = true;
				dfs(nr, nc, sum + map[nr][nc], count + 1, visit);
				visit[nr][nc] = false;
			}
		}
	}
	
	public void bfs(int r, int c, int sum) {
		for (int i = 0; i < 4; i++) {
			int currentSum = sum;
			int count = 0;
			
			for (int j = 0; j < 4; j++) {
				if (j == i) {
					continue;
				}
				int nr = r + dr[j];
				int nc = c + dc[j];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					currentSum += map[nr][nc];
					count++;
				} else {
					break;
				}
				if (count == 3) {
					maxSum = Math.max(maxSum, currentSum);
				}
			}
		}
	}
}
