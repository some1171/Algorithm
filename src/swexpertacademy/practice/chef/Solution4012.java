package swexpertacademy.practice.chef;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution4012 {
	public static int T, N, M, minDiff;
	public static int[][] map;
	public static boolean[] peek;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		Solution4012 solution = new Solution4012();

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = N / 2;
			minDiff = Integer.MAX_VALUE;
			map = new int[N][N];
			peek = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			solution.peek(0, 0);
			bw.flush();
			bw.write("#" + (tc + 1) + " " + minDiff + "\n");
		}

		br.close();
		bw.close();
	}

	public void peek(int pos, int count) {
		if (count == M) {
			int sum1 = 0;
			int sum2 = 0;

			for (int i = 0; i < N; i++) {
				if (peek[i]) {
					for (int j = 0; j < N; j++) {
						if (i == j) {
							continue;
						}
						if (peek[j]) {
							sum1 += map[i][j];
						}
					}
				} else {
					for (int j = 0; j < N; j++) {
						if (i == j) {
							continue;
						}
						if (!peek[j]) {
							sum2 += map[i][j];
						}
					}
				}
			}
			minDiff = Math.min(minDiff, Math.abs(sum1 - sum2));
			return;
		}
		if (pos == N) {
			return;
		}
		peek[pos] = true;
		peek(pos + 1, count + 1);
		peek[pos] = false;
		peek(pos + 1, count);
	}
}
