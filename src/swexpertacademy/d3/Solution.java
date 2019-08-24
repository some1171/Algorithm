package swexpertacademy.d3;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int totalTestCase = sc.nextInt();
		int currentTestCase = 0;
		int a, b, c;
		
		while (currentTestCase++ < totalTestCase) {
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
			
			if (a == b) {
				System.out.println("#" + currentTestCase + " " + c);
			} else if (a == c) {
				System.out.println("#" + currentTestCase + " " + b);
			} else {
				System.out.println("#" + currentTestCase + " " + a);
			}
		}
		sc.close();
	}
}
