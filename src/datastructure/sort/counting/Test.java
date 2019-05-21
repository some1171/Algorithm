package datastructure.sort.counting;

import java.util.Arrays;

/*
 * Counting Sort: O(n+k) (k�� ������ �ִ밪)
 * ���� ����
 * i. ������ ������ ǥ���� �� �ִ� �ڷῡ ���ؼ��� ���� ���� (���� �׸����� �ε����Ǵ� ī��Ʈ���� �迭�� ����ϱ� ����)
 * ii. ���� ���� ���� ū ������ �˾ƾ� ��
 * 
 * 0 4 1 3 1 2 4 1
 * 
 * i.
 * 0 1 2 3 4
 * 1 3 1 1 2
 * 
 * ii. �� �׸��� �տ� ��ġ�� �׸��� ���� �ݿ�
 * 0 1 2 3 4
 * 1 4 5 6 8
 * 
 * iii. ������ �׸���� ���� ����
 * data		0 4 1 3 1 2 4 1
 * counts	1 3 5 6 8 
 * temp		_ _ _ 1 _ _ _ _
 * 
 * data		0 4 1 3 1 2 4 1
 * counts	1 3 5 6 7 
 * temp		_ _ _ 1 _ _ _ 4
 */

public class Test {
	public static void main(String[] args) {
		int[] array = {0, 4, 1, 3, 1, 2, 4, 1};
		int[] sortedArray = new int[array.length];
		
		printArray(array);
		sortedArray = countingSort(array);
		printArray(sortedArray);
	}
	
	private static int[] countingSort(int[] arr) {
		int[] counts = new int[returnMaxValue(arr) + 1];
		int[] temp = new int[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			counts[arr[i]]++;
		}
		
		for (int i = 1; i < counts.length; i++) {
			counts[i] = counts[i] + counts[i - 1];
		}
		
		for (int i = arr.length - 1; i >= 0; i--) {
			temp[counts[arr[i]] - 1] = arr[i];
			counts[arr[i]]--;
		}

		return temp;
	}
	
	private static void countingSortRecursive(int[] data, int[] counts, int[] temp) {
		
	}
	
	private static void countingSortRecursive(int[] data, int[] counts, int[] temp, int index) {
		// return
	}
	
	private static int returnMaxValue(int[] arr) {
		int max = arr[0];
		
		for (int i : arr) {
			max = Math.max(i, max);
		}
		
		return max;
	}
	
	private static void printArray(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
