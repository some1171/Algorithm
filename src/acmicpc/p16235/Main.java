package acmicpc.p16235;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, K;
	public static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	public static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };
	public static int[][] map, A;
	public static PriorityQueue<Tree> currentYear = new PriorityQueue<Tree>();
	public static PriorityQueue<Tree> nextYear = new PriorityQueue<Tree>();
	public static Queue<Tree> liveTreeList = new LinkedList<Tree>();
	public static Queue<Tree> deadTreeList = new LinkedList<Tree>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		A = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			currentYear.add(new Tree(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken())));
		}

		Main main = new Main();
		bw.flush();
		bw.write(main.aging() + "\n");

		br.close();
		bw.close();
	}

	public int aging() {
		for (int k = 0; k < K; k++) {
			// 봄
			while (!currentYear.isEmpty()) {
				Tree tree = currentYear.poll();
				if (tree.a <= map[tree.r][tree.c]) {
					map[tree.r][tree.c] -= tree.a;
					tree.old();
					liveTreeList.add(tree);
				} else {
					deadTreeList.add(tree);
				}
			}
			// 여름
			while (!deadTreeList.isEmpty()) {
				Tree tree = deadTreeList.poll();
				map[tree.r][tree.c] = map[tree.r][tree.c] + tree.a / 2;
			}
			// 가을
			while (!liveTreeList.isEmpty()) {
				Tree tree = liveTreeList.poll();

				if (tree.a % 5 != 0) {
					nextYear.add(tree);
					continue;
				}

				for (int i = 0; i < 8; i++) {
					int nr = tree.r + dr[i];
					int nc = tree.c + dc[i];

					if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
						nextYear.add(new Tree(nr, nc, 1));
					}
				}
				nextYear.add(tree);
			}
			// 겨울
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] += A[i][j];
				}
			}

			while (!nextYear.isEmpty()) {
				currentYear.add(nextYear.poll());
			}
		}

		return currentYear.size();
	}
}

class Tree implements Comparable<Tree> {
	int r, c, a;

	Tree(int r, int c, int a) {
		this.r = r;
		this.c = c;
		this.a = a;
	}

	public void old() {
		a++;
	}

	@Override
	public int compareTo(Tree target) {
		return this.a <= target.a ? -1 : 1;
	}
}
