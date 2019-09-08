package swexpertacademy.d4;

import java.util.Scanner;

public class Solution1210 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count = 0, r = 0, c = 0;
		int[][] ladder = new int[100][100];

		
		while (count < 10) {
			int testCase = in.nextInt();
			r = 99;
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = in.nextInt();

					if (i == 99 && ladder[i][j] == 2) {
						c = j;
					}
				}
			}

			ladder[r][c] = 0;

			while (r != 0) {
				if (c != 0 && ladder[r][c - 1] == 1) {
					c = c - 1;
					ladder[r][c] = 0;
				} else if (c != 99 && ladder[r][c + 1] == 1) {
					c = c + 1;
					ladder[r][c] = 0;
				} else {
					r = r - 1;
					ladder[r][c] = 0;
				}
			}

			System.out.println("#" + testCase + " " + c);
			count++;
		}
		in.close();
	}
}
