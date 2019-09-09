package acmicpc.practice.retirement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14501 {
	public static int N, maxProfit;
	public static int[] t, p;
	
	public static void main(String[] args) throws IOException {
		Main14501 main = new Main14501();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		maxProfit = 0;
		N = Integer.parseInt(br.readLine().trim());
		t = new int[N];
		p = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		main.dfs(0, 0);
		
		bw.flush();
		bw.write(maxProfit + "\n");
		br.close();
		bw.close();
	}
	
	public void dfs(int pos, int profit) {
		if (pos >= N) {
			maxProfit = Math.max(maxProfit, profit);
			return;
		}
		if (pos + t[pos] - 1 < N) {
			dfs(pos + t[pos], profit + p[pos]);
		}
		dfs(pos + 1, profit);
	}
}
