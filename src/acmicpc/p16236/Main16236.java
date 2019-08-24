package acmicpc.p16236;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16236 {
	public static int dr[] = { -1, 0, 0, 1 };
	public static int dc[] = { 0, -1, 1, 0 };
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int r = 0, c = 0;
		int map[][] = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					r = i;
					c = j;
				}
			}
		}
		
		boolean isUpdate = true;
		boolean[][] visit = new boolean[N][N];
		Shark shark = new Shark(r, c);
		visit[r][c] = true;
		Queue<Point1> queue = new LinkedList<Point1>();
		
		while (isUpdate) {
			isUpdate = false;
			queue.clear();
			queue.add(new Point1(shark.r, shark.c, 0));
			
			Point1 candiP = new Point1(20, 20, -1);
			while (!queue.isEmpty()) {
				Point1 point = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int nr = point.r + dr[i];
					int nc = point.c + dc[i];
					int nd = point.d + 1;
					
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc] && map[nr][nc] <= shark.size) {
						queue.add(new Point1(nr, nc, nd));
						visit[nr][nc] = true;
					}
				}
				
				if (map[point.r][point.c] >= shark.size || map[point.r][point.c] == 0) {
					continue;
				}
				
				if (candiP.d == -1) {
					candiP = point;
				} else {
					if (candiP.d < point.d) {
						break;
					}
					if (candiP.d > point.d) {
						candiP = point;
						continue;
					}
					if (candiP.d == point.d) {
						if (candiP.r > point.r) {
							candiP = point;
						} else if (candiP.r == point.r) {
							if (candiP.c > point.c) {
								candiP = point;
							}
						}
					}
				}
			}
			
			if (candiP.d != -1) {
				shark.eat();
				map[shark.r][shark.c] = 0;
				map[candiP.r][candiP.c] = 9;
				shark.move(candiP.r, candiP.c, candiP.d);
				isUpdate = true;
				visit = new boolean[N][N];
			}
		}
		
		bw.write(shark.time + "\n");
		br.close();
		bw.close();
	}
}

class Point1 {
	int r, c, d;

	public Point1(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}

class Shark {
	int size, r, c, time, count;

	public Shark(int r, int c) {
		this.r = r;
		this.c = c;
		this.size = 2;
		this.time = 0;
		this.count = 0;
	}

	public void move(int r, int c, int d) {
		time = time + d;
		this.r = r;
		this.c = c;
	}

	public void eat() {
		count++;

		if (count == size) {
			size++;
			count = 0;
		}
	}
}
