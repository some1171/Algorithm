package acmicpc.mst.p1197;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static int V, E;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(node1, node2, weight));
		}
		Collections.sort(edgeList);
		int[] parent = new int[V];
		for (int i = 0; i < V; i++) {
			parent[i] = i;
		}
		
		int weight = 0;
		for (int i = 0; i < E; i++) {
			Edge e = edgeList.get(i);
			if (!findParent(parent, e.node1, e.node2)) {
				unionParent(parent, e.node1, e.node2);
				weight += e.weight;
			}
		}
		
		bw.write(weight + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

	public static boolean findParent(int[] parent, int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a == b) {
			return true;
		}
		return false;
	}

	public static void unionParent(int[] parent, int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
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
	int node1, node2, weight;

	public Edge(int node1, int node2, int weight) {
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge e) {
		if (this.weight < e.weight) {
			return -1;
		} else if (this.weight == e.weight) {
			return 0;
		} else {
			return 1;
		}
	}
}
