package acmicpc.snake;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main3190 {
	public static int N, K, L;
	public static int[] dr = { 0, 1, 0, -1 };
	public static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		int[][] map = new int[N][N];
		K = Integer.parseInt(br.readLine().trim());
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine().trim());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = 2;
		}
		int[] positionR = new int[10100];
		int[] positionC = new int[10100];
		char[] cmd = new char[10001];
		L = Integer.parseInt(br.readLine().trim());
		for (int l = 0; l < L; l++) {
			st = new StringTokenizer(br.readLine().trim());
			cmd[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
		}

		int time = 0, tailIndex = 0, hr = 0, hc = 0, hd = 0;
		map[hr][hc] = 1;
		positionR[0] = hr;
		positionC[0] = hc;
		while (true) {
			time++;
			int nr = hr + dr[hd];
			int nc = hc + dc[hd];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1) {
				break;
			}
			if (map[nr][nc] == 2) {
				hr = nr;
				hc = nc;
				map[hr][hc] = 1;
				positionR[time] = hr;
				positionC[time] = hc;
			} else {
				hr = nr;
				hc = nc;
				map[hr][hc] = 1;
				positionR[time] = hr;
				positionC[time] = hc;
				map[positionR[tailIndex]][positionC[tailIndex]] = 0;
				tailIndex++;
			}
			
			// cmd ผ๖วเ
			if (cmd[time] == 'D') {
				hd = (hd + 1) % 4;
			} else if (cmd[time] == 'L') {
				hd = (hd + 3) % 4;
			}
		}

		bw.flush();
		bw.write(time + "\n");
		br.close();
		bw.close();
	}
}
