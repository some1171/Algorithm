package swexpertacademy.practice.microbeisolation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 자료구조 쓰지 않고 객체 배열 사용한 경우, 실행 시간과 메모리 모두 증가
public class Solution2 {
	public static int T, N, M, K;
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			Node[][] map = new Node[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = new Node(0, 0, 0);
				}
			}

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				map[r][c] = new Node(n, d - 1, n);
			}

			for (int m = 0; m < M; m++) {
				Node[][] nextMap = new Node[N][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						nextMap[i][j] = new Node(0, 0, 0);
					}
				}

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j].n == 0) {
							continue;
						}

						int nr = i + dr[map[i][j].d];
						int nc = j + dc[map[i][j].d];
						int nn = map[i][j].n;
						int nd = map[i][j].d;

						if (nr == 0) {
							nd = 1;
							nn = nn / 2;
						} else if (nr == N - 1) {
							nd = 0;
							nn = nn / 2;
						} else if (nc == 0) {
							nd = 3;
							nn = nn / 2;
						} else if (nc == N - 1) {
							nd = 2;
							nn = nn / 2;
						}

						nextMap[nr][nc] = nextMap[nr][nc].add(nn, nd);
					}
				}

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						map[i][j] = nextMap[i][j];
					}
				}
			}

			int totalMicrobe = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].n != 0) {
						totalMicrobe += map[i][j].n;
					}
				}
			}

			bw.flush();
			bw.write("#" + (t + 1) + " " + totalMicrobe + "\n");
		}

		br.close();
		bw.close();
	}
}

class Node {
	int n, d, m;

	public Node(int n, int d, int m) {
		this.n = n;
		this.d = d;
		this.m = m;
	}

	public Node add(int n, int d) {
		this.n = this.n + n;
		if (m <= n) {
			m = n;
			this.d = d;
		}

		return this;
	}
}