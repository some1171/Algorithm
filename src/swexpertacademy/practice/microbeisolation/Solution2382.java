package swexpertacademy.practice.microbeisolation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution2382 {
	public static int T, N, M, K;
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			LinkedList<Colony> colonyList = new LinkedList<Colony>();
			
			for (int k = 0; k < K; k++) {				
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				map[r][c] = n;
				colonyList.add(new Colony(r, c, d - 1));
			}
			
			for (int m = 0; m < M; m++) {
				int[][] max = new int[N][N];
				int[][] direction = new int[N][N];
				int[][] nextMap = new int[N][N];
				
				for (int i = 0; i < colonyList.size(); i++) {
					Colony colony = colonyList.get(i);
					int nr = colony.r + dr[colony.d];
					int nc = colony.c + dc[colony.d];
					int nd = colony.d, nn = map[colony.r][colony.c];
					if (nr == 0) {
						nd = 1;
						nn = map[colony.r][colony.c] / 2;
					} else if (nr == N - 1) {
						nd = 0;
						nn = map[colony.r][colony.c] / 2;
					} else if (nc == 0) {
						nd = 3;
						nn = map[colony.r][colony.c] / 2;
					} else if (nc == N - 1){
						nd = 2;
						nn = map[colony.r][colony.c] / 2;
					}
					
					nextMap[nr][nc] += nn;
					if (max[nr][nc] < nn) {
						max[nr][nc] = nn;
						direction[nr][nc] = nd;
					}
				}
				colonyList.clear();
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						map[i][j] = nextMap[i][j];
						if (map[i][j] != 0) {
							colonyList.add(new Colony(i, j, direction[i][j]));
						}
					}
				}
				
				
			}
			int totalMicrobe = 0;
			
			while(!colonyList.isEmpty()) {
				Colony colony = colonyList.poll();
				totalMicrobe += map[colony.r][colony.c];
			}
			
			bw.flush();
			bw.write("#" + (t + 1) + " " + totalMicrobe + "\n");
		}
		
		br.close();
		bw.close();
	}
}

class Colony {
	int r, c, d;
	
	public Colony (int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}