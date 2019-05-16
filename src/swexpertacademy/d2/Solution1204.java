package swexpertacademy.d2;

import java.util.Scanner;

public class Solution1204 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int test = in.nextInt();
		int testNumber = 0;
		
		while (testNumber < test) {
			int[] scores = new int[1000];
			int[] scoreFrequency = new int[101];
			
			testNumber = in.nextInt();
			for (int i = 0; i < 1000; i++) {
				scores[i] = in.nextInt();
			}
			
			for (int i = 0; i < 1000; i++) {
				scoreFrequency[scores[i]]++;
			}
			
			System.out.println("#" + testNumber + " " + maxNumberIndex(scoreFrequency));
		}
	}
	
	public static int maxNumberIndex(int[] numbers) {
		int max = numbers[0];
		int index = 0;
		
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] >= max) {
				max = numbers[i];
				index = i;
			}
		}
		
		return index;
	}
}
