package acmicpc.practice.fishingking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main17143 {
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		ArrayList<Shark> sharkList = new ArrayList<Shark>();
		int R, C, M, size = 0;
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[R + 1][C + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());

			map[r][c] = i + 1;
			sharkList.add(new Shark(r, c, v, d, s));
		}
		
		int man = 0;
		while (man < C) {
			// 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
			man++;
			// 2. 낚시왕이 있는 열에서 가장 가까운 상어 잡는다.
			for (int r = 1; r <= R; r++) {
				if (map[r][man] != 0) {
					size += sharkList.get(map[r][man] - 1).s;
					map[r][man] = 0;
					break;
				}
			}
			// 3. 상어 이동한다.
			int[][] nextMap = new int[R + 1][C + 1];
			for (int i = 1; i <= R; i++) {
				for (int j = 1; j <= C; j++) {
					if (map[i][j] != 0) {
						int idx = map[i][j] - 1;
						map[i][j] = 0;
						Shark shark = sharkList.get(idx);
						int d = shark.d;
						int v = shark.v;
						int nr = i;
						int nc = j;
						for (int k = 0; k < v; k++) {
							nr += dr[d];
							nc += dc[d];
							if (nr < 1 || nr > R || nc < 1 || nc > C) {
								nr -= dr[d];
								nc -= dc[d];
								if (d == 0) {
									d = 1;
									shark.d = 1;
								} else if (d == 1) {
									d = 0;
									shark.d = 0;
								} else if (d == 2) {
									d = 3;
									shark.d = 3;
								} else if (d == 3) {
									d = 2;
									shark.d = 2;
								}
								nr += dr[d];
								nc += dc[d];
							}
						}
						if (nextMap[nr][nc] != 0) {
							Shark firstShark = sharkList.get(nextMap[nr][nc] - 1);
							if (firstShark.s >= shark.s) {
								continue;
							} else {
								nextMap[nr][nc] = idx + 1;
							}
						} else {
							nextMap[nr][nc] = idx + 1;
						}
					}
				}
			}
			
			map = nextMap;
		}

		bw.flush();
		bw.write(size + "\n");
		br.close();
		bw.close();
	}
}

class Shark {
	int r, c, v, d, s;

	public Shark(int r, int c, int v, int d, int s) {
		this.r = r;
		this.c = c;
		this.v = v;
		this.d = d;
		this.s = s;
	}
}
