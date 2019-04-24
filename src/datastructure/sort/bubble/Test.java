package datastructure.sort.bubble;

/*
 * Bubble Sort: O(n^2)
 * 앞에서부터 두개씩 비교
 * 3 5 4 2 1
 * 
 * i.
 * 3 5 4 2 1
 * 3 4 5 2 1
 * 3 4 2 5 1
 * 3 4 2 1 5
 * 
 * ii.
 * 3 4 2 1 5
 * 3 2 4 1 5
 * 3 2 1 4 5
 * 
 * iii.
 * 2 3 1 4 5
 * 2 1 3 4 5
 * 
 * iv.
 * 1 2 3 4 5
 * 
 */

public class Test {
	public static void main(String[] args) {
		int[] array = { 3, 5, 4, 2, 1 };

		printArray(array);
		//bubbleSort(array);
		bubbleSortRecursive(array);
		printArray(array);
	}

	private static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
	}

	private static void bubbleSortRecursive(int[] arr) {
		bubbleSortRecursive(arr, arr.length - 1);
	}

	private static void bubbleSortRecursive(int[] arr, int last) {
		if (last > 0) {
			for (int i = 0; i < last; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
				}
			}
			bubbleSortRecursive(arr, last - 1);
		}
	}

	private static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	private static void printArray(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
