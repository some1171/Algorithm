package swexpertacademy.d3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution1220 {
	private static final int SIZE = 100;
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int testCase = 1;
		
		while (testCase < 11) {
			int answer = 0;
			int[][] table = new int[SIZE][SIZE];
			sc.nextInt();
			
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					table[i][j] = sc.nextInt();
				}
			}
			
			for (int i = 0; i < SIZE; i++) {
				Queue<Integer> queue = new LinkedList<Integer>();
				int status = 0;
				
				for (int j = 0; j < SIZE; j++) {
					if (table[j][i] == 1 || table[j][i] == 2) {
						queue.add(table[j][i]);
					}
				}
				
				while(!queue.isEmpty()) {
					int n = queue.poll();
					
					if (status == 0 && n == 1) {
						status = 1;
					} else if (status == 1 && n == 2) {	
						if (queue.isEmpty() || queue.peek() == 1) {
							status = 0;
							answer++;
						}
					}
					
				}
			}
			
			System.out.println("#" + testCase++ + " " + answer);
		}
	}
}
