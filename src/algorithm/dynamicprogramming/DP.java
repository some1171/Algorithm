package algorithm.dynamicprogramming;

/*
 * Dynamic Programming
 * ���� 1. ū ������ ���� ������ ���� �� �ִ�. (���� ����)
 * ���� 2. ���� �������� ���� ������ �װ��� �����ϴ� ū ���������� �����ϴ�.
 * 
 * Memoization (�޸������̼� ���): �̹� ����� ����� �迭�� ����
 */
public class DP {
	public static int[] fibonacci = new int[50];
	
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(fibo(46));
	}
	
	// �Ǻ���ġ ���� (int����: 46����)
	public static int fibo(int x) {
		if (x == 1) {
			return 1;
		}
		if (x == 2) {
			return 1;
		}
		if (fibonacci[x] != 0) {
			return fibonacci[x];
		}
		return fibonacci[x] = fibo(x - 1) + fibo(x - 2);
	}
}
