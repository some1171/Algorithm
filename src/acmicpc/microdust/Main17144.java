package acmicpc.microdust;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main17144 {
	public static int R, C, T, ac1, ac2;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	public static int[][] map = new int[50][50];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		boolean first = true;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					if (first) {
						ac1 = i;
						first = false;
					} else {
						ac2 = i;
					}
				}
			}
		}

		int t = 0;
		while (t < T) {
			int[][] nmap = new int[R][C];
			// 1. 미세먼지를 확산시킨다. (공간 있고 && 공기청정기 없는 곳)
			nmap[ac1][0] = -1;
			nmap[ac2][0] = -1;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == 0 || map[i][j] == -1) {
						continue;
					}
					if (map[i][j] < 5) {
						nmap[i][j] += map[i][j];
						continue;
					}
					int dust = map[i][j];
					int diffusion = dust / 5;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (nc == 0) {
							if (nr == ac1 || nr == ac2) {
								continue;
							}
						}
						if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
							nmap[nr][nc] += diffusion;
							dust -= diffusion;
						}
					}
					nmap[i][j] += dust;
				}
			}
			
			// 2. 공기청정기 작동한다.
			// 2-1. 첫번째 공기 청정기
			for (int i = ac1 - 1; i >= 1; i--) {
				nmap[i][0] = nmap[i - 1][0];
			}
			for (int i = 0; i < C - 1; i++) {
				nmap[0][i] = nmap[0][i + 1];
			}
			for (int i = 0; i < ac1; i++) {
				nmap[i][C - 1] = nmap[i + 1][C - 1];
			}
			for (int i = C - 1; i >= 1; i--) {
				if (i == 1) {
					nmap[ac1][i] = 0;
					continue;
				}
				nmap[ac1][i] = nmap[ac1][i - 1];
			}
			
			// 2-2. 두번째 공기 청정기
			for (int i = ac2 + 1; i < R - 1; i++) {
				nmap[i][0] = nmap[i + 1][0];
			}
			for (int i = 0; i < C - 1; i++) {
				nmap[R - 1][i] = nmap[R - 1][i + 1];
			}
			for (int i = R - 1; i > ac2; i--) {
				nmap[i][C - 1] = nmap[i - 1][C - 1];
			}
			for (int i = C - 1; i >= 1; i--) {
				if (i == 1) {
					nmap[ac2][i] = 0;
					continue;
				}
				nmap[ac2][i] = nmap[ac2][i - 1];
			}
			// 맵 복사
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] = nmap[i][j];
				}
			}
			
			t++;
		}

		// 3. 남은 미세먼지 양을 계산한다.
		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0 || map[i][j] == -1) {
					continue;
				}
				count += map[i][j];
			}
		}

		bw.flush();
		bw.write(count + "\n");
		br.close();
		bw.close();
	}
}
