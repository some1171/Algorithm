package datastructure.map.hashtable;

public class Node {
	@SuppressWarnings("unused")
	private String key;
	private String value;

	public Node(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String key() {
		return key;
	}
	
	public String value() {
		return value;
	}
	
	public void value(String value) {
		this.value = value;
	}
}
