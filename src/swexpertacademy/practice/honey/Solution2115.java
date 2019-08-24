package swexpertacademy.practice.honey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution2115 {
	public static int T, N, M, C, maxRevenue, curRevenue, oneRevenue, twoRevenue;
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		Solution2115 s = new Solution2115();

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxRevenue = 0;
			// 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 두점 선택
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - M + 1; j++) {
					Point p1 = new Point(i, j);

					int[] honey = new int[M];
					int r = p1.r;
					int c = p1.c;
					int idx = 0;
					for (int k = c; k < c + M; k++) {
						honey[idx] = map[r][k];
						idx++;
					}
					curRevenue = 0;
					s.getMax(honey, 0, 0, 0);
					oneRevenue = curRevenue;
					
					for (int k = i + 1; k < N; k++) {
						for (int l = 0; l < N - M + 1; l++) {
							Point p2 = new Point(k, l);
							
							r = p2.r;
							c = p2.c;
							idx = 0;
							for (int m = c; m < c + M; m++) {
								honey[idx] = map[r][m];
								idx++;
							}
							curRevenue = 0;
							s.getMax(honey, 0, 0, 0);
							twoRevenue = curRevenue;
							maxRevenue = Math.max(maxRevenue, oneRevenue + twoRevenue);
						}
					}
				}
			}

			bw.flush();
			bw.write("#" + (tc + 1) + " " + maxRevenue + "\n");
		}
		br.close();
		bw.close();
	}
	
	public void getMax(int[] arr, int idx, int cap, int rvn) {
		if (idx == M) {
			curRevenue = Math.max(curRevenue, rvn);
			return;
		}
		if (cap + arr[idx] <= C) {
			getMax(arr, idx + 1, cap + arr[idx], rvn + arr[idx] * arr[idx]);
		}
		getMax(arr, idx + 1, cap, rvn);
	}
}

class Point {
	int r, c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
