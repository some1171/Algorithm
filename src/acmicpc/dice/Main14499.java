package acmicpc.dice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14499 {
	public static int N, M, R, C, K;
	public static int[] dr = { 0, 0, -1, 1 };
	public static int[] dc = { 1, -1, 0, 0 };
	public static int[] cmd;
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		Main14499 main = new Main14499();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cmd = new int[K];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < K; i++) {
			cmd[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		int[] pos = { 3, 2, 0, 4, 5, 1 };
		int[] dice = { 0, 0, 0, 0, 0, 0 };
		
		for (int k = 0; k < K; k++) {
			int command = cmd[k];
			int nr = R + dr[command];
			int nc = C + dc[command];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
				continue;
			}
			if (command == 0) {
				main.moveEast(pos, dice);
			} else if (command == 1) {
				main.moveWest(pos, dice);
			} else if (command == 2) {
				main.moveNorth(pos, dice);
			} else {
				main.moveSouth(pos, dice);
			}
			
			bw.flush();
			bw.write(dice[pos[2]] + "\n");
		}
		
		br.close();
		bw.close();
	}

	public void moveSouth(int[] pos, int[] dice) {
		R += dr[3];
		C += dc[3];
		int tail = pos[5];
		for (int i = 5; i > 2; i--) {
			pos[i] = pos[i - 1];
		}
		pos[2] = tail;
		if (map[R][C] == 0) {
			map[R][C] = dice[pos[4]];
		} else {
			dice[pos[4]] = map[R][C];
			map[R][C] = 0;
		}
	}

	public void moveNorth(int[] pos, int[] dice) {
		R += dr[2];
		C += dc[2];
		int head = pos[2];
		for (int i = 2; i < 5; i++) {
			pos[i] = pos[i + 1];
		}
		pos[5] = head;
		if (map[R][C] == 0) {
			map[R][C] = dice[pos[4]];
		} else {
			dice[pos[4]] = map[R][C];
			map[R][C] = 0;
		}
	}

	public void moveEast(int[] pos, int[] dice) {
		R += dr[0];
		C += dc[0];
		int head = pos[0];
		pos[0] = pos[4];
		pos[4] = pos[1];
		pos[1] = pos[2];
		pos[2] = head;
		if (map[R][C] == 0) {
			map[R][C] = dice[pos[4]];
		} else {
			dice[pos[4]] = map[R][C];
			map[R][C] = 0;
		}
	}

	public void moveWest(int[] pos, int[] dice) {
		R += dr[1];
		C += dc[1];
		int head = pos[0];
		pos[0] = pos[2];
		pos[2] = pos[1];
		pos[1] = pos[4];
		pos[4] = head;
		if (map[R][C] == 0) {
			map[R][C] = dice[pos[4]];
		} else {
			dice[pos[4]] = map[R][C];
			map[R][C] = 0;
		}
	}
}
