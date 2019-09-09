package acmicpc.practice.pipe1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main17070 {
	public static int N, count = 0;
	public static int[] dr = { 0, 1, 1 };
	public static int[] dc = { 1, 0, 1 };
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		Main17070 main = new Main17070();
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
		
		int r1 = 0, c1 = 0, r2 = 0, c2 = 1, d = 0;
		main.dfs(r1, c1, r2, c2, d);
		
		bw.flush();
		bw.write(count + "\n");
		br.close();
		bw.close();
	}

	public void dfs(int r1, int c1, int r2, int c2, int d) {
		if (r2 == N - 1 && c2 == N - 1) {
			count++;
			return;
		}
		if (d == 0) {
			int nr1, nc1, nr2, nc2, nd;
			// 가로로 이동한다.
			nd = d;
			nr1 = r1 + dr[0];
			nc1 = c1 + dc[0];
			nr2 = r2 + dr[0];
			nc2 = c2 + dc[0];
			if (nc2 < N && map[nr2][nc2] == 0) {
				dfs(nr1, nc1, nr2, nc2, nd);
			}
			// 대각성 이동한다.
			nd = 2;
			nr2 = r2 + dr[2];
			nc2 = c2 + dc[2];
			if (nr2 < N && nc2 < N && map[nr2][nc2] == 0 && map[nr2][nc2 - 1] == 0 && map[nr2 - 1][nc2] == 0) {
				dfs(nr1, nc1, nr2, nc2, nd);
			}
		} else if (d == 1) {
			int nr1, nc1, nr2, nc2, nd;
			// 세로로 이동한다.
			nd = d;
			nr1 = r1 + dr[1];
			nc1 = c1 + dc[1];
			nr2 = r2 + dr[1];
			nc2 = c2 + dc[1];
			if (nr2 < N && map[nr2][nc2] == 0) {
				dfs(nr1, nc1, nr2, nc2, nd);
			}
			// 대각선 이동한다.
			nd = 2;
			nr2 = r2 + dr[2];
			nc2 = c2 + dc[2];
			if (nr2 < N && nc2 < N && map[nr2][nc2] == 0 && map[nr2][nc2 - 1] == 0 && map[nr2 -1][nc2] == 0) {
				dfs(nr1, nc1, nr2, nc2, nd);
			}
		} else {
			int nr1, nc1, nr2, nc2, nd;
			// 가로 이동한다.
			nd = 0;
			nr1 = r1 + dr[2];
			nc1 = c1 + dc[2];
			nr2 = r2 + dr[0];
			nc2 = c2 + dc[0];
			if (nc2 < N && map[nr2][nc2] == 0) {
				dfs(nr1, nc1, nr2, nc2, nd);
			}
			// 세로 이동한다.
			nd = 1;
			nr2 = r2 + dr[1];
			nc2 = c2 + dc[1];
			if (nr2 < N && map[nr2][nc2] == 0) {
				dfs(nr1, nc1, nr2, nc2, nd);
			}
			// 대각선 이동한다.
			nd = d;
			nr2 = r2 + dr[2];
			nc2 = c2 + dc[2];
			if (nr2 < N && nc2 < N && map[nr2][nc2] == 0 && map[nr2][nc2 - 1] == 0 && map[nr2 -1][nc2] == 0) {
				dfs(nr1, nc1, nr2, nc2, nd);
			}
		}
	}
}
