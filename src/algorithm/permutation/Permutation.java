package algorithm.permutation;

public class Permutation {
	public static int[] arr = { 1, 2, 3, 4, 5 };
	public static boolean[] isPick = new boolean[5];

	public static void main(String[] args) {
		doPermutation(0, 5, 3, new int[3], new boolean[5]);
	}

	public static void doPermutationWithSwap(int depth, int[] array, int n, int r) {
		if (depth == r) {
			printArray(array);
			return;
		}
		
		for (int i = depth; i < n; i++) {
			swap(arr, i, depth);
			array[depth] = arr[i];
			doPermutationWithSwap(depth + 1, array, n, r);
			swap(arr, i, depth);
		}
	}
	
	public static void doPermutation(int depth, int n, int r, int[] array, boolean[] visit) {
		if (depth == r) {
			printArray(array);
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				visit[i] = true;
				array[depth] = arr[i];
				doPermutation(depth + 1, n, r, array, visit);
				visit[i] = false;
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void printArray(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
