package datastructure.sort.insertion;

/*
 * 미완성
 * Insertion Sort O(n^2)
 * 앞 또는 뒤 에서부터 이미 정렬된 부분과 비교하며 삽입 위치 탐색
 * 
 * 정렬된 원소 집합, 미정렬 원소 집합
 * 69 10 30 2 16 8 31 22
 * 
 * 10 69 30 2 16 8 31 22
 * 10 30 69 2 16 8 31 22
 * 2 10 30 69 16 8 31 22
 */

public class Test {
	public static void main(String[] args) {
		
	}
	
	private static int[] insertionSort(int[] arr) {
		return insertionSort(arr, 1);
	}
	
	private static int[] insertionSort(int[] arr, int idx) {
		if (idx == arr.length - 1) {
			return arr;
		}
		int point = idx - 1;
		
		while (true) {
			if (point == 0) {
				break;
			}
		}
		
		insertionSort(arr, idx + 1);
		
		return arr;
	}
	
	private static void printArray(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
