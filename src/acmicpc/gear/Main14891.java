package acmicpc.gear;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14891 {
	public static int K;
	public static int[][] rotations;
	
	public static void main(String[] args) throws IOException {
		Main14891 main = new Main14891();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int[][] gears = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String str = br.readLine().trim();
			for (int j = 0; j < 8; j++) {
				gears[i][j] = str.charAt(j) - '0';
			}
		}
		K = Integer.parseInt(br.readLine().trim());
		rotations = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < 2; j++) {
				rotations[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k = 0; k < K; k++) {
			boolean[] isDifferent = new boolean[3];
			if (gears[0][2] != gears[1][6]) {
				isDifferent[0] = true;
			}
			if (gears[1][2] != gears[2][6]) {
				isDifferent[1] = true;
			}
			if (gears[2][2] != gears[3][6]) {
				isDifferent[2] = true;
			}
			int number = rotations[k][0];
			int direction = rotations[k][1];
			
			if (number == 1) {
				main.rotate(gears[0], direction);
				if (isDifferent[0]) {
					main.rotate(gears[1], direction * (-1));
					if (isDifferent[1]) {
						main.rotate(gears[2], direction);
						if (isDifferent[2]) {
							main.rotate(gears[3], direction * (-1));
						}
					}
				}
			} else if (number == 2) {
				main.rotate(gears[1], direction);
				if (isDifferent[0]) {
					main.rotate(gears[0], direction * (-1));
				}
				if (isDifferent[1]) {
					main.rotate(gears[2], direction * (-1));
					if (isDifferent[2]) {
						main.rotate(gears[3], direction);
					}
				}
			} else if (number == 3) {
				main.rotate(gears[2], direction);
				if (isDifferent[2]) {
					main.rotate(gears[3], direction * (-1));
				}
				if (isDifferent[1]) {
					main.rotate(gears[1], direction * (-1));
					if (isDifferent[0]) {
						main.rotate(gears[0], direction);
					}
				}
			} else {
				main.rotate(gears[3], direction);
				if (isDifferent[2]) {
					main.rotate(gears[2], direction * (-1));
					if (isDifferent[1]) {
						main.rotate(gears[1], direction);
						if (isDifferent[0]) {
							main.rotate(gears[0], direction * (-1));
						}
					}
				}
			}
		}
		
		int point = gears[0][0] * 1 + gears[1][0] * 2 + gears[2][0] * 4 + gears[3][0] * 8;
		bw.flush();
		bw.write(point + "\n");
		br.close();
		bw.close();
	}
	
	public void rotate(int[] arr, int d) {
		// 시계 방향
		if (d == 1) {
			int tail = arr[7];
			for (int i = 7; i > 0; i--) {
				arr[i] = arr[i - 1];
			}
			arr[0] = tail;
		}
		// 반시계 방향
		if (d == -1) {
			int head = arr[0];
			for (int i = 0; i < 7; i++) {
				arr[i] = arr[i + 1];
			}
			arr[7] = head;
		}
	}
}
