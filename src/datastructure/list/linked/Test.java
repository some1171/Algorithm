package datastructure.list.linked;

public class Test {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();

		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(4);
		ll.print();

		ll.removeAt(3);
		ll.print();
		
		ll.add(1);
		ll.add(2);
		ll.add(1);;
		ll.print();
		
		ll.remove(1);
		ll.print();
		
		ll.removeAll(1);
		ll.print();
	}
}