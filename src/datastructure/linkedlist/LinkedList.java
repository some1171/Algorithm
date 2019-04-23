package datastructure.linkedlist;

public class LinkedList {
	private Node header;
	private int size;

	public LinkedList() {
		header = new Node();
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public void add(int item) {
		Node end = new Node(item);
		Node node = header;

		while (node.next != null) {
			node = node.next;
		}

		size++;
		node.next = end;
	}

	public void remove(int item) {
		Node node = header;

		while (node.next != null) {
			if (node.next.item == item) {
				node.next = node.next.next;
				size--;
				
				return;
			} else {
				node = node.next;
			}
		}
	}
	
	public void removeAt(int index) {
		Node node = header;
		
		if (index < 0 || index > this.size() - 1) {
			System.out.println("Incorrect Index!");
			
			return;
		}
		
		if (index == 0) {
			header.next = header.next.next;
			size--;
			
			return;
		}
		
		for (int i = 0; i < index; i++) {
			node = node.next;
			
			if (index == this.size() - 1 && i == this.size() - 2) {
				node.next = null;
				size--;
				
				return;
			}
		}
		
		size--;
		node.next = node.next.next;
	}
	
	void removeAll(int item) {
		Node node = header;

		while (node.next != null) {
			if (node.next.item == item) {
				node.next = node.next.next;
				size--;
			} else {
				node = node.next;
			}
		}
	}
	
	public void clear() {
		header.next = null;
		size = 0;
	}
	
	public int get(int index) {
		
		if (index < 0 || index > this.size() - 1) {
			System.out.println("Incorrect Index!");
			
			return 0;
		}
		
		Node node = header.next;

		for (int i = 0; i < index; i++) {
			node = node.next;
		}

		return node.item;
	}
	
	public void print() {
		Node node = header.next;

		if (node == null) {
			return;
		}

		while (node.next != null) {
			System.out.print(node.item + " -> ");
			node = node.next;
		}

		System.out.println(node.item);
	}
	
	private static class Node {
		int item;
		Node next;
		
		Node() {
			
		}
		
		Node(int item) {
			this.item = item;
		}
	}
}