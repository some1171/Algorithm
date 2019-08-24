package swexpertacademy.practice.protectionfilm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution2112 {
	public static int T, W, D, K, chemCount;
	public static boolean[] peek;
	public static int[] chem;
	public static int[][] map;
	public static boolean isSuccess;
	
	public static void main(String[] args) throws IOException {
		Solution2112 solution = new Solution2112();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < T; tc++) {
			isSuccess = false;
			chemCount = 0;
			st = new StringTokenizer(br.readLine().trim());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if (K == 1) {
				isSuccess = true;
				chemCount = 0;
			} else if (solution.doTest(map)) {
				isSuccess = true;
				chemCount = 0;
			} else {
				for (int c = 1; c <= K; c++) {
					if (isSuccess) {
						break;
					}
					if (c == K) {
						chemCount = K;
						break;
					}
					peek = new boolean[D];
					chem = new int[D];
					solution.peek(0, 0, c);
				}
			}
			
			bw.flush();
			bw.write("#" + (tc + 1) + " " + chemCount + "\n");
		}
		
		br.close();
		bw.close();
	}
	
	public void peek(int pos, int currentCount, int totalCount) {
		if (isSuccess) {
			return;
		}
		if (currentCount == totalCount) {
			chooseChem(0);
			if (isSuccess) {
				chemCount = totalCount;
			}
			return;
		}
		if (pos == D) {
			return;
		}
		peek[pos] = true;
		peek(pos + 1, currentCount + 1, totalCount);
		peek[pos] = false;
		peek(pos + 1, currentCount, totalCount);
	}
	
	public void chooseChem(int pos) {
		if (isSuccess) {
			return;
		}
		if (pos == D) {
			int[][] testMap = new int[D][W];
			for (int i = 0; i < D; i++) {
				if (peek[i]) {
					for (int j = 0; j < W; j++) {
						testMap[i][j] = chem[i];
					}
				} else {
					for (int j = 0; j < W; j++) {
						testMap[i][j] = map[i][j];
					}
				}
			}
			
			if (doTest(testMap)) {
				isSuccess = true;
			}
			return;
		}
		if (peek[pos]) {
			chem[pos] = 1;
			chooseChem(pos + 1);
			chem[pos] = 0;
			chooseChem(pos + 1);
		} else {
			chooseChem(pos + 1);
		}
	}
	
	public boolean doTest(int[][] map) {
		for(int i = 0; i < W; i++) {
			int maxLength = 0, length = 0;
			int previous = map[0][i];
			for (int j = 0; j < D; j++) {
				if (previous == map[j][i]) {
					length++;
				} else {
					maxLength = Math.max(maxLength, length);
					length = 1;
				}
				previous = map[j][i];
				if (j == D - 1) {
					maxLength = Math.max(maxLength, length);
				}
			}
			if (maxLength < K) {
				return false;
			}
		}
		
		return true;
	}
}
