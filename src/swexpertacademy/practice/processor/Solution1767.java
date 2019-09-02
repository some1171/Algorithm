package swexpertacademy.practice.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution1767 {
	public static int T, N, P, min, maxConnectedProcessor;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, -1, 0, 1 };
	public static int[] pr = new int[144];
	public static int[] pc = new int[144];
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		Solution1767 solution = new Solution1767();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			P = 0;
			min = 0;
			maxConnectedProcessor = 0;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
						pr[P] = i;
						pc[P] = j;
						P++;
					}
				}
			}
			
			solution.dfs(0, map, new boolean[P]);
			
			bw.write("#" + (tc + 1) + " " + min + "\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
	
	public void dfs(int pos, int[][] nmap, boolean[] connected) {
		if (pos == P) {
			// 탐색 완료, 길이 계산 후 최소값 갱신
			int cpCount = 0;
			for (int i = 0; i < P; i++) {
				if (connected[i]) {
					cpCount++;
				}
			}
			int length = 0;
			if (cpCount == maxConnectedProcessor) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (nmap[i][j] == 2) {
							length++;
						}
					}
				}
				min = Math.min(min, length);
			} else if (cpCount > maxConnectedProcessor) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (nmap[i][j] == 2) {
							length++;
						}
					}
				}
				maxConnectedProcessor = cpCount;
				min = length;
			}

			return;
		}
		int r = pr[pos];
		int c = pc[pos];
		boolean isPossible;
		// 위
		isPossible = true;
		for (int i = 0; i < r; i++) {
			if (nmap[i][c] != 0) {
				isPossible = false;
			}
		}
		if (isPossible) {
			for (int i = 0; i < r; i++) {
				nmap[i][c] = 2;
			}
			connected[pos] = true;
			dfs(pos + 1, nmap, connected);
			connected[pos] = false;
			for (int i = 0; i < r; i++) {
				nmap[i][c] = 0;
			}
		}
		// 아래
		isPossible = true;
		for (int i = r + 1; i < N; i++) {
			if (nmap[i][c] != 0) {
				isPossible = false;
			}
		}
		if (isPossible) {
			for (int i = r + 1; i < N; i++) {
				nmap[i][c] = 2;
			}
			connected[pos] = true;
			dfs(pos + 1, nmap, connected);
			connected[pos] = false;
			for (int i = r + 1; i < N; i++) {
				nmap[i][c] = 0;
			}
		}
		// 오른쪽
		isPossible = true;
		for (int i = c + 1; i < N; i++) {
			if (nmap[r][i] != 0) {
				isPossible = false;
			}
		}
		if (isPossible) {
			for (int i = c + 1; i < N; i++) {
				nmap[r][i] = 2;
			}
			connected[pos] = true;
			dfs(pos + 1, nmap, connected);
			connected[pos] = false;
			for (int i = c + 1; i < N; i++) {
				nmap[r][i] = 0;
			}
		}
		// 왼쪽
		isPossible = true;
		for (int i = 0; i < c; i++) {
			if (nmap[r][i] != 0) {
				isPossible = false;
			}
		}
		if (isPossible) {
			for (int i = 0; i < c; i++) {
				nmap[r][i] = 2;
			}
			connected[pos] = true;
			dfs(pos + 1, nmap, connected);
			connected[pos] = false;
			for (int i = 0; i < c; i++) {
				nmap[r][i] = 0;
			}
		}
		// 연결안하는 경우
		dfs(pos + 1, nmap, connected);
	}
}