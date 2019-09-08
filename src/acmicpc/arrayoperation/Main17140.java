package acmicpc.arrayoperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main17140 {
	public static int R, C, K, T;
	public static int[][] map = new int[100][100];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken()) - 1;
		C = Integer.parseInt(st.nextToken()) - 1;
		K = Integer.parseInt(st.nextToken());
		T = 0;
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			if (T > 100) {
				T = -1;
				break;
			}
			if (map[R][C] == K) {
				break;
			}
			int rSize = getRsize(map);
			int cSize = getCsize(map);
			if (rSize >= cSize) {
				map = rOperation(map, rSize, cSize);
			} else {
				map = cOperation(map, rSize, cSize);
			}
			
			T++;
		}

		bw.write(T + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

	public static int[][] rOperation(int[][] map, int rSize, int cSize) {
		int[][] nmap = new int[100][100];

		for (int i = 0; i < rSize; i++) {
			int[] count = new int[101];
			for (int j = 0; j < cSize; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				count[map[i][j]]++;
			}
			int idx = 0;
			for (int c = 1; c < 101; c++) {
				for (int n = 0; n < 101; n++) {
					if (count[n] == c) {
						nmap[i][idx++] = n;
						nmap[i][idx++] = c;
					}
				}
			}
		}
		return nmap;
	}

	public static int[][] cOperation(int[][] map, int rSize, int cSize) {
		int[][] nmap = new int[100][100];

		for (int i = 0; i < cSize; i++) {
			int[] count = new int[101];
			for (int j = 0; j < rSize; j++) {
				if (map[j][i] == 0) {
					continue;
				}
				count[map[j][i]]++;
			}
			int idx = 0;
			for (int c = 1; c < 101; c++) {
				for (int n = 0; n < 101; n++) {
					if (count[n] == c) {
						nmap[idx++][i] = n;
						nmap[idx++][i] = c;
					}
				}
			}
		}
		return nmap;
	}

	public static int getRsize(int[][] map) {
		for (int i = 99; i >= 0; i--) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] != 0) {
					return i + 1;
				}
			}
		}
		return 0;
	}

	public static int getCsize(int[][] map) {
		for (int i = 99; i >= 0; i--) {
			for (int j = 0; j < 100; j++) {
				if (map[j][i] != 0) {
					return i + 1;
				}
			}
		}
		return 0;
	}
}
