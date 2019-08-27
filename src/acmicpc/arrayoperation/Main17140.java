package acmicpc.arrayoperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main17140 {
	public static int r, c, k, rSize, cSize;

	public static void main(String[] args) throws IOException {
		Main17140 main = new Main17140();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		int[][] map = new int[100][100];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int t = 0;
		while (true) {
			if (map[r][c] == k) {
				break;
			}
			rSize = main.getRsize(map);
			cSize = main.getCsize(map);
			if (rSize >= cSize) {
				map = main.operationR(map);
			} else {
				map = main.operationC(map);
			}
			
			t++;
			if (t == 101) {
				t = -1;
				break;
			}
		}

		bw.flush();
		bw.write(t + "\n");
		br.close();
		bw.close();
	}

	public int[][] operationR(int[][] map) {
		int[][] nmap = new int[100][100];
		for (int i = 0; i < rSize; i++) {
			int[] counts = new int[101];
			// 1. 행에 대한 갯수를 센다.
			for (int j = 0; j < cSize; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				counts[map[i][j]]++;
			}
			// 2. 해당 갯수로 다음 맵의 행을 만든다.
			int idx = 0;
			for (int count = 1; count < 101; count++) {
				for (int number = 1; number < 101; number++) {
					if (counts[number] == count) {
						if (idx == 100) {
							break;
						}
						nmap[i][idx++] = number;
						nmap[i][idx++] = count;
					}
				}
			}
		}
		return nmap;
	}

	public int[][] operationC(int[][] map) {
		int[][] nmap = new int[100][100];
		for (int i = 0; i < cSize; i++) {
			int[] counts = new int[101];
			for (int j = 0; j < rSize; j++) {
				if (map[j][i] == 0) {
					continue;
				}
				counts[map[j][i]]++;
			}
			int idx = 0;
			for (int count = 1; count < 101; count++) {
				for (int number = 1; number < 101; number++) {
					if(counts[number] == count) {
						if (idx == 100) {
							break;
						}
						nmap[idx++][i] = number;
						nmap[idx++][i] = count;
					}
				}
			}
		}
		return nmap;
	}

	public int getRsize(int[][] map) {
		for (int i = 99; i >= 0; i--) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] != 0) {
					return i + 1;
				}
			}
		}
		
		return 0;
	}

	public int getCsize(int[][] map) {
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
