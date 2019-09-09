package acmicpc.dp.p14852;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static int N;
	public static int[] memo = new int[1000001];
	
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
		if (n == 1) {
			return 2;
		}
		if (n == 2) {
			return 7;
		}
		return 0;
	}
}
