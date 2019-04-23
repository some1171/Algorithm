package datastructure.queue;

public class Test {
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<Integer>();
		
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.print();
		
		queue.poll();
		queue.print();
		
		queue.poll();
		queue.poll();
		queue.poll();
		queue.print();
		
		queue.poll();
		queue.print();
	}
}
