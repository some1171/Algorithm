package swexpertacademy.d3;

import java.util.LinkedList;
import java.util.Scanner;

public class Solution1230 {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		LinkedList<Integer> cryptogram;
		int testCase = 0;
		int length, count, index;
		String command;
		
		while (testCase++ < 10) {
			cryptogram = new LinkedList<Integer>();
			length = in.nextInt();
			
			for (int i = 0; i < length; i++) {
				cryptogram.add(in.nextInt());
			}
			
			count = in.nextInt();
			for (int i = 0; i < count; i++) {
				command = in.next();
				
				if (command.equals("I")) {
					index = in.nextInt();
					length = in.nextInt();
					
					for (int j = 0; j < length; j++) {
						cryptogram.add(index++, in.nextInt());
					}
				}
				
				if (command.equals("D")) {
					index = in.nextInt();
					length = in.nextInt();
					
					for (int j = 0; j < length; j++) {
						cryptogram.remove(index);
					}
				}
				
				if (command.equals("A")) {
					length = in.nextInt();
					
					for (int j = 0; j < length; j++) {
						cryptogram.add(in.nextInt());
					}
				}
			}
			
			count = 0;
			System.out.print("#" + testCase + " ");
			for (int i : cryptogram) {
				if (count++ == 10) {
					break;
				}
				
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
