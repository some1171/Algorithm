package acmicpc.practice.easy2048;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main12100 {
	public static int N, max = 0, map[][];
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		Main12100 main = new Main12100();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		main.dfs(0, map);
		
		bw.flush();
		bw.write(max + "\n");
		br.close();
		bw.close();
	}

	public void dfs(int count, int[][] map) {
		if (count == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, map[i][j]);
				}
			}
			return;
		}

		dfs(count + 1, moveUp(map));
		dfs(count + 1, moveDown(map));
		dfs(count + 1, moveLeft(map));
		dfs(count + 1, moveRight(map));
	}

	public int[][] moveUp(int[][] map) {
		boolean[][] merged = new boolean[N][N];
		int[][] nmap = new int[N][N];
		for (int j = 0; j < N; j++) {
			nmap[0][j] = map[0][j];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				int nr = i;
				int nc = j;
				while (true) {
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || nmap[nr][nc] != 0) {
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
							nr -= dr[0];
							nc -= dc[0];
							nmap[nr][nc] = map[i][j];
						} else {
							if (nmap[nr][nc] == map[i][j]) {
								if (!merged[nr][nc]) {
									nmap[nr][nc] += map[i][j];
									merged[nr][nc] = true;
								} else {
									nr -= dr[0];
									nc -= dc[0];
									nmap[nr][nc] = map[i][j];
								}
							} else {
								nr -= dr[0];
								nc -= dc[0];
								nmap[nr][nc] = map[i][j];
							}
						}
						break;
					}
					nr += dr[0];
					nc += dc[0];
				}
			}
		}
		return nmap;
	}

	public int[][] moveDown(int[][] map) {
		boolean[][] merged = new boolean[N][N];
		int[][] nmap = new int[N][N];
		for (int j = 0; j < N; j++) {
			nmap[N - 1][j] = map[N - 1][j];
		}
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				int nr = i;
				int nc = j;
				while (true) {
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || nmap[nr][nc] != 0) {
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
							nr -= dr[1];
							nc -= dc[1];
							nmap[nr][nc] = map[i][j];
						} else {
							if (nmap[nr][nc] == map[i][j]) {
								if (!merged[nr][nc]) {
									nmap[nr][nc] += map[i][j];
									merged[nr][nc] = true;
								} else {
									nr -= dr[1];
									nc -= dc[1];
									nmap[nr][nc] = map[i][j];
								}
							} else {
								nr -= dr[1];
								nc -= dc[1];
								nmap[nr][nc] = map[i][j];
							}
						}
						break;
					}
					nr += dr[1];
					nc += dc[1];
				}
			}
		}
		return nmap;
	}

	public int[][] moveLeft(int[][] map) {
		boolean[][] merged = new boolean[N][N];
		int[][] nmap = new int[N][N];
		for (int j = 0; j < N; j++) {
			nmap[j][0] = map[j][0];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[j][i] == 0) {
					continue;
				}
				int nr = j;
				int nc = i;
				while (true) {
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || nmap[nr][nc] != 0) {
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
							nr -= dr[2];
							nc -= dc[2];
							nmap[nr][nc] = map[j][i];
						} else {
							if (nmap[nr][nc] == map[j][i]) {
								if (!merged[nr][nc]) {
									nmap[nr][nc] += map[j][i];
									merged[nr][nc] = true;
								} else {
									nr -= dr[2];
									nc -= dc[2];
									nmap[nr][nc] = map[j][i];
								}
							} else {
								nr -= dr[2];
								nc -= dc[2];
								nmap[nr][nc] = map[j][i];
							}
						}
						break;
					}
					nr += dr[2];
					nc += dc[2];
				}
			}
		}
		return nmap;
	}

	public int[][] moveRight(int[][] map) {
		boolean[][] merged = new boolean[N][N];
		int[][] nmap = new int[N][N];
		for (int j = 0; j < N; j++) {
			nmap[j][N - 1] = map[j][N - 1];
		}
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if (map[j][i] == 0) {
					continue;
				}
				int nr = j;
				int nc = i;
				while (true) {
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || nmap[nr][nc] != 0) {
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
							nr -= dr[3];
							nc -= dc[3];
							nmap[nr][nc] = map[j][i];
						} else {
							if (nmap[nr][nc] == map[j][i]) {
								if (!merged[nr][nc]) {
									nmap[nr][nc] += map[j][i];
									merged[nr][nc] = true;
								} else {
									nr -= dr[3];
									nc -= dc[3];
									nmap[nr][nc] = map[j][i];
								}
							} else {
								nr -= dr[3];
								nc -= dc[3];
								nmap[nr][nc] = map[j][i];
							}
						}
						break;
					}
					nr += dr[3];
					nc += dc[3];
				}
			}
		}
		return nmap;
	}
}
