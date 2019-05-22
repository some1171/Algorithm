package datastructure.etc.stringbuilder;

/*
 * String			: 연산시마다 객체 생성
 * StringBuilder	: 배열 공간 연산, async, 빠르다
 * StringBuffer		: synchronized, thread safe
 * 
 */

public class StringBuilder {
	private char[] value;
	private int size;
	private int index;
	
	public StringBuilder() {
		size = 1;
		value = new char[size];
		index = 0;
	}
	
	public StringBuilder(String str) {
		size = str.length() * 2;
		value = new char[size];
		index = 0;
		append(str);
	}
	
	public void append(String str) {
		if (str == null) {
			return;
		}
		
		int len = str.length();
		ensureCapacity(len);
		
		for (int i = 0; i < str.length(); i++) {
			value[index] = str.charAt(i);
			index++;
		}
		System.out.println("(" + size + ", " + index + ")");
	}

	private void ensureCapacity(int len) {
		if (index + len > size) {
			size = (size + len) * 2;
			char[] newValue = new char[size];
			
			for (int i = 0; i < value.length; i++) {
				newValue[i] = value[i];
			}
			value = newValue;
		}
		System.out.println("(" + size + ", " + index + ")");
	}
	
	public String toString() {
		return new String(value, 0, index);
	}
}
