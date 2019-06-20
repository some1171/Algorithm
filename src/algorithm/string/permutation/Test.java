package algorithm.string.permutation;

import java.util.Arrays;

/*
 * Q. 두 개의 문자열이 서로 치환되는지 여부 판별
 * 가지고 있는 문자의 종류와 갯수가 동일해야 한다.
 */
public class Test {
	public static void main(String[] args) {
		System.out.println(isPermutation("ABC", "BCA"));
		System.out.println(isPermutation("ABC", "BDA"));
		
		System.out.println(isPermutationWithAscii("ABC", "BCA"));
		System.out.println(isPermutationWithAscii("ABC", "BDA"));
	}
	
	private static boolean isPermutation(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		
		return sort(s).equals(sort(t));
	}
	
	private static String sort(String s) {
		char[] content = s.toCharArray();
		Arrays.sort(content);
		
		return new String(content);
	}
	
	private static boolean isPermutationWithAscii(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		
		int[] ascii = new int[128];
		for (int i = 0; i < s.length(); i++) {
			ascii[s.charAt(i)]++;
		}
		for (int i = 0; i < t.length(); i++) {
			ascii[t.charAt(i)]--;
			if (ascii[t.charAt(i)] < 0) {
				return false;
			}
		}
		
		return true;
	}
}
