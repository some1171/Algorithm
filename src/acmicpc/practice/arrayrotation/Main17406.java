package acmicpc.practice.arrayrotation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main17406 {
	public static int N, M, K, min = Integer.MAX_VALUE;
	public static int[][] map = new int[50][50];
	public static Operator[] operators;
	
	public static void main(String[] args) throws IOException {
		Main17406 main = new Main17406();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		operators = new Operator[K];
		int[] pick = new int[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			operators[i] = new Operator(r, c, s);
			pick[i] = i;
		}
		
		main.pick(0, K, K, pick);
		
		bw.flush();
		bw.write(min + "\n");
		br.close();
		bw.close();
	}
	
	public void pick(int depth, int n, int k, int[] pick) {
		if (depth == k) {
			// 다 뽑은 경우, 연산을 수행한다.
			int[][] cmap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					cmap[i][j] = map[i][j];
				}
			}
			for (int i = 0; i < K; i++) {
				int operatorIndex = pick[i]; 
				rotate(operatorIndex, cmap);
			}
			min = Math.min(min, getMin(cmap));
			
			return;
		}
		
		for (int i = depth; i < n; i++) {
			swap(i, depth, pick);
			pick(depth + 1, n, k, pick);
			swap(i, depth, pick);
		}
	}
	
	public void swap(int i, int j, int[] peek) {
		int temp = peek[i];
		peek[i] = peek[j];
		peek[j] = temp;
	}
	
	public void rotate(int operatorIndex, int[][] map) {
		Operator o = operators[operatorIndex];
		int r = o.r;
		int c = o.c;
		
		for (int s = o.s; s > 0; s--) {
			int startR = r - s;
			int startC = c - s;
			int endR = r + s;
			int endC = c + s;
			int head = map[startR][startC];
			
			for (int i = startR; i < endR; i++) {
				map[i][startC] = map[i + 1][startC];
			}
			for (int i = startC; i < endC; i++) {
				map[endR][i] = map[endR][i + 1];
			}
			for (int i = endR; i > startR; i--) {
				map[i][endC] = map[i - 1][endC];
			}
			for (int i = endC; i > startC + 1; i--) {
				map[startR][i] = map[startR][i - 1];
			}
			map[startR][startC + 1] = head;
		}
	}
	
	public int getMin(int[][] map) {
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += map[i][j];
			}
			min = Math.min(min, sum);
		}
		
		return min;
	}
}

class Operator {
	int r, c, s;
	
	public Operator(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}