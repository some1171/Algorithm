package datastructure.queue.withtwostack;

public class Test {
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<Integer>();
		
		queue.add(1);
		queue.add(2);
		queue.add(3);
		
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
	}
}
