package swexpertacademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1216 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int testCase = 0;

		while (testCase < 10) {
			char[][] chars = new char[100][100];
			int number = Integer.parseInt(in.readLine());
			int maxLength = 0, horizontalMaxLength = 0, verticalMaxLength = 0;

			for (int i = 0; i < 100; i++) {
				String line = in.readLine();
				chars[i] = line.toCharArray();
			}

			outerLoop:
			for (int length = 100; length > 0; length--) {
				for (int i = 0; i < 100; i++) {
					for (int j = 0; j < 100 - length + 1; j++) {
						int check = 0;

						for (int k = j; k < j + (length / 2); k++) {
							if (chars[i][k] == chars[i][2 * j + length - 1 - k]) {
								check++;
							}
						}
						if (check == length / 2 || length == 1) {
							horizontalMaxLength = length;
							break outerLoop;
						}
					}
				}
			}

			outerLoop:
			for (int length = 100; length > horizontalMaxLength; length--) {
				for (int i = 0; i < 100; i++) {
					for (int j = 0; j < 100 - length + 1; j++) {
						int check = 0;
						for (int k = j; k < j + (length / 2); k++) {
							if (chars[k][i] == chars[2 * j + length - 1 - k][i]) {
								check++;
							}
						}
						if (check == length / 2 || length == 1) {
							verticalMaxLength = length;
							break outerLoop;
						}
					}
				}
			}
			
			maxLength = Math.max(horizontalMaxLength, verticalMaxLength);

			System.out.println("#" + number + " " + maxLength);
			testCase++;
		}
	}
}
