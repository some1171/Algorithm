package acmicpc.mst.p1922;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(n1, n2, c));
		}
		Collections.sort(edgeList);
		
		int[] parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		int cost = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			Edge e = edgeList.get(i);
			if (!findParent(parent, e.n1, e.n2)) {
				unionParent(parent, e.n1, e.n2);
				cost += e.c;
			}
		}
		
		bw.write(cost + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
	
	// Kruskal Algorithm
	public static void unionParent(int[] parent, int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}
	
	public static boolean findParent(int[] parent, int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a == b) {
			return true;
		}
		return false;
	}
	
	public static int getParent(int[] parent, int n) {
		if (parent[n] == n) {
			return n;
		}
		parent[n] = getParent(parent, parent[n]);
		return parent[n];
	}
}

class Edge implements Comparable<Edge> {
	int n1, n2, c;
	
	public Edge(int n1, int n2, int c) {
		this.n1 = n1;
		this.n2 = n2;
		this.c = c;
	}

	@Override
	public int compareTo(Edge e) {
		if (this.c < e.c) {
			return -1;
		} else if (this.c == e.c) {
			return 0;
		} else {
			return 1;
		}
	}
}
