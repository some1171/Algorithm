package datastructure.map.hashtable;

import java.util.LinkedList;

/*
 * Hash Table
 * f(key); hash function -> HashCode; 정수-> Index; array -> Value; data
 * 
 * key hashcode index list
 * hashcode를 배열의 index로 (배열의 크기에 대한 나머지) direct 접근 -> 빠르다
 * 
 * Hash Algorithm & collision; 충돌(하나의 배열방에 겹쳐서 저장되는 현상)
 * 검색시간 O(1) -> O(n)
 * 
 * different keys -> same hash code 가능
 * different code -> same index 가능
 * 
 * 구현 hash algorithm example
 * 입력받은 각 문자열의 아스키 값의 합
 * 각 배열방 안에 linked list
 */

public class Hashtable {
	private LinkedList<Node>[] data;

	@SuppressWarnings("unchecked")
	public Hashtable(int size) {
		this.data = new LinkedList[size];
	}
	
	public int getHashCode(String key) {
		int hashcode = 0;
		for (char c : key.toCharArray()) {
			hashcode += c;
		}
		return hashcode;
	}
	
	private int convertToIndex(int hashcode) {
		return hashcode % data.length;
	}
	
	private Node searchKey(LinkedList<Node> list, String key) {
		if (list == null) {
			return null;
		}
		
		for (Node node : list) {
			if (node.key().equals(key)) {
				return node;
			}
		}
		
		return null;
	}
	
	public void put(String key, String value) {
		int hashcode = getHashCode(key);
		int index = convertToIndex(hashcode);
		System.out.println(key + ", hashcode(" + hashcode + "), index(" + index + ")");
		
		LinkedList<Node> list = data[index];
		if (list == null) {
			list = new LinkedList<Node>();
			data[index] = list;
		}
		
		Node node = searchKey(list, key);
		if (node == null) {
			list.addLast(new Node(key, value));
		} else {
			node.value(value);
		}
	}
	
	public String get(String key) {
		int hashcode = getHashCode(key);
		int index = convertToIndex(hashcode);
		
		LinkedList<Node> list = data[index];
		Node node = searchKey(list, key);
		
		return node == null ? "Not found" : node.value();
	}
}
