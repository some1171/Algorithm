package datastructure.stack;

import java.util.EmptyStackException;

public class Stack<T> {
	private Node<T> top;
	
	public T pop() {
		if (top == null) {
			throw new EmptyStackException();
		}
		
		T item = top.item;
		top = top.next;
		
		return item;
	}
	
	public void push(T item) {
		Node<T> node = new Node<T>(item);
		node.next = top;
		top = node;
	}
	
	public T peek() {
		if (top == null) {
			throw new EmptyStackException();
		}
		
		return top.item;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public void print() {
		Node<T> node = top;
		
		while (node != null) {
			System.out.print(node.item + " ");
			node = node.next;
		}
		System.out.println();
	}
	
	@SuppressWarnings("hiding")
	private class Node<T> {
		private T item;
		private Node<T> next;
		
		public Node(T item) {
			this.item = item;
		}
	}
}
