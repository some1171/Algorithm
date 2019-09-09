package acmicpc.practice.dragoncurve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15685 {
	public static int N;
	public static int[] dr = { 0, -1, 0, 1 };
	public static int[] dc = { 1, 0, -1, 0 };
	public static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		Main15685 main = new Main15685();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		visit = new boolean[101][101];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int length = (int) Math.pow(2, g);
			int[] direction = new int[length];
			direction[0] = d;
			if (g != 0) {
				for (int j = 1; j <= g; j++) {
					int currentLength = (int) Math.pow(2, j);
					for (int k = (int) Math.pow(2, j - 1); k < currentLength; k++) {
						direction[k] = direction[currentLength - 1 - k] + 1;
						if (direction[k] == 4) {
							direction[k] = 0;
						}
					}
				}
			}
			main.drawDragonCurve(r, c, direction);
		}
		int count = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (!visit[i][j]) {
					continue;
				}
				if (visit[i][j + 1] && visit[i + 1][j] && visit [i + 1][j + 1]) {
					count++;
				}
			}
		}
		bw.flush();
		bw.write(count +" ");
		
		br.close();
		bw.close();
	}
	
	public void drawDragonCurve(int r, int c, int[] direction) {
		visit[r][c] = true;
		
		for (int d : direction) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			visit[nr][nc] = true;
			r = nr;
			c = nc;
		}
	}
}
