package datastructure.sort.merge;

/*
 * Merge Sort
 * 배열을 두개씩 묶어 나눈 후 병합해가며 정렬한다.
 * n개씩 logn번 -> O(nlogn)
 * 
 * 4 2 6 3 7 8 5 1
 * 
 * 4 2 6 3 | 7 8 5 1
 * 
 * 4 2 | 6 3 | 7 8 | 5 1
 * 
 * 2 4 | 3 6 | 7 8 | 1 5
 * 
 * 2 3 4 6 | 1 5 7 8
 * 
 * 1 2 3 4 5 6 7  8
 * 
 */

public class Test {
	public static void main(String[] args) {
		int[] arr = {3, 9, 4, 7, 5, 0, 1, 6, 8, 2};
		
		printArray(arr);
		mergeSort(arr);
		printArray(arr);
	}
	
	private static void mergeSort(int[] arr) {
		int[] temp = new int[arr.length];
		mergeSort(arr, temp, 0, arr.length - 1);
	}
	
	private static void mergeSort(int[] arr, int[] temp, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			mergeSort(arr, temp, start, mid);
			mergeSort(arr, temp, mid + 1, end);
			merge(arr, temp, start, mid, end);
		}
	}
	
	private static void merge(int[] arr, int[] temp, int start, int mid, int end) {
		for (int i = start; i <= end; i++) {
			temp[i] = arr[i];
		}
		
		int part1 = start;
		int part2 = mid + 1;
		int index = start;
		
		while (part1 <= mid && part2 <= end) {
			if (temp[part1] <= temp[part2]) {
				arr[index] = temp[part1];
				part1++;
			} else {
				arr[index] = temp[part2];
				part2++;
			}
			index++;
		}
		
		for (int i = 0; i <= mid - part1; i++) {
			arr[index + i] = temp[part1 + i];
		}
	}
	
	private static void printArray(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
