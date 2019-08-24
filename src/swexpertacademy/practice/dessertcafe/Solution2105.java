package swexpertacademy.practice.dessertcafe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution2105 {
	public static int T, N, MAX;
	public static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			MAX = -1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int a = 1; a < N; a++) {
						for (int b = 1; b < N; b++) {
							if (j + a < N && j >= b && i + a + b < N && (a + b) * 2 > MAX) {
								boolean isPossible = true;
								boolean[] dessert = new boolean[101];
								int r = i, c = j;
								
								for (int n = 0; n < a; n++) {
									r++;
									c++;
									if (dessert[map[r][c]]) {
										isPossible = false;
										break;
									}
									dessert[map[r][c]] = true;
								}
								
								if (!isPossible) {
									isPossible = true;
									continue;
								}
								
								for (int n = 0; n < b; n++) {
									r++;
									c--;
									if (dessert[map[r][c]]) {
										isPossible = false;
										break;
									}
									dessert[map[r][c]] = true;
								}
								
								if (!isPossible) {
									isPossible = true;
									continue;
								}
								
								for (int n = 0; n < a; n++) {
									r--;
									c--;
									if (dessert[map[r][c]]) {
										isPossible = false;
										break;
									}
									dessert[map[r][c]] = true;
								}
								
								if (!isPossible) {
									isPossible = true;
									continue;
								}
								
								for (int n = 0; n < b; n++) {
									r--;
									c++;
									if (dessert[map[r][c]]) {
										isPossible = false;
										break;
									}
									dessert[map[r][c]] = true;
								}
								
								if (!isPossible) {
									isPossible = true;
									continue;
								}
								
								MAX = (a + b) * 2;
							}
						}
					}
				}
			}
			
			bw.flush();
			bw.write("#" + (tc + 1) + " " + MAX + "\n");
		}
		br.close();
		bw.close();
	}
}
