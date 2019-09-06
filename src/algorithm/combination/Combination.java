package algorithm.combination;

public class Combination {
	public static int[] arr = { 1, 2, 3, 4, 5 };

	public static void main(String[] args) {
		//doCombination(0, 0, new int[3], 5, 3);
		doRecombination(0, 0, new int[4], 5, 4);
	}

	public static void doCombination(int pos, int count, int[] array, int n, int r) {
		if (count == r) {
			printArray(array);
			return;
		}
		if (pos == n) {
			return;
		}
		array[count] = arr[pos];
		doCombination(pos + 1, count + 1, array, n, r);
		doCombination(pos + 1, count, array, n, r);
	}

	public static void doRecombination(int pos, int count, int[] array, int n, int r) {
		if (count == r) {
			printArray(array);
			return;
		}
		if (pos == n) {
			return;
		}
		array[count] = arr[pos];
		doRecombination(pos, count + 1, array, n, r);
		doRecombination(pos + 1, count, array, n, r);
	}
	
	public static void printArray(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		return;
	}
}
