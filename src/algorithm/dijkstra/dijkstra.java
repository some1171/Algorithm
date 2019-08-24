package algorithm.dijkstra;

import java.util.Scanner;

public class dijkstra {
	static final int INF = 100;
	static int totalTestCase, currentTestCase = 0;
	static int[][] map;
	static boolean[] visit;
	static int[] distance;
	static int vertex, start, end, edge;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		totalTestCase = sc.nextInt();
		
		while (currentTestCase++ < totalTestCase) {
			vertex = sc.nextInt();
			start = sc.nextInt();
			end = sc.nextInt();
			edge = sc.nextInt();
			map = new int[vertex + 1][vertex + 1];
			visit = new boolean[vertex + 1];
			distance = new int[vertex + 1];
			
			for (int i = 1; i <= vertex; i++) {
				for (int j = 1; j <= vertex; j++) {
					if (i != j) {
						map[i][j] = INF;
					}
				}
			}
			
			for (int i = 1; i <= edge; i++) {
				map[sc.nextInt()][sc.nextInt()] = sc.nextInt();
			}
			
			for (int i = 1; i <= vertex; i++) {
				distance[i] = INF;
			}
			
			dijkstra();
			System.out.printf("#%d %d\n", currentTestCase, distance[end]);
		}
		
		sc.close();
	}
	
	public static void dijkstra() {
		int v = 0;
		distance[1] = 0;
		
		for (int i = 1; i <= vertex; i++) {
			int min = INF;
			
			for (int j = 1; j <= vertex; j++) {
				if (visit[j] == false && min > distance[j]) {
					min = distance[j];
					v = j;
				}
			}
			visit[v] = true;
			
			for (int j = 1; j <= vertex; j++) {
				if (distance[j] > distance[v] + map[v][j]) {
					distance[j] = distance[v] + map[v][j];
				}
			}
		}
	}
}
