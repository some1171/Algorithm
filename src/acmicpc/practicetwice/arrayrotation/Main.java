package acmicpc.practicetwice.arrayrotation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, K, min = Integer.MAX_VALUE;
	public static int[][] map;
	public static Operator[] operators;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		operators = new Operator[K];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine().trim());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			operators[k] = new Operator(r, c, s);
		}

		int[] pick = new int[K];
		for (int k = 0; k < K; k++) {
			pick[k] = k;
		}
		pick(0, pick);

		bw.write(min + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

	public static void pick(int depth, int[] arr) {
		if (depth == K) {
			int[][] nmap = new int[N][M];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, nmap[i], 0, M);
			}
			for (int i = 0; i < K; i++) {
				Operator o = operators[arr[i]];
				nmap = rotateOperation(nmap, o.r, o.c, o.s);
			}
			min = Math.min(min, getValue(nmap));
			return;
		}
		for (int i = depth; i < K; i++) {
			swap(arr, depth, i);
			pick(depth + 1, arr);
			swap(arr, depth, i);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static int[][] rotateOperation(int[][] map, int r, int c, int s) {
		for (int l = s; l >= 1; l--) {
			int tail = map[r - l][c - l];
			for (int i = r - l; i < r + l; i++) {
				map[i][c - l] = map[i + 1][c - l];
			}
			for (int i = c - l; i < c + l; i++) {
				map[r + l][i] = map[r + l][i + 1];
			}
			for (int i = r + l; i > r - l; i--) {
				map[i][c + l] = map[i - 1][c + l];
			}
			for (int i = c + l; i > c - l + 1; i--) {
				map[r - l][i] = map[r - l][i - 1];
			}
			map[r - l][c - l + 1] = tail;
		}
		return map;
	}

	public static int getValue(int[][] map) {
		int minSum = -1;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += map[i][j];
			}
			if (minSum == -1) {
				minSum = sum;
			} else {
				minSum = Math.min(minSum, sum);
			}
		}
		return minSum;
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
