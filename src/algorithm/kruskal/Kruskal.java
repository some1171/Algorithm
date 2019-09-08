package algorithm.kruskal;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Kruskal Algorithm(크루스칼 알고리즘)
 * 가장 적은 비용으로 모든 노드를 연결하기 위한 알고리즘: 최소 비용 신장 트리(MST;Minimum Spanning Tree)
 * 
 * Base: MST일 때 간선의 수 = 노드 수 - 1 (반드시)
 * 1. 간선의 수가 짧은 순서대로 그래프에 포함시킨다. (간선 정보 오름차순 정렬)
 * 2. 포함시키기 전, 사이클 테이블을 확인한다. 포함시키면 사이클이 발생하는 경우 포함시키지 않는다. (MST는 사이클이 발생하면 안되기 때문) (Union-Find)
 * 3. 사이클이 형성되지 않으면 그래프에 포함시킨다.
 * 
 * 간선 정보 (노드1, 노드2, 비용)
 * 
 * 사이클 발생 여부
 * 사이클 테이블에서 이미 같은 부모를 가지고 있는데 연결하면 사이클이 발생한다.
 * 따라서 같은 부모를 가지고 있는지 확인 후 아닐 경우에만 그래프에 포함시킨다.
 * 
 * 전체 연결 여부
 * 사이클 테이블의 모든 노드들이 동일한 부모를 갖고 있으면 전체 연결된 상태이다.
 */

public class Kruskal {
	public static void main(String[] args) {
		int nodeCount = 7;
		int edgeCount = 11;
		int totalDistance = 0;
		
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		edgeList.add(new Edge(1, 7, 12));
		edgeList.add(new Edge(4, 7, 13));
		edgeList.add(new Edge(2, 4, 24));
		edgeList.add(new Edge(2, 5, 62));
		edgeList.add(new Edge(1, 4, 28));
		edgeList.add(new Edge(5, 7, 73));
		edgeList.add(new Edge(1, 2, 67));
		edgeList.add(new Edge(5, 6, 45));
		edgeList.add(new Edge(3, 6, 37));
		edgeList.add(new Edge(3, 5, 20));
		edgeList.add(new Edge(1, 5, 17));
		
		// 간선 비용 오름차순 정렬
		Collections.sort(edgeList);
		
		// parent 배열 생성 및 초기화
		int[] parent = new int[nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < edgeCount; i++) {
			// 사이클이 발생하지 않는 경우 그래프에 포함
			Edge e = edgeList.get(i);
			if (!findParent(parent, e.node1 - 1, e.node2 - 1)) {
				totalDistance += e.distance;
				unionParent(parent, e.node1 - 1, e.node2 - 1);
			}
			// 사이클이 발생하는 경우 무시
		}
		System.out.println("MST 거리: " + totalDistance);
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
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
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
	int node1, node2;
	int distance;
	
	public Edge(int a, int b, int distance) {
		this.node1 = a;
		this.node2 = b;
		this.distance = distance;
	}

	@Override
	public int compareTo(Edge e) {
		if (this.distance < e.distance) {
			return -1;
		} else if (this.distance == e.distance) {
			return 0;
		} else {
			return 1;
		}
	}
}
