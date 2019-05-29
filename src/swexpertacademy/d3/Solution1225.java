package swexpertacademy.d3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution1225 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = 0;
		
		while (testCase < 10) {
			testCase = sc.nextInt();
			Queue<Integer> queue = new LinkedList<Integer>();
			int count = 0;
			
			while (count++ < 8) {
				queue.add(sc.nextInt());
			}
			
			int index = 1;
			while (true) {
				int encode = queue.poll() - index;
				
				if (encode <= 0) {
					queue.add(0);	
					break;
				} else {
					queue.add(encode);
				}
				
				if (index == 5) {
					index = 1;
				} else {
					index++;
				}
			}
			
			System.out.print("#" + testCase + " ");
			
			Iterator<Integer> iterator = queue.iterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next() + " ");
			}
			System.out.println();
		}
	}
}
