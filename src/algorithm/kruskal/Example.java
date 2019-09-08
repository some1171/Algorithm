package algorithm.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Example {
	public static int N;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	public static int[][] firstMap, map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		firstMap = new int[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				firstMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬의 갯수를 센다.
		int islandCount = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (firstMap[i][j] == 1 && map[i][j] == 0) {
					bfs(i, j, islandCount);
					islandCount++;
				}
			}
		}
		islandCount--;

		// 정점간 거리를 세어 간선 정보를 저장한다.
		ArrayList<Edge2> edgeList = new ArrayList<Edge2>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					int islandIndex = map[i][j];
					for (int d = 0; d < 4; d++) {
						int nr = i;
						int nc = j;
						int count = 0;
						while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
							nr += dr[d];
							nc += dc[d];
							count++;
							if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
								if (map[nr][nc] == islandIndex) {
									break;
								}
								if (map[nr][nc] != 0 && map[nr][nc] != islandIndex) {
									int nextIslandIndex = map[nr][nc];
									edgeList.add(new Edge2(islandIndex, nextIslandIndex, count - 1));
									break;
								}
							}
						}
					}
				}
			}
		}

		Collections.sort(edgeList);
		int distance = 0;
		int[] parent = new int[islandCount];
		for (int i = 0; i < islandCount; i++) {
			parent[i] = i;
		}
		// 간선을 탐색하며 그래프에 포함시키고 거리를 업데이트한다.
		for (int i = 0; i < edgeList.size(); i++) {
			Edge2 e = edgeList.get(i);
			if (!findParent(parent, e.node1 - 1, e.node2 - 1)) {
				unionParent(parent, e.node1 - 1, e.node2 - 1);
				distance += e.distance;
			}
		}
		// 연결 종료 후 모든 정점이 포함되어있는지 확인한다.
		for (int i = 0; i < islandCount; i++) {
			if (getParent(parent, i) != 0) {
				distance = -1;
			}
		}

		bw.write(distance + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

	public static boolean findParent(int[] parent, int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a == b) {
			return true;
		}
		return false;
	}

	public static void unionParent(int[] parent, int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	public static int getParent(int[] parent, int n) {
		if (parent[n] == n) {
			return n;
		}
		parent[n] = getParent(parent, parent[n]);
		return parent[n];
	}

	public static void bfs(int i, int j, int idx) {
		Queue<Point> queue = new LinkedList<Point>();
		map[i][j] = idx;
		queue.add(new Point(i, j));

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int r = p.r;
			int c = p.c;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && firstMap[nr][nc] == 1 && map[nr][nc] == 0) {
					map[nr][nc] = idx;
					queue.add(new Point(nr, nc));
				}
			}
		}
	}
}

class Edge2 implements Comparable<Edge2> {
	int node1, node2, distance;

	public Edge2(int node1, int node2, int distance) {
		this.node1 = node1;
		this.node2 = node2;
		this.distance = distance;
	}

	@Override
	public int compareTo(Edge2 e) {
		if (this.distance < e.distance) {
			return -1;
		} else if (this.distance == e.distance) {
			return 0;
		} else {
			return 1;
		}
	}
}

class Point {
	int r, c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
