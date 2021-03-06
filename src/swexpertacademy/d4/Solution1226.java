package swexpertacademy.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution1226 {
	private static final int SIZE = 16;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = 0;
		String[][] temp = new String[SIZE][SIZE];
		int[][] map = new int[SIZE][SIZE];
		
		while (testCase < 10) {
			int answer = 0;
			boolean[][] visited = new boolean[SIZE][SIZE];
			Stack<Point> stack = new Stack<Point>();
			
			testCase = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < SIZE; i++) {
				temp[i] = br.readLine().split("");
			}
			
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					map[i][j] = Integer.parseInt(temp[i][j]);
					
					if (map[i][j] == 2) {
						visited[i][j] = true;
						stack.push(new Point(i, j));
					}
				}
			}
			
			while (!stack.isEmpty()) {
				Point point = stack.pop();
				
				if (map[point.r][point.c] == 3) {
					answer = 1;
					break;
				}
				if (!point.left && !visited[point.r][point.c - 1] && map[point.r][point.c - 1] != 1) {
					Point p = new Point(point.r, point.c - 1);
					p.right = true;
					visited[p.r][p.c] = true;
					stack.push(p);
				}
				if (!point.right && !visited[point.r][point.c + 1] && map[point.r][point.c + 1] != 1) {
					Point p = new Point(point.r, point.c + 1);
					point.left = true;
					visited[p.r][p.c] = true;
					stack.push(p);
				}
				if (!point.up && !visited[point.r - 1][point.c] && map[point.r - 1][point.c] != 1) {
					Point p = new Point(point.r - 1, point.c);
					p.down = true;
					visited[p.r][p.c] = true;
					stack.push(p);
				}
				if (!point.down && !visited[point.r + 1][point.c] && map[point.r][point.c] != 1) {
					Point p = new Point(point.r + 1, point.c);
					p.up = true;
					visited[p.r][p.c] = true;
					stack.push(p);
				}
			}
			
			System.out.println("#" + testCase + " " + answer);
		}
	}
}
