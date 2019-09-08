package swexpertacademy.d3;

import java.util.Scanner;
import java.util.Stack;

public class Solution1217 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count = 0;
		
		while (count < 10) {
			int testCase = in.nextInt();
			int number = in.nextInt();
			int power = in.nextInt();
			int answer = 1;
			
			Stack<Integer> stack = new Stack<Integer>();
			
			for (int i = 0; i < power; i++) {
				stack.push(number);
			}
			
			while (!stack.isEmpty()) {
				answer *= stack.pop();
			}
			
			System.out.println("#" + testCase + " " + answer);
			count++;
		}
		in.close();
	}
}