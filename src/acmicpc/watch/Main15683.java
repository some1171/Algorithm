package acmicpc.watch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main15683 {
	public static int N, M, K, minBlindSpot = 100;
	public static LinkedList<Camera> camList = new LinkedList<Camera>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int map[][];

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					camList.add(new Camera(i, j, map[i][j]));
				}
			}
		}
		K = camList.size();
		Main15683 main = new Main15683();
		main.dfs(0, map);

		bw.flush();
		bw.write(minBlindSpot + "\n");

		br.close();
		bw.close();
	}

	public void dfs(int pos, int[][] map) {
		if (pos == K) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0) {
						count++;
					}
				}
			}
			minBlindSpot = Math.min(minBlindSpot, count);

			return;
		}

		Camera cam = camList.get(pos);

		if (cam.t == 1) {
			dfs(pos + 1, lookUp(map, cam));
			dfs(pos + 1, lookLeft(map, cam));
			dfs(pos + 1, lookRight(map, cam));
			dfs(pos + 1, lookDown(map, cam));
		} else if (cam.t == 2) {
			dfs(pos + 1, lookRight(lookLeft(map, cam), cam));
			dfs(pos + 1, lookDown(lookUp(map, cam), cam));
		} else if (cam.t == 3) {
			dfs(pos + 1, lookRight(lookUp(map, cam), cam));
			dfs(pos + 1, lookDown(lookRight(map, cam), cam));
			dfs(pos + 1, lookDown(lookLeft(map, cam), cam));
			dfs(pos + 1, lookLeft(lookUp(map, cam), cam));
		} else if (cam.t == 4) {
			dfs(pos + 1, lookLeft(lookRight(lookUp(map, cam), cam), cam));
			dfs(pos + 1, lookDown(lookRight(lookUp(map, cam), cam), cam));
			dfs(pos + 1, lookLeft(lookRight(lookDown(map, cam), cam), cam));
			dfs(pos + 1, lookLeft(lookDown(lookUp(map, cam), cam), cam));
		} else if (cam.t == 5) {
			dfs(pos + 1, lookRight(lookLeft(lookDown(lookUp(map, cam), cam), cam), cam));
		}
	}

	public int[][] copyMap(int[][] map) {
		int[][] copyMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

		return copyMap;
	}

	public int[][] lookUp(int[][] map, Camera cam) {
		int[][] tempMap = copyMap(map);
		for (int i = cam.r; i >= 0; i--) {
			if (tempMap[i][cam.c] == 6) {
				break;
			}
			tempMap[i][cam.c] = 9;
		}
		return tempMap;
	}

	public int[][] lookLeft(int[][] map, Camera cam) {
		int[][] tempMap = copyMap(map);
		for (int i = cam.c; i >= 0; i--) {
			if (tempMap[cam.r][i] == 6) {
				break;
			}
			tempMap[cam.r][i] = 9;
		}
		return tempMap;
	}

	public int[][] lookRight(int[][] map, Camera cam) {
		int[][] tempMap = copyMap(map);
		for (int i = cam.c + 1; i < M; i++) {
			if (tempMap[cam.r][i] == 6) {
				break;
			}
			tempMap[cam.r][i] = 9;
		}
		return tempMap;
	}

	public int[][] lookDown(int[][] map, Camera cam) {
		int[][] tempMap = copyMap(map);
		for (int i = cam.r + 1; i < N; i++) {
			if (tempMap[i][cam.c] == 6) {
				break;
			}
			tempMap[i][cam.c] = 9;
		}
		return tempMap;
	}
}

class Camera {
	int r, c, t;

	public Camera(int r, int c, int t) {
		this.r = r;
		this.c = c;
		this.t = t;
	}
}
