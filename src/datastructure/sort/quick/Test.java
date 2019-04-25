package datastructure.sort.quick;

/* 
 * Quick Sort: O(nlogn), worst: O(n^2)
 * pivot -> partitioning 기준값 기준 정렬 반복
 * 
 * s                 e
 * 3 9 4 7 5 0 1 6 8 2
 * pivot = 5
 * 9 - 2 swap
 * 큰 값 찾으면 s 중지 e 이동
 * 
 *   s               e
 * 3 9 4 7 5 0 1 6 8 2
 * 
 *       s     e
 * 3 2 4 7 5 0 1 6 8 9
 * 
 *       s     e
 * 3 2 4 1 5 0 7 6 8 9
 * 
 *         s e
 * 3 2 4 1 0 5 7 6 8 9
 * 
 *         e s
 * 3 2 4 1 0 5 7 6 8 9
 * 
 */

public class Test {
	public static void main(String[] args) {
		int[] arr = {3, 9, 4, 7, 5, 0, 1, 6, 8, 2};
		
		printArray(arr);
		quickSort(arr);
		printArray(arr);
	}
	
	private static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}
	
	private static void quickSort(int[] arr, int start, int end) {
		int part2 = partition(arr, start, end);
		
		if (start < part2 - 1) {
			quickSort(arr, start, part2 - 1);
		}
		if (part2 < end) {
			quickSort(arr, part2, end);
		}
		
	}
	
	private static int partition(int[] arr, int start, int end) {
		int pivot = arr[(start + end) / 2];
		
		while (start <= end) {
			while (arr[start] < pivot) {
				start++;
			}
			while (arr[end] > pivot) {
				end--;
			}
			if (start <= end) {
				swap(arr, start, end);
				start++;
				end--;
			}
		}
		
		return start;
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
