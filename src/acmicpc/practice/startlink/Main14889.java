package acmicpc.practice.startlink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14889 {
	public static int N, min = Integer.MAX_VALUE;
	public static int[][] synergy;

	public static void main(String[] args) throws IOException {
		Main14889 main = new Main14889();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		synergy = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				synergy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		main.pick(0, 0, new boolean[N]);
		
		bw.flush();
		bw.write(min + "\n");
		br.close();
		bw.close();
	}

	public void pick(int pos, int count, boolean[] pick) {
		if (count == N / 2) {
			// 다 뽑은 경우, 각 팀의 능력치를 계산한 후, 최소값을 계산한다.
			int start = 0;
			int link = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (pick[i] && pick[j]) {
						start += synergy[i][j];
					} else if (!pick[i] && !pick[j]) {
						link += synergy[i][j];
					}
				}
			}
			
			int diff = Math.abs(start - link);
			min = Math.min(min, diff);
		}
		if (pos == N) {
			return;
		}
		// 뽑을 경우
		pick[pos] = true;
		pick(pos + 1, count + 1, pick);
		// 뽑지 않을 경우
		pick[pos] = false;
		pick(pos + 1, count, pick);
	}
}
