package swexpertacademy.practice.swimmingpool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution1952 {
	public static int T, minFee;
	public static int[] fee = new int[4];
	public static int[] plan = new int[12];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				fee[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			minFee = fee[3];
			
			Solution1952 solution = new Solution1952();
			solution.dfs(0, 0);
			
			bw.flush();
			bw.write("#" + (t + 1) + " " + minFee + "\n");
		}
		
		br.close();
		bw.close();
	}
	
	public void dfs(int pos, int accumFee) {
		if (pos == 12) {
			minFee = Math.min(minFee, accumFee);
			return;
		}
		if (accumFee > minFee) {
			return;
		}
		if (plan[pos] == 0) {
			dfs(pos + 1, accumFee);
		} else {
			dfs(pos + 1, accumFee + plan[pos] * fee[0]);
			dfs(pos + 1, accumFee + fee[1]);
			if (pos + 3 < 12) {
				dfs(pos + 3, accumFee + fee[2]);
			} else {
				dfs(12, accumFee + fee[2]);
			}
		}
	}
}
