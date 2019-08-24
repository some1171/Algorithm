package swexpertacademy.practice.numbermaker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution4008 {
	public static int T, N, diff, max, min;
	public static int[] count = new int[4], numbers;
	
	public static void main(String[] args) throws IOException {
		Solution4008 solution = new Solution4008();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			numbers = new int[N];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				count[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			solution.dfs(0, 0, 0, 0, 0, new int[N - 1]);
			diff = max - min;
			
			bw.flush();
			bw.write("#" + (tc + 1) + " " + diff + "\n");
		}
		
		br.close();
		bw.close();
	}
	
	public void dfs(int pos, int c1, int c2, int c3, int c4, int[] peek) {
		if (c1 == count[0] && c2 == count[1] && c3 == count[2] && c4 == count[3]) {
			compute(peek);
			return;
		}
		if (c1 < count[0]) {
			peek[pos] = 0;
			dfs(pos + 1, c1 + 1, c2, c3, c4, peek);
		}
		if (c2 < count[1]) {
			peek[pos] = 1;
			dfs(pos + 1, c1, c2 + 1, c3, c4, peek);
		}
		if (c3 < count[2]) {
			peek[pos] = 2;
			dfs(pos + 1, c1, c2, c3 + 1, c4, peek);
		}
		if (c4 < count[3]) {
			peek[pos] = 3;
			dfs(pos + 1, c1, c2, c3, c4 + 1, peek);
		}
	}
	
	public void compute(int[] peek) {
		int result = numbers[0];
		
		for (int i = 0; i < N - 1; i++) {
			switch(peek[i]) {
				case 0:	result = result + numbers[i + 1];
						break;
				
				case 1: result = result - numbers[i + 1];
						break;
					
				case 2: result = result * numbers[i + 1];
						break;
					
				case 3: result = result / numbers[i + 1];
						break;
			}
		}
		
		max = Math.max(max, result);
		min = Math.min(min, result);
	}
}
