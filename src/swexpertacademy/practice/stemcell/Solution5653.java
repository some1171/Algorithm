package swexpertacademy.practice.stemcell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5653 {
	public static int T, N, M, K;
	public static int[] dr = { -1, 0, 0, 1 };
	public static int[] dc = { 0, -1, 1, 0 };
	public static int[][] map;
	public static boolean[][] visit;
	public static PriorityQueue<Cell> queue;
	public static Queue<Cell> usedCells;

	public static void main(String[] args) throws IOException {
		Solution5653 solution = new Solution5653();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int totalCellCount = 0;
			map = new int[700][700];
			visit = new boolean[700][700];
			queue = new PriorityQueue<Cell>();
			usedCells = new LinkedList<Cell>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			for (int i = 300; i < 300 + N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 300; j < 300 + M; j++) {
					int v = Integer.parseInt(st.nextToken());
					if (v != 0) {
						map[i][j] = v;
						visit[i][j] = true;
						queue.add(new Cell(i, j, v, 0));
					}
				}
			}
			solution.bfs();
			while(!usedCells.isEmpty()) {
				Cell c = usedCells.poll();
				if (c.dt > K) {
					totalCellCount++;
				}
			}

			bw.flush();
			bw.write("#" + (tc + 1) + " " + totalCellCount + "\n");
		}

		br.close();
		bw.close();
	}

	public void bfs() {
		while (!queue.isEmpty()) {
			Cell c = queue.poll();
			if (c.t == K) {
				usedCells.add(c);
				continue;
			}
			if (c.t == c.at) {
				for(int d = 0; d < 4; d++) {
					int nr = c.r + dr[d];
					int nc = c.c + dc[d];
					
					if (!visit[nr][nc]) {
						visit[nr][nc] = true;
						queue.add(new Cell(nr, nc, c.v, c.t + 1));
					}
				}
				usedCells.add(c);
			} else {
				c.t += 1;
				queue.add(c);
			}
		}
	}
}

class Cell implements Comparable<Cell> {
	int r, c, v, t, at, dt;

	public Cell(int r, int c, int v, int t) {
		this.r = r;
		this.c = c;
		this.v = v;
		this.t = t;
		at = t + v;
		dt = at + v;
	}

	@Override
	public int compareTo(Cell c) {
		if (this.t < c.t) {
			return -1;
		} else if (this.t == c.t) {
			if (this.v > c.v) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}
