package algorithm.kruskal;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Kruskal Algorithm(ũ�罺Į �˰���)
 * ���� ���� ������� ��� ��带 �����ϱ� ���� �˰���: �ּ� ��� ���� Ʈ��(MST;Minimum Spanning Tree)
 * 
 * Base: MST�� �� ������ �� = ��� �� - 1 (�ݵ��)
 * 1. ������ ���� ª�� ������� �׷����� ���Խ�Ų��. (���� ���� �������� ����)
 * 2. ���Խ�Ű�� ��, ����Ŭ ���̺��� Ȯ���Ѵ�. ���Խ�Ű�� ����Ŭ�� �߻��ϴ� ��� ���Խ�Ű�� �ʴ´�. (MST�� ����Ŭ�� �߻��ϸ� �ȵǱ� ����) (Union-Find)
 * 3. ����Ŭ�� �������� ������ �׷����� ���Խ�Ų��.
 * 
 * ���� ���� (���1, ���2, ���)
 * 
 * ����Ŭ �߻� ����
 * ����Ŭ ���̺��� �̹� ���� �θ� ������ �ִµ� �����ϸ� ����Ŭ�� �߻��Ѵ�.
 * ���� ���� �θ� ������ �ִ��� Ȯ�� �� �ƴ� ��쿡�� �׷����� ���Խ�Ų��.
 * 
 * ��ü ���� ����
 * ����Ŭ ���̺��� ��� ������ ������ �θ� ���� ������ ��ü ����� �����̴�.
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
		
		// ���� ��� �������� ����
		Collections.sort(edgeList);
		
		// parent �迭 ���� �� �ʱ�ȭ
		int[] parent = new int[nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < edgeCount; i++) {
			// ����Ŭ�� �߻����� �ʴ� ��� �׷����� ����
			Edge e = edgeList.get(i);
			if (!findParent(parent, e.node1 - 1, e.node2 - 1)) {
				totalDistance += e.distance;
				unionParent(parent, e.node1 - 1, e.node2 - 1);
			}
			// ����Ŭ�� �߻��ϴ� ��� ����
		}
		System.out.println("MST �Ÿ�: " + totalDistance);
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
