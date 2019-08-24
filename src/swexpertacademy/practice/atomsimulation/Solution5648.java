package swexpertacademy.practice.atomsimulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution5648 {
	public static int T, N, E;
	public static int[] dr = { 1, -1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int[][] atom = new int[1000][4];
		int[][] visitCount = new int[4001][4001];
		boolean[][] isCrush = new boolean[4001][4001];

		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				atom[i][0] = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				atom[i][1] = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				atom[i][2] = Integer.parseInt(st.nextToken());
				atom[i][3] = Integer.parseInt(st.nextToken());
			}
			E = 0;
			
			while (true) {
				// 1. 원자들을 각각의 방향대로 이동시키며, isCrush의 count를 증가시킨다. (2 이상일 경우 충돌한 것으로 판단한다.)
				for (int i = 0; i < N; i++) {
					atom[i][0] += dc[atom[i][2]];
					atom[i][1] += dr[atom[i][2]];
					if (atom[i][0] < 0 || atom[i][0] > 4000 || atom[i][1] < 0 || atom[i][1] > 4000) {
						removeAt(atom, i);
						i--;
					} else {
						visitCount[atom[i][0]][atom[i][1]]++;
						if (visitCount[atom[i][0]][atom[i][1]] > 1) {
							isCrush[atom[i][0]][atom[i][1]] = true;
						}
					}
				}
				// 2. 충돌한 원자들을 삭제하고 E를 계산한다.
				for (int i = 0; i < N; i++) {
					int x = atom[i][0];
					int y = atom[i][1];
					visitCount[x][y]--;
					if (isCrush[x][y]) {
						E += atom[i][3];
						removeAt(atom, i);
						i--;
						if (visitCount[x][y] == 0) {
							isCrush[x][y] = false;
						}
					}
				}
				// 3. 충돌 가능성이 없으면 종료한다.
				if (N < 2) {
					break;
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + E + "\n");
		}

		br.close();
		bw.close();
	}
	
	public static void removeAt(int[][] atom, int idx) {
		atom[idx][0] = atom[N - 1][0];
		atom[idx][1] = atom[N - 1][1];
		atom[idx][2] = atom[N - 1][2];
		atom[idx][3] = atom[N - 1][3];
		N--;
	}
}