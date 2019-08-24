package swexpertacademy.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1249 {
	private static final int INF = 999999;
	private static int size;
	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};
	private static int[][] map, distance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int t = 0; t < testCase; t++) {
			size = Integer.parseInt(br.readLine().trim());
			map = new int[size][size];
			distance = new int[size][size];
			
			for (int i = 0; i < size; i++) {
				String str = br.readLine();
				
				for (int j = 0; j < size; j++) {
					map[i][j] = str.charAt(j) - '0';
					distance[i][j] = INF;
				}
			}
			
			System.out.printf("#%d %d\n", t + 1, bfs());
		}
		br.close();
	}
	
	public static int bfs() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(0, 0, 0));
		
		while (!queue.isEmpty()) {
			Node node1 = queue.poll();
			int r1 = node1.r;
			int c1 = node1.c;
			int d1 = node1.d;
			
			for (int i = 0; i < 4; i++) {
				int r2 = r1 + dr[i];
				int c2 = c1 + dc[i];
				
				if (r2 < 0 || r2 >= size || c2 < 0 || c2 >= size) {
					continue;
				}
				int d2 = d1 + map[r2][c2];
				
				if (d2 >= distance[r2][c2]) {
					continue;
				}
				distance[r2][c2] = d2;
				
				queue.offer(new Node(r2, c2, d2));
			}
		}
		
		return distance[size - 1][size - 1];
	}
	
	private static class Node {
		int r, c, d;
		
		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
