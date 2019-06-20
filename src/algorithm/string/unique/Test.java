package algorithm.string.unique;

import java.util.HashMap;

/*
 * Q. ���ڿ� ��������
 * ASCII - 128
 * Extended ASCII - 256
 * Unicode -> 2^10 + 2^16 = 1,114,112
 * alphabet 26 (�ҹ��� a-z: ascii 97 - 122) (0 - 25)
 * int 32��Ʈ  max : 2^31 - 1
 * 
 * Q. ���� ���� ���� ���� ���� ���
 * 1. ���� ���ں��� ���� �� O(n^2)
 * 2. Quick Sort O(n log n) �� ��
 */

public class Test {
	public static void main(String[] args) {
		System.out.println(isUnique("abcdefgghijk"));
		System.out.println(isUnique("abcdefghijk"));
		
		System.out.println(isUniqueWithHashMap("abcdefgghijk"));
		System.out.println(isUniqueWithHashMap("abcdefghijk"));
		
		System.out.println(isUniqueWithBitOperator("abcdefgghijk"));
		System.out.println(isUniqueWithBitOperator("abcdefghijk"));
	}
	
	private static boolean isUnique(String str) {
		if (str.length() > 128) {
			return false;
		}
		
		boolean[] char_set = new boolean[128];
		for (int i = 0; i < str.length(); i++) {
			int value = str.charAt(i);
			
			if (char_set[value]) {
				return false;
			}
			char_set[value] = true;
		}
		
		return true;
	}
	
	private static boolean isUniqueWithHashMap(String str) {
		HashMap<Integer, Boolean> hm = new HashMap<Integer, Boolean>();
		
		for (int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);
			
			if (hm.containsKey(c)) {
				return false;
			}
			hm.put(c, true);
		}
		
		return true;
	}
	
	private static boolean isUniqueWithBitOperator(String str) { 
		int checker = 0;
		
		for (int i = 0; i < str.length(); i++) {
			int value = str.charAt(i) - 97; // str.charAt(i) - 'a'
			
			if ((checker & (1 << value)) > 0) {
				return false;
			}
			checker |= (1 << value);
		}
		
		return true;
	}
}
