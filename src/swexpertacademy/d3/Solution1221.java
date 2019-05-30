package swexpertacademy.d3;

import java.util.Scanner;

public class Solution1221 {
	private static Scanner sc = new Scanner(System.in);;

	public static void main(String[] args) {
		int testCase = sc.nextInt();
		int testNumber = 1;
		
		while (testNumber <= testCase) {
			sc.next();
			int test = 0;
			int count = sc.nextInt();
			String[] chart = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
			String str;
			
			int[] total = new int[10];
			while (test++ < count) {
				str = sc.next();
				
				switch (str) {
				case "ZRO":
					total[0]++;
					break;
				case "ONE":
					total[1]++;
					break;
				case "TWO":
					total[2]++;
					break;
				case "THR":
					total[3]++;
					break;
				case "FOR":
					total[4]++;
					break;
				case "FIV":
					total[5]++;
					break;
				case "SIX":
					total[6]++;
					break;
				case "SVN":
					total[7]++;
					break;
				case "EGT":
					total[8]++;
					break;
				case "NIN":
					total[9]++;
					break;
				}
			}
			
			System.out.println("#" + testNumber++);
			int index = 0;
			for (int i : total) {
				for (int j = 0; j < i; j++) {
					System.out.print(chart[index] + " ");
				}
				index++;
			}
			System.out.println();
		}
	}
}
