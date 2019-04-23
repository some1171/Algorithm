package datastructure.queue;

import java.util.NoSuchElementException;

public class Queue<T> {
	private Node<T> first;
	private Node<T> last;
	
	public void add(T item) {
		Node<T> node = new Node<T>(item);
		
		if (last != null) {
			last.next = node;
		}
		last = node;
		
		if (first == null) {
			first = last;
		}
	}
	
	public T poll() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		
		T item = first.item;
		first = first.next;
		
		if (first == null) {
			last = null;
		}
		
		return item;
	}
	
	public T peek() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		
		return first.item;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void print() {
		Node<T> node = first;
		
		if (first == null) {
			return;
		}
		
		while (node.next != null) {
			System.out.print(node.item + " ");
			node = node.next;
		}
		System.out.println(node.item);
	}
	
	private static class Node<T> {
		private T item;
		private Node<T> next;
		
		public Node(T item) {
			this.item = item;
		}
	}
}
