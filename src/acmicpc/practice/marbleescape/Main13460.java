package acmicpc.practice.marbleescape;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13460 {
	public static int N, M, count = -1, blueR, blueC, redR, redC;;
	public static int[] dr = { 0, -1, 1, 0 };
	public static int[] dc = { -1, 0, 0, 1 };
	public static char[][] map;
	public static boolean[][][][] visit;

	public static void main(String[] args) throws IOException {
		Main13460 main = new Main13460();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'B') {
					blueR = i;
					blueC = j;
				} else if (map[i][j] == 'R') {
					redR = i;
					redC = j;
				}
			}
		}
		visit = new boolean[10][10][10][10];
		main.bfs();
		
		bw.flush();
		bw.write(count + "\n");
		br.close();
		bw.close();
	}

	public void bfs() {
		Queue<Status> queue = new LinkedList<Status>();
		Status start = new Status(blueR, blueC, redR, redC, 0);
		queue.add(start);
		
		while (!queue.isEmpty()) {
			Status status = queue.poll();
			if (status.count > 10) {
				continue;
			}
			if (map[status.br][status.bc] != 'O' && map[status.rr][status.rc] == 'O') {
				count = status.count;
				break;
			} else if (map[status.br][status.bc] == 'O') {
				continue;
			}
			
			for (int d = 0; d < 4; d++) {
				int nbr = status.br, nbc = status.bc, nrr = status.rr, nrc = status.rc;
				
				while (true) {
					if (map[nbr][nbc] != '#' && map[nbr][nbc] != 'O') {
						nbr += dr[d];
						nbc += dc[d];
					} else {
						if (map[nbr][nbc] == '#') {
							nbr -= dr[d];
							nbc -= dc[d];
						}
						break;
					}
				}
				while (true) {
					if (map[nrr][nrc] != '#' && map[nrr][nrc] != 'O') {
						nrr += dr[d];
						nrc += dc[d];
					} else {
						if (map[nrr][nrc] == '#') {
							nrr -= dr[d];
							nrc -= dc[d];
						}
						break;
					}
				}
				if (nbr == nrr && nbc == nrc && map[nbr][nbc] != 'O') {
					int bd = Math.abs(nbr - status.br) + Math.abs(nbc - status.bc);
					int rd = Math.abs(nrr - status.rr) + Math.abs(nrc - status.rc);
					if (bd > rd) {
						nbr -= dr[d];
						nbc -= dc[d];
					} else {
						nrr -= dr[d];
						nrc -= dc[d];
					}
				}
				if (!visit[nbr][nbc][nrr][nrc]) {
					visit[nbr][nbc][nrr][nrc] = true;
					queue.add(new Status(nbr, nbc, nrr, nrc, status.count + 1));
				}
			}
		}
	}
}

class Status {
	int br, bc, rr, rc, count; 

	public Status(int br, int bc, int rr, int rc, int count) {
		this.br = br;
		this.bc = bc;
		this.rr = rr;
		this.rc = rc;
		this.count = count;
	}
}
