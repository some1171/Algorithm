package acmicpc.robotcleaner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14503 {
	public static int N, M;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int r, c, d;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cleanCount = 0;
		boolean isExist = true;
		boolean[][] visit = new boolean[N][M];
		while (isExist) {
			if (!visit[r][c]) {
				cleanCount++;
				visit[r][c] = true;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr, nc;
				if (d == 0) {
					d = 3;
					nr = r + dr[3];
					nc = c + dc[3];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0 && !visit[nr][nc]) {
						r = nr;
						c = nc;
						break;
					}
				} else if (d == 1) {
					d = 0;
					nr = r + dr[0];
					nc = c + dc[0];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0 && !visit[nr][nc]) {
						r = nr;
						c = nc;
						break;
					}
				} else if (d == 2) {
					d = 1;
					nr = r + dr[1];
					nc = c + dc[1];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0 && !visit[nr][nc]) {
						r = nr;
						c = nc;
						break;
					}
				} else {
					d = 2;
					nr = r + dr[2];
					nc = c + dc[2];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0 && !visit[nr][nc]) {
						r = nr;
						c = nc;
						break;
					}
				}
				if (i == 3) {
					nr = r - dr[d];
					nc = c - dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
						r = nr;
						c = nc;
					} else {
						isExist = false;
					}
				}
			}
		}
		
		bw.flush();
		bw.write(cleanCount + "\n");
		br.close();
		bw.close();
	}
}
