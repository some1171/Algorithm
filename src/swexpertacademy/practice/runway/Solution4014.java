package swexpertacademy.practice.runway;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution4014 {
	public static int T, N, X, answer;
	public static int[][] map;
	
	public static void main(String[] args) throws IOException {
		Solution4014 solution = new Solution4014();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			answer = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				int previousH = map[i][0];
				boolean[] visit = new boolean[N];
				boolean flag = true;
				
				for (int j = 0; j < N; j++) {
					int currentH = map[i][j];
					int diff = currentH - previousH;
					if (diff > 1 || diff < -1) {
						flag = false;
						break;
					}
					if (diff == 1) {
						if(solution.getBackwardFlatDistance(i, j - 1, 1) < X) {
							flag = false;
							break;
						}
						for (int k = j - 1; k >= j - X; k--) {
							if (visit[k]) {
								flag = false;
								break;
							}
						}
						for (int k = j - 1; k >= j - X; k--) {
							visit[k] = true;
						}
					} else if (diff == -1) {
						if(solution.getForwardFlatDistance(i, j, 1) < X) {
							flag = false;
							break;
						}
						for (int k = j; k < j + X; k++) {
							visit[k] = true;
						}
					}
					previousH = currentH;
				}
				if (flag) {
					answer++;
				}
			}
			
			for (int i = 0; i < N; i++) {
				int previousH = map[0][i];
				boolean[] visit = new boolean[N];
				boolean flag = true;
				
				for (int j = 0; j < N; j++) {
					int currentH = map[j][i];
					int diff = currentH - previousH;
					if (diff > 1 || diff < -1) {
						flag = false;
						break;
					}
					if (diff == 1) {
						if(solution.getBackwardFlatDistance(j - 1, i, -1) < X) {
							flag = false;
							break;
						}
						for (int k = j - 1; k >= j - X; k--) {
							if (visit[k]) {
								flag = false;
								break;
							}
						}
						for (int k = j - 1; k >= j - X; k--) {
							visit[k] = true;
						}
					} else if (diff == -1) {
						if(solution.getForwardFlatDistance(j, i, -1) < X) {
							flag = false;
							break;
						}
						for (int k = j; k < j + X; k++) {
							visit[k] = true;
						}
					}
					previousH = currentH;
				}
				if (flag) {
					answer++;
				}
			}
			
			bw.flush();
			bw.write("#" + (tc + 1) + " " + answer + "\n");
		}
		
		br.close();
		bw.close();
	}
	
	public int getForwardFlatDistance(int r, int c, int d) {
		int distance = 0, previousH = map[r][c];
		
		if (d == 1) {
			for (int i = c; i < N; i++) {
				int currentH = map[r][i];
				if (previousH != currentH) {
					return distance;
				}
				distance++;
			}
		} else if (d == -1) {
			for (int i = r; i < N; i++) {
				int currentH = map[i][c];
				if (previousH != currentH) {
					return distance;
				}
				distance++;
			}
		}
		
		return distance;
	}
	
	public int getBackwardFlatDistance(int r, int c, int d) {
		int distance = 0, currentH = map[r][c];
		
		if (d == 1) {
			for (int i = c; i >= 0; i--) {
				int previousH = map[r][i];
				if (previousH != currentH) {
					return distance;
				}
				distance++;
			}
		} else if (d == -1) {
			for (int i = r; i >= 0; i--) {
				int previousH = map[i][c];
				if (previousH != currentH) {
					return distance;
				}
				distance++;
			}
		}
		
		return distance;
	}
}
