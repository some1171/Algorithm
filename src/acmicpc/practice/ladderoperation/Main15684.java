package acmicpc.practice.ladderoperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15684 {
	public static int N, M, H, count = 0;
	public static boolean isSuccess = false;

	public static void main(String[] args) throws IOException {
		Main15684 main = new Main15684();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		boolean map[][] = new boolean[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = true;
		}
		if (main.check(map)) {
			isSuccess = true;
			count = 0;
		} else {
			int totalPeekCount = 1;
			while (totalPeekCount < 4) {
				if (isSuccess) {
					break;
				}
				main.dfs(1, 1, main.copyMap(map), 0, totalPeekCount);
				totalPeekCount++;
			}
		}
		if (!isSuccess) {
			count = -1;
		}
		
		bw.write(count + "\n");
		
		br.close();
		bw.close();
	}
	
	public void dfs(int posR, int posC, boolean[][] nmap, int currentPickCount, int totalPickCount) {
		if (isSuccess) {
			return;
		}
		if (currentPickCount == totalPickCount) {
			// 갯수 만큼 뽑기 완료
			// 체크하고 종료
			if (check(nmap)) {
				isSuccess = true;
				count = totalPickCount;
			}
			return;
		}
		if (posC == N) {
			return;
		}
		// 현재 이미 연결되어 있는가? 그럼 패스
		// 현재 뽑는 경우
		// 현재 안뽑는경우
		if (!nmap[posR][posC]) {
			nmap[posR][posC] = true;
			if (posR == H) {
				dfs(1, posC + 1, nmap, currentPickCount + 1, totalPickCount);
			} else {
				dfs(posR + 1, posC, nmap, currentPickCount + 1, totalPickCount);
			}
			nmap[posR][posC] = false;
			if (posR == H) {
				dfs(1, posC + 1, nmap, currentPickCount, totalPickCount);
			} else {
				dfs(posR + 1, posC, nmap, currentPickCount, totalPickCount);
			}
		} else {
			if (posR == H) {
				dfs(1, posC + 1, nmap, currentPickCount, totalPickCount);
			} else {
				dfs(posR + 1, posC, nmap, currentPickCount, totalPickCount);
			}
		}
	}
	
	public boolean check(boolean[][] map) {
		for (int i = 1; i <= N; i++) {
			int r = 1, c = i;
			while (r <= H) {
				if (map[r][c]) {
					c = c + 1;
				} else {
					if (c != 1 && map[r][c - 1]) {
						c = c - 1;
					}
				}
				r++;
				if (r == H + 1) {
					if (c != i) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean[][] copyMap(boolean[][] map) {
		boolean[][] nmap = new boolean[H + 1][N + 1];
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= N; j++) {
				nmap[i][j] = map[i][j];
			}
		}
		return nmap;
	}
}
