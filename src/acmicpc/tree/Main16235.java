package acmicpc.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main16235 {
	public static int N, M, K;
	public static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	public static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };
	public static int[][] a, map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		a = new int[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		LinkedList<Tree> treeList = new LinkedList<Tree>();
		LinkedList<Tree> aliveTreeList = new LinkedList<Tree>();
		LinkedList<Tree> deadTreeList = new LinkedList<Tree>();
		LinkedList<Tree> newTreeList = new LinkedList<Tree>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			treeList.add(new Tree(r, c, age));
		}
		Collections.sort(treeList);

		for (int k = 0; k < K; k++) {
			// 봄
			while (!newTreeList.isEmpty()) {
				Tree t = newTreeList.poll();
				int r = t.r;
				int c = t.c;
				int age = t.age;
				if (map[r][c] >= age) {
					map[r][c] -= age;
					t.age++;
					aliveTreeList.add(t);
				} else {
					deadTreeList.add(t);
				}
			}
			while (!treeList.isEmpty()) {
				Tree t = treeList.poll();
				int r = t.r;
				int c = t.c;
				int age = t.age;
				if (map[r][c] >= age) {
					map[r][c] -= age;
					t.age++;
					aliveTreeList.add(t);
				} else {
					deadTreeList.add(t);
				}
			}
			// 여름
			while (!deadTreeList.isEmpty()) {
				Tree t = deadTreeList.poll();
				int r = t.r;
				int c = t.c;
				int age = t.age;
				map[r][c] += age / 2;
			}
			// 가을
			while (!aliveTreeList.isEmpty()) {
				Tree t = aliveTreeList.poll();
				int r = t.r;
				int c = t.c;
				int age = t.age;
				if (age % 5 == 0) {
					treeList.add(t);
					for (int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
							newTreeList.add(new Tree(nr, nc, 1));
						}
					}
				} else {
					treeList.add(t);
				}
			}
			// 겨울
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] += a[i][j];
				}
			}
		}

		bw.write(treeList.size() + newTreeList.size() + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
}

class Tree implements Comparable<Tree> {
	int r, c, age;

	public Tree(int r, int c, int age) {
		this.r = r;
		this.c = c;
		this.age = age;
	}

	@Override
	public int compareTo(Tree t) {
		if (this.age < t.age) {
			return -1;
		} else if (this.age == t.age){
			return 0;
		} else {
			return 1;
		}
	}
}