package swexpertacademy.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * isp: in-stack precedence
 * icp: in-comming precedence
 * 
 * token	isp		icp
 * )		-		-
 * x /		2		2
 * + -		1		1
 * (		0		3
 */

public class Solution1222 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 1;
		
		while (count <= 10) {
			int length = Integer.parseInt(br.readLine());
			char[] tokens = new char[length];
			char[] postfix = new char[length];
			tokens = br.readLine().toCharArray();

			Stack<Character> operator = new Stack<Character>();

			int index = 0;
			for (char c : tokens) {
				if (c == '(') {
					operator.push(c);
				} else if (c == '*' || c == '/') {
					while (!operator.isEmpty() && (operator.peek() == '*' || operator.peek() == '/')) {
						postfix[index++] = operator.pop();
					}
					operator.push(c);
				} else if (c == '+' || c == '-') {
					while (!operator.isEmpty() && operator.peek() != '(') {
						postfix[index++] = operator.pop();
					}
					operator.push(c);
				} else if (c == ')') {
					while (operator.peek() != '(') {
						postfix[index++] = operator.pop();
					}
					operator.pop();
				} else {
					postfix[index++] = c;
				}
			}

			while (!operator.isEmpty()) {
				postfix[index++] = operator.pop();
			}

			Stack<Integer> calculator = new Stack<Integer>();

			for (char c : postfix) {
				int a, b;
				
				if (c == '\u0000') {
					break;
				}
				
				if (c == '*') {
					b = calculator.pop();
					a = calculator.pop();
					calculator.push(a * b);
				} else if (c == '/') {
					b = calculator.pop();
					a = calculator.pop();
					calculator.push(a / b);
				} else if (c == '+') {
					b = calculator.pop();
					a = calculator.pop();
					calculator.push(a + b);
				} else if (c == '-') {
					b = calculator.pop();
					a = calculator.pop();
					calculator.push(a - b);
				} else {
					calculator.push(c - '0');
				}
			}

			System.out.println("#" + (count++) + " " + calculator.pop());
		}
	}
}
