package algorithm.dynamicprogramming;

/*
 * Dynamic Programming
 * 가정 1. 큰 문제를 작은 문제로 나눌 수 있다. (분할 정복)
 * 가정 2. 작은 문제에서 구한 정답은 그것을 포함하는 큰 문제에서도 동일하다.
 * 
 * Memoization (메모이제이션 사용): 이미 계산한 결과를 배열에 저장
 */
public class DP {
	public static int[] fibonacci = new int[50];
	
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(fibo(46));
	}
	
	// 피보나치 수열 (int범위: 46이하)
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
