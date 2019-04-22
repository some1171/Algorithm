package datastructure;

public class LinkedListTest {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();

		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.print();

		ll.print();

	}
}

class LinkedList {
	Node header;

	LinkedList() {
		header = new Node();
	}

	void append(int data) {
		Node end = new Node();
		end.data = data;
		Node node = header;

		while (node.next != null) {
			node = node.next;
		}

		node.next = end;
	}

	void delete(int data) {
		Node node = header;

		while (node.next != null) {
			if (node.next.data == data) {
				node.next = node.next.next;
			} else {
				node = node.next;
			}
		}
	}

	void deleteAt(int index) {
		Node node = header.next;
		
		if (index < 0 || index > this.size() - 1) {
			System.out.println("Incorrect Index!");
			
			return;
		}
		
		for (int i = 0; i <= index; i++) {
			if (index == this.size() - 1 && i == this.size() - 2) {
				node.next = null;
				
				return;
			} else if (index == 0) {
				node = node.next;
				header.next = node;
				
				return;
			}
			
			node = node.next;
		}
		
		node.next = node.next.next;
	}

	void deleteAll() {
		header.next = null;
	}

	int get(int index) {
		
		if (index < 0 || index > this.size() - 1) {
			System.out.println("Incorrect Index!");
			
			return 0;
		}
		
		Node node = header.next;

		for (int i = 0; i < index; i++) {
			node = node.next;
		}

		return node.data;
	}
	
	int size() {
		int size = 1;
		Node node = header.next;
		
		while (node.next != null) {
			node = node.next;
			size++;
		}
		
		return size;
	}

	void print() {
		Node node = header.next;

		if (node == null) {
			return;
		}

		while (node.next != null) {
			System.out.print(node.data + " -> ");
			node = node.next;
		}

		System.out.println(node.data);
	}
}

class Node {
	int data;
	Node next = null;
}
