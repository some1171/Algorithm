package acmicpc.practice.slope;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14890 {
	public static int N, L;
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		Main14890 main = new Main14890();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		int count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[] occupied;
		boolean isPossible;
		int currentHeight;
		// °¡·Î Å½»ö
		for (int i = 0; i < N; i++) {
			isPossible = true;
			currentHeight = map[i][0];
			occupied = new boolean[N];
			for (int j = 0; j < N; j++) {
				if (currentHeight == map[i][j]) {
					continue;
				}
				if (currentHeight + 1 == map[i][j]) {
					if (main.getFlatLengthBackward(i, j - 1) < L) {
						isPossible = false;
						break;
					}
					for (int k = j - 1; k >= j - L; k--) {
						if (occupied[k]) {
							isPossible = false;
							break;
						}
					}
					for (int k = j - 1; k >= j - L; k--) {
						occupied[k] = true;
					}
					currentHeight = map[i][j];
				} else if (currentHeight - 1 == map[i][j]) {
					if (main.getFlatLengthForward(i, j) < L) {
						isPossible = false;
						break;
					}
					for (int k = j; k < j + L; k++) {
						if (occupied[k]) {
							isPossible = false;
							break;
						}
					}
					for (int k = j; k < j + L; k++) {
						occupied[k] = true;
					}
					currentHeight = map[i][j];
				} else {
					isPossible = false;
					break;
				}
			}
			if (isPossible) {
				count++;
			}
		}
		// ¼¼·Î Å½»ö
		for (int i = 0; i < N; i++) {
			isPossible = true;
			currentHeight = map[0][i];
			occupied = new boolean[N];
			for (int j = 0; j < N; j++) {
				if (currentHeight == map[j][i]) {
					continue;
				}
				if (currentHeight + 1 == map[j][i]) {
					if (main.getFlatLengthUpward(j - 1, i) < L) {
						isPossible = false;
						break;
					}
					for (int k = j - 1; k >= j - L; k--) {
						if (occupied[k]) {
							isPossible = false;
							break;
						}
					}
					for (int k = j - 1; k >= j - L; k--) {
						occupied[k] = true;
					}
					currentHeight = map[j][i];
				} else if (currentHeight - 1 == map[j][i]) {
					if (main.getFlatLengthDownward(j, i) < L) {
						isPossible = false;
						break;
					}
					for (int k = j; k < j + L; k++) {
						if (occupied[k]) {
							isPossible = false;
							break;
						}
					}
					for (int k = j; k < j + L; k++) {
						occupied[k] = true;
					}
					currentHeight = map[j][i];
				} else {
					isPossible = false;
					break;
				}
			}
			if (isPossible) {
				count++;
			}
		}
		
		bw.flush();
		bw.write(count + "\n");
		br.close();
		bw.close();
	}

	public int getFlatLengthBackward(int r, int c) {
		int length = 0, height = map[r][c];
		for (int i = c; i >= 0; i--) {
			if (map[r][i] == height) {
				length++;
			} else {
				break;
			}
		}
		return length;
	}

	public int getFlatLengthForward(int r, int c) {
		int length = 0, height = map[r][c];
		for (int i = c; i < N; i++) {
			if (map[r][i] == height) {
				length++;
			} else {
				break;
			}
		}
		return length;
	}

	public int getFlatLengthUpward(int r, int c) {
		int length = 0, height = map[r][c];
		for (int i = r; i >= 0; i--) {
			if (map[i][c] == height) {
				length++;
			} else {
				break;
			}
		}
		return length;
	}

	public int getFlatLengthDownward(int r, int c) {
		int length = 0, height = map[r][c];
		for (int i = r; i < N; i++) {
			if (map[i][c] == height) {
				length++;
			} else {
				break;
			}
		}
		return length;
	}
}
