package swexpertacademy.d4;

import java.util.Scanner;
import java.util.Stack;

public class Solution1219 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = 0;
		
		while (testCase < 10) {
			int answer = 0, count = 0;
			int[] routes1 = new int[100];
			int[] routes2 = new int[100];
			boolean[] visited = new boolean[100];
			Stack<Integer> stack = new Stack<Integer>();
			testCase = sc.nextInt();
			count = sc.nextInt();
			
			for (int i = 0; i < count; i++) {
				int index = sc.nextInt();
				
				if (routes1[index] == 0) {
					routes1[index] = sc.nextInt();
				} else {
					routes2[index] = sc.nextInt();
				}
			}
			
			visited[0] = true;
			stack.push(0);
			
			while(!stack.isEmpty()) {
				int node = stack.pop();
				
				if (!visited[routes1[node]] && routes1[node] != 0) {
					stack.push(routes1[node]);
					visited[routes1[node]] = true;
				}
				
				if (!visited[routes2[node]] && routes2[node] != 0) {
					stack.push(routes2[node]);
					visited[routes2[node]] = true;
				}
			}
			
			if (visited[99] == true) {
				answer = 1;
			}
			
			System.out.println("#" + testCase + " " + answer);
		}
		sc.close();
	}
}
