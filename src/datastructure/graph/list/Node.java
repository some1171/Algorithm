package datastructure.graph.list;

import java.util.LinkedList;

public class Node {
	private int data;
	private LinkedList<Node> adjacent;
	private boolean marked;
	
	public Node(int data) {
		this.data = data;
		this.adjacent = new LinkedList<Node>();
		this.marked = false;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public LinkedList<Node> getAdjacent() {
		return adjacent;
	}

	public void setAdjacent(LinkedList<Node> adjacent) {
		this.adjacent = adjacent;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}
}
