package datastructure.map.hashtable;

import java.util.LinkedList;

/*
 * Hash Table
 * f(key); hash function -> HashCode; ����-> Index; array -> Value; data
 * 
 * key hashcode index list
 * hashcode�� �迭�� index�� (�迭�� ũ�⿡ ���� ������) direct ���� -> ������
 * 
 * Hash Algorithm & collision; �浹(�ϳ��� �迭�濡 ���ļ� ����Ǵ� ����)
 * �˻��ð� O(1) -> O(n)
 * 
 * different keys -> same hash code ����
 * different code -> same index ����
 * 
 * ���� hash algorithm example
 * �Է¹��� �� ���ڿ��� �ƽ�Ű ���� ��
 * �� �迭�� �ȿ� linked list
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
