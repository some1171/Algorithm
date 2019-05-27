package swexpertacademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution1218 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = 0;
		
		while (testCase < 10) {
			int size = Integer.parseInt(in.readLine());
			char[] brackets = new char[size];
			brackets = in.readLine().toCharArray();
			Stack<Character> stack = new Stack<Character>();
			int answer = 1;
			
			for (char c : brackets) {
				if (c == '<' || c == '{' || c == '(' || c == '[') {
					stack.push(c);
				} else if (c == '>') {
					if (stack.isEmpty() || stack.pop() != '<') {
						answer = 0;
						break;
					}
				} else if (c == '}') {
					if (stack.isEmpty() || stack.pop() != '{') {
						answer = 0;
						break;
					}
				} else if (c == ')') {
					if (stack.isEmpty() || stack.pop() != '(') {
						answer = 0;
						break;
					}
				} else if (c == ']') {
					if (stack.isEmpty() || stack.pop() != '[') {
						answer = 0;
						break;
					}
				}
			}
			
			if (!stack.isEmpty()) {
				answer = 0;
			}
			
			System.out.println("#" + (testCase + 1) + " " + answer);
			testCase++;
		}
	}
}
