package datastructure.graph.list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	Node[] nodes;

	public Graph(int size) {
		nodes = new Node[size];

		for (int i = 0; i < size; i++) {
			nodes[i] = new Node(i);
		}
	}

	public void addEdge(int i1, int i2) {
		Node n1 = nodes[i1];
		Node n2 = nodes[i2];

		if (!n1.getAdjacent().contains(n2)) {
			n1.getAdjacent().add(n2);
		}
		if (!n2.getAdjacent().contains(n1)) {
			n2.getAdjacent().add(n1);
		}
	}

	public void dfs() {
		dfs(0);
	}

	public void dfs(int index) {
		Node root = nodes[index];

		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		root.setMarked(true);

		while (!stack.isEmpty()) {
			Node node = stack.pop();

			for (Node n : node.getAdjacent()) {
				if (n.isMarked() == false) {
					n.setMarked(true);
					stack.push(n);
				}
			}

			visit(node);
		}
	}

	public void dfsRecursive() {
		dfsRecursive(0);
	}
	
	public void dfsRecursive(Node node) {
		if (node == null) {
			return;
		}

		node.setMarked(true);
		visit(node);

		for (Node n : node.getAdjacent()) {
			if (n.isMarked() == false) {
				dfsRecursive(n);
			}
		}
	}

	public void dfsRecursive(int index) {
		Node node = nodes[index];
		dfsRecursive(node);
	}

	public void bfs() {
		bfs(0);
	}

	public void bfs(int index) {
		Node root = nodes[index];

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		root.setMarked(true);

		while (!queue.isEmpty()) {
			Node node = queue.poll();

			for (Node n : node.getAdjacent()) {
				if (n.isMarked() == false) {
					n.setMarked(true);
					queue.add(n);
				}
			}

			visit(node);
		}

	}

	public void visit(Node node) {
		System.out.print(node.getData() + " ");
	}
}
