package algorithm.unionfind;

/*
 * Union-Find, ������ ã��(Disjoint-set, ���μ� ����)
 * �׷����� �� ������ ���� �׷����� ���ϴ��� �Ǻ��ϴ� �˰��� (=����Ǿ� �ִ��� �Ǻ�)
 * 
 * parent �迭�� �����Ͽ� �ֻ��� �θ� ��� ��ȣ�� �����Ѵ�. (�θ� �� �� ���� ���� ���� �θ� �ǵ���; ���ȣ��)
 * ���: �θ� ������ ���� �׷����� ���ϴ� ���� �ȴ�.
 * 
 * *union�� parent�迭�� ��ü������ ������Ʈ ������ ����. getParentȣ�� �� �迭�� ������Ʈ �ȴ�.
 */
public class Unionfind {
	public static void main(String[] args) {
		int[] parent = new int[11];
		
		// 10���� ��尡 ���� �������� �����Ѵ�.
		for (int i = 1; i <= 10; i++) {
			parent[i] = i;
		}
		
		printArray(parent);
		
		unionParent(parent, 1, 2);
		unionParent(parent, 2, 3);
		unionParent(parent, 3, 4);
		
		unionParent(parent, 5, 6);
		unionParent(parent, 6, 7);
		unionParent(parent, 7, 8);
		
		unionParent(parent, 9, 10);
		
		printArray(parent);
		
		System.out.println("1 - 2?: " + findParent(parent, 1, 2));
		System.out.println("2 - 4?: " + findParent(parent, 2, 4));
		System.out.println("3 - 5?: " + findParent(parent, 3, 5));
		
		unionParent(parent, 1, 5);
		
		printArray(parent);
		System.out.println("3 - 7?: " + findParent(parent, 3, 7));
		printArray(parent);
		System.out.println("7 - 8?: " + findParent(parent, 7, 8));
		printArray(parent);
		
	}
	
	// Ư�� ����� �θ� ã�� �޼ҵ�
	public static int getParent(int[] parent, int n) {
		if (parent[n] == n) {
			return n;
		}
		parent[n] = getParent(parent, parent[n]);
		return parent[n];
	}
	
	// �� ��带 �����ϴ� �޼ҵ�
	public static void unionParent(int[] parent, int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
	
	// �� ��尡 ���� �θ� �������� Ȯ���Ѵ�. = �� ��尡 ���� �׷����� ���Ѵ�. = �� ��尡 ����Ǿ� �ִ�.
	public static boolean findParent(int[] parent, int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a == b) {
			return true;
		}
		return false;
	}
	
	public static void printArray(int[] arr) {
		for (int i = 1; i <= 10; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 1; i <= 10; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
