package swexpertacademy.d4;

import java.util.Scanner;

public class Solution1211 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count = 0, r = 0, c = 0;
		int[][] ladder = new int[100][100];
		int[] starts;
		int[][] visited;
		
		while (count < 10) {
			int testCase = in.nextInt();
			
			starts = new int[100];
			int start = 0, distance, min = 0;
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = in.nextInt();

					if (i == 0 && ladder[i][j] == 1) {
						starts[j] = 1;
					}
				}
			}
			
			for (int i = 0; i < starts.length; i++) {
				if (starts[i] == 1) {
					r = 0;
					c = i;
					visited = new int[100][100];
					visited[r][c] = 1;
					distance = 0;
					
					while (r != 99) {
						if (c != 0 && ladder[r][c - 1] == 1 && visited[r][c - 1] == 0) {
							c = c - 1;
							visited[r][c] = 1;
							distance++;
						} else if (c != 99 && ladder[r][c + 1] == 1 && visited[r][c + 1] == 0) {
							c = c + 1;
							visited[r][c] = 1;
							distance++;
						} else {
							r = r + 1;
							visited[r][c] = 1;
							distance++;
						}
					}
					
					if (i == 0) {
						min = distance;
						start = i;
					} else if (distance <= min) {
						min = distance;
						start = i;
					}
				}
			}

			System.out.println("#" + testCase + " " + start);
			count++;
		}
	}
}