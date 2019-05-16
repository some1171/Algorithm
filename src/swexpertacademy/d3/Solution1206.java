package swexpertacademy.d3;

import java.util.Scanner;

public class Solution1206 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int leftView, rightView;

		for (int i = 0; i < 10; i++) {
			int testCase = in.nextInt();
			int[] buildings = new int[testCase];
			int view = 0;

			for (int j = 0; j < testCase; j++) {
				buildings[j] = in.nextInt();
			}

			for (int j = 2; j < testCase - 2; j++) {
				leftView = buildings[j] - Math.max(buildings[j - 1], buildings[j - 2]);
				rightView = buildings[j] - Math.max(buildings[j + 1], buildings[j + 2]);

				if (leftView > 0 && rightView > 0) {
					view += Math.min(leftView, rightView);
				}
			}

			System.out.println("#" + (i + 1) + " " + view);
		}
	}
}
