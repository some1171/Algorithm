package swexpertacademy.practice.pinball;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution5650 {
	public static int T, N;
	public static int[] dr = { -1, 0, 0, 1 };
	public static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int maxPoint = 0;
			ArrayList<Hole> wormholes = new ArrayList<Hole>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= 6 && map[i][j] <= 10) {
						wormholes.add(new Hole(i, j, map[i][j]));
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0) {
						continue;
					}
					for (int d = 0; d < 4; d++) {
						Ball ball = new Ball(i, j, d);
						int startR = i, startC = j;
						boolean flag = false;
						while(true) {
							int r = ball.r;
							int c = ball.c;
							int current = map[r][c];
							
							if (flag && r == startR && c == startC) {
								maxPoint = Math.max(maxPoint, ball.p);
								break;
							}
							if (!flag) {
								flag = true;
							}
							if (current == -1) {
								maxPoint = Math.max(maxPoint, ball.p);
								break;
							}
							if (current == 1) {
								ball.p++;
								if (ball.d == 0) {
									ball.d = 3;
								} else if (ball.d == 1) {
									ball.d = 0;
								} else if (ball.d == 2) {
									ball.d = 1;
								} else {
									ball.d = 2;
								}
							} else if (current == 2) {
								ball.p++;
								if (ball.d == 0) {
									ball.d = 2;
								} else if (ball.d == 1) {
									ball.d = 3;
								} else if (ball.d == 2) {
									ball.d = 1;
								} else {
									ball.d = 0;
								}
							} else if (current == 3) {
								ball.p++;
								if (ball.d == 0) {
									ball.d = 1;
								} else if (ball.d == 1) {
									ball.d = 2;
								} else if (ball.d == 2) {
									ball.d = 3;
								} else {
									ball.d = 0;
								}
							} else if (current == 4) {
								ball.p++;
								if (ball.d == 0) {
									ball.d = 3;
								} else if (ball.d == 1) {
									ball.d = 2;
								} else if (ball.d == 2) {
									ball.d = 0;
								} else {
									ball.d = 1;
								}
							} else if (current == 5) {
								ball.p++;
								if (ball.d == 0) {
									ball.d = 3;
								} else if (ball.d == 1) {
									ball.d = 2;
								} else if (ball.d == 2) {
									ball.d = 1;
								} else {
									ball.d = 0;
								}
							} else if (current >= 6 && current <= 10) {
								for (Hole h : wormholes) {
									if (h.n == current && (h.r != r || h.c != c)) {
										ball.r = h.r;
										ball.c = h.c;
									}
								}
							}
							
							int nr = ball.r + dr[ball.d];
							int nc = ball.c + dc[ball.d];
							if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
								ball.p++;
								if (ball.d == 0) {
									ball.d = 3;
									nr = nr + dr[ball.d];
									nc = nc + dc[ball.d];
								} else if (ball.d == 1) {
									ball.d = 2;
									nr = nr + dr[ball.d];
									nc = nc + dc[ball.d];
								} else if (ball.d == 2) {
									ball.d = 1;
									nr = nr + dr[ball.d];
									nc = nc + dc[ball.d];
								} else {
									ball.d = 0;
									nr = nr + dr[ball.d];
									nc = nc + dc[ball.d];
								}
							}
							ball.r = nr;
							ball.c = nc;
						}
					}
				}
			}

			bw.flush();
			bw.write("#" + (tc + 1) + " " + maxPoint + "\n");
		}

		br.close();
		bw.close();
	}
}

class Ball {
	int r, c, d, p;

	public Ball(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
		this.p = 0;
	}
}

class Hole {
	int r, c, n;
	
	public Hole(int r, int c, int n) {
		this.r = r;
		this.c = c;
		this.n = n;
	}
}