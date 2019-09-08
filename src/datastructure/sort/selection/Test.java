package datastructure.sort.selection;

/*
 * Selection Sort: O(n^2)
 * 3 5 4 2 1
 * 
 * i. index = 0, min = 3
 * min = 1
 * swap: 1 5 4 2 3
 * 
 * ii. index = 1, min = 5
 * min = 2
 * swap: 1 2 4 5 3
 * 
 * iii. index = 2, min = 4
 * min = 3
 * swap: 1 2 3 5 4
 * 
 * iv. index = 3, min = 5
 * min = 4
 * swap: 1 2 3 4 5
 * 
 */

public class Test {
	public static void main(String[] args) {
		int[] array = { 3, 5, 4, 2, 1 };

		printArray(array);
		// selectionSort(array);
		selectionSortRecursive(array);
		printArray(array);
	}

	@SuppressWarnings("unused")
	private static void selectionSort(int[] arr) {
		int min, temp;

		for (int i = 0; i < arr.length - 1; i++) {
			min = arr[i];

			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < min) {
					min = arr[j];
					arr[j] = arr[i];
					arr[i] = min;
				}
			}
		}
	}

	private static void selectionSortRecursive(int[] arr) {
		selectionSortRecursive(arr, 0);
	}

	private static void selectionSortRecursive(int[] arr, int start) {
		if (start < arr.length - 1) {
			int min_index = start;

			for (int i = start; i < arr.length; i++) {
				if (arr[i] < arr[min_index]) {
					min_index = i;
				}
			}
			swap(arr, start, min_index);
			selectionSortRecursive(arr, start + 1);
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
