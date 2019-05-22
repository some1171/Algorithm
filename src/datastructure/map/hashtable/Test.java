package datastructure.map.hashtable;

public class Test {
	public static void main(String[] args) {
		Hashtable ht = new Hashtable(3);
		
		ht.put("apple", "apple is red");
		ht.put("banana", "banana is yellow");
		ht.put("kiwi", "kiwi is green");
		ht.put("strawberry", "strawberry is red");
		ht.put("kiwi", "kiwi is sour");
		
		System.out.println(ht.get("apple"));
		System.out.println(ht.get("banana"));
		System.out.println(ht.get("kiwi"));
		System.out.println(ht.get("strawberry"));
		System.out.println(ht.get("melon"));
	}
}
