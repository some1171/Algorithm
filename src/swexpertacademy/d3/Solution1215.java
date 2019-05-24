package swexpertacademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1215 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int testCase = 0;
		
		while (testCase < 10) {
			char[][] chars = new char[8][8];
			int length = Integer.parseInt(in.readLine());
			int count = 0;
			
			for (int i = 0; i < 8; i++) {
				String line = in.readLine();
				chars[i] = line.toCharArray();
			}
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8 - length + 1; j++) {
					int check = 0;
					
					for (int k = j; k < j + (length / 2); k++) {
						if (chars[i][k] == chars[i][2 * j + length - 1 - k]) {
							check++;
						}
					}
					if (check == length / 2 || length == 1) {
						count++;
					}
				}
			}
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8 - length + 1; j++) {
					int check = 0;
					for (int k = j; k < j + (length / 2); k++) {
						if (chars[k][i] == chars[2 * j + length - 1 - k][i]) {
							check++;
						}
					}
					if (check == length / 2 || length == 1) {
						count++;
					}
				}
			}
			
			System.out.println("#" + (testCase + 1) + " " + count);
			testCase++;
		}
	}
}
