package swexpertacademy.practice.securityservice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2117 {
	public static int T, N, M;
	public static int dr[] = { -1, 0, 0, 1 };
	public static int dc[] = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			int maxCount = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// bfs
			// �߽��� N*N ���� ���Ͽ�
			// K = 1 ~ N�� ���Ͽ�
			// bfs �Ͽ� �ƽø� ����, Ž�� ���� ����� ���ڸ� Ž�� ����
			// visit�� �� ���̽��� �ʱ�ȭ
			// �ٸ� ���: �Ϲ����� bfs���� Ȩ����Ʈ�� ���� �������κ��� �Ÿ��� k ���� ������ �ش� Ȩ ���� ��üŽ���ʿ���� 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k < N + 2; k++) {
						Queue<Point> queue = new LinkedList<Point>();
						boolean[][] visit = new boolean[N][N];
						int cost = 0, count = 0, revenue = 0;
						queue.add(new Point(i, j, 1));
						visit[i][j] = true;
						if (map[i][j] == 1) {
							revenue += M;
							count++;
						}

						while (!queue.isEmpty()) {
							Point p = queue.poll();
							for (int d = 0; d < 4; d++) {
								int nr = p.r + dr[d];
								int nc = p.c + dc[d];
								int nl = p.l + 1;
								
								if (nl > k) {
									break;
								}
								
								if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc]) {
									queue.add(new Point(nr, nc, nl));
									visit[nr][nc] = true;
									if (map[nr][nc] == 1) {
										revenue += M;
										count++;
									}
								}
							}
						}
						cost = k * k + (k - 1) * (k - 1);
						if (cost <= revenue) {
							maxCount = Math.max(maxCount, count);
						}
					}

				}
			}

			bw.flush();
			bw.write("#" + (tc + 1) + " " + maxCount + "\n");
		}
		br.close();
		bw.close();
	}
}

class Point {
	int r, c, l;

	public Point(int r, int c, int l) {
		this.r = r;
		this.c = c;
		this.l = l;
	}
}