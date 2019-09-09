package acmicpc.practice.babyshark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main16236 {
	public static int N, M;
	public static int[] dr = { -1, 0, 0, 1 };
	public static int[] dc = { 0, -1, 1, 0 };
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		Main16236 main = new Main16236();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		M++;
		map = new int[N][N];
		Shark shark = new Shark(0, 0);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark.r = i;
					shark.c = j;
					map[i][j] = 0;
				} else if (map[i][j] != 0) {
					M++;
				}
			}
		}
		
		main.bfs(shark);
		bw.write(shark.time + "\n");
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public void bfs(Shark shark) {
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		boolean[][] visit = new boolean[N][N];
		visit[shark.r][shark.c] = true;
		queue.add(new Point(shark.r, shark.c, 0));
		
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int r = p.r;
			int c = p.c;
			int t = p.t;
			if (map[r][c] != 0 && map[r][c] < shark.size) {
				shark.move(r, c, t);
				shark.eat();
				map[r][c] = 0;
				M--;
				queue.clear();
				visit = new boolean[N][N];
				visit[shark.r][shark.c] = true;
				queue.add(new Point(shark.r, shark.c, 0));
			} else {
				if (M == 0) {
					break;
				}
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc] && map[nr][nc] <= shark.size) {
						visit[nr][nc] = true;
						queue.add(new Point(nr, nc, t + 1));
					}
				}
			}
		}
	}
}

class Shark {
	int r, c, size, count, time;
	
	public Shark(int r, int c) {
		this.r = r;
		this.c = c;
		this.size = 2;
		this.count = 0;
		this.time = 0;
	}
	
	public void eat() {
		count++;
		if (count == size) {
			count = 0;
			size++;
		}
	}
	
	public void move(int r, int c, int t) {
		this.r = r;
		this.c = c;
		time += t;
	}
}

class Point implements Comparable<Point>{
	int r, c, t;
	
	public Point(int r, int c, int t) {
		this.r = r;
		this.c = c;
		this.t = t;
	}

	@Override
	public int compareTo(Point p) {
		if (t < p.t) {
			return -1;
		} else if (t == p.t) {
			if(r < p.r) {
				return -1;
			} else if (r == p.r) {
				if (c < p.c) {
					return -1;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}
