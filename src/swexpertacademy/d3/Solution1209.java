package swexpertacademy.d3;

import java.util.Scanner;

public class Solution1209 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count = 0;
		int testCase;
		
		while (count < 10) {
			int index = 0;
			int[][] array = new int[100][100];
			int[] sums = new int[202];
			int max, temp;
			
			testCase = in.nextInt();
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					array[i][j] = in.nextInt();
				}
			}
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					sums[index] += array[i][j];
					sums[index + 100] += array[j][i];
				}
				index++;
			}
			
			for (int i = 0; i < 100; i++) {
				sums[index * 2] += array[i][i];
				sums[index * 2 + 1] += array[i][100 - i - 1];
			}
			
			max = sums[0];
			for (int i : sums) {
				temp = Math.max(max, i);
				
				if (max != temp) {
					max = temp;
				}
			}
			
			System.out.println("#" + testCase + " " + max);
			count++;
		}
		in.close();
	}
}