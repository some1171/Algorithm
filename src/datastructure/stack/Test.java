package datastructure.stack;

public class Test {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.print();
		
		stack.pop();
		stack.print();
		
		stack.peek();
		stack.print();
		
		stack.pop();
		stack.pop();
		stack.print();
		
		stack.pop();
		stack.print();
	}
}
