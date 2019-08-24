package swexpertacademy.practice.hikingtrail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution1949 {
	public static int T, N, K;
	public static int L;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, -1, 0, 1 };
	public static int[][] map;
	public static LinkedList<Point> topList = new LinkedList<Point>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			L = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			int top = 1;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(map[i][j], top);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == top) {
						topList.add(new Point(i, j, 0));
					}
				}
			}
			
			Solution1949 solution = new Solution1949();
			solution.dfs();
			topList.clear();
			bw.write("#" + (t + 1) + " " + (L + 1) + "\n");
			bw.flush();
		}

		br.close();
		bw.close();
	}

	public void dfs() {
		for (int index = 0; index < topList.size(); index++) {
			Stack<Point> stack = new Stack<Point>();
			Point start = topList.get(index);

			for (int k = 0; k <= K; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						int temp = map[i][j];
						int length = 0;
						map[i][j] = map[i][j] - k;
						stack.push(start);
						
						while (!stack.isEmpty()) {
							Point p = stack.pop();
							
							for (int d = 0; d < 4; d++) {
								int nr = p.r + dr[d];
								int nc = p.c + dc[d];
								int nl = p.l + 1;
								
								if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
									if (map[nr][nc] < map[p.r][p.c]) {
										stack.push(new Point(nr, nc, nl));
										length = Math.max(length, nl);
									}
								}
							}
						}
						L = Math.max(L, length);
						map[i][j] = temp;
					}
				}
			}
		}
	}
}

class Point {
	int r, c, l;

	Point(int r, int c, int l) {
		this.r = r;
		this.c = c;
		this.l = l;
	}
}
