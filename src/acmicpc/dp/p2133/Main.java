package acmicpc.dp.p2133;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static int N;
	public static int[] memo = new int[31];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine().trim());
		
		bw.write(dp(N) + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static int dp(int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return 0;
		}
		if (n == 2) {
			return 3;
		}
		if (memo[n] != 0) {
			return memo[n];
		}
		int result = 3 * dp(n - 2);
		for (int i = 3; i <= n; i++) {
			if (i % 2 == 0) {
				result += 2 * dp(n - i);
			}
		}
		return memo[n] = result;
	}
}
