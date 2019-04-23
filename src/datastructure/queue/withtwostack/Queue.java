package datastructure.queue.withtwostack;

import java.util.Stack;

public class Queue<T> {
	Stack<T> newStack, oldStack;

	Queue() {
		newStack = new Stack<T>();
		oldStack = new Stack<T>();
	}

	public int size() {
		return newStack.size() + oldStack.size();
	}

	public void add(T item) {
		newStack.push(item);
	}

	private void pourStack() {
		if (oldStack.isEmpty()) {
			while (!newStack.isEmpty()) {
				oldStack.push(newStack.pop());
			}
		}
	}
	
	public T peek() {
		pourStack();
		
		return oldStack.peek();
	}
	
	public T pop() {
		pourStack();
		return oldStack.pop();
	}
}
