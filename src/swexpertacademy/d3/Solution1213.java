package swexpertacademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1213 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int testCase;
		String pattern, target;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			testCase = Integer.parseInt(in.readLine());
			pattern = in.readLine();
			target = in.readLine();
			int count = 0;

			for (int j = 0; j < target.length() - pattern.length() + 1; j++) {
				if (target.charAt(j) == pattern.charAt(0)) {
					if (pattern.length() == 1) {
						count++;
					} else if (target.substring(j, j + pattern.length()).equals(pattern)) {
						count++;
					}
				}
			}

			System.out.println("#" + testCase + " " + count);
		}
	}
}
