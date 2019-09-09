package acmicpc.practice.coloredpaper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static int min = 100;
	public static int[] paper = { 0, 5, 5, 5, 5, 5 };
	public static int[][] map = new int[10][10];

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		main.dfs(0, 0);
		if (min == 100) {
			min = -1;
		}
		bw.write(min + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
	
	public void dfs(int idx, int cnt) {
		if (idx == 100) {
			min = Math.min(min, cnt);
			return;
		}
		int r = idx / 10;
		int c = idx % 10;
		if (min <= cnt) {
			return;
		}
		
		if (map[r][c] == 1) {
			for (int size = 5; size > 0; size--) {
				if (paper[size] <= 0) {
					continue;
				}
				if (check(r, c, size)) {
					fill(r, c, size, 0);
					paper[size]--;
					dfs(idx + 1, cnt + 1);
					fill(r, c, size, 1);
					paper[size]++;
				}
			}
		} else {
			dfs(idx + 1, cnt);
		}
	}
	
	public boolean check(int r, int c, int len) {
		if (r + len > 10 || c + len > 10) {
			return false;
		}
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void fill(int r, int c, int len, int status) {
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				map[i][j] = status;
			}
		}
	}
}
