package algorithm.unionfind;

/*
 * Union-Find, 합집합 찾기(Disjoint-set, 서로소 집합)
 * 그래프의 두 정점이 같은 그래프에 속하는지 판별하는 알고리즘 (=연결되어 있는지 판별)
 * 
 * parent 배열을 생성하여 최상위 부모 노드 번호를 저장한다. (부모 값 중 제일 작은 값이 부모가 되도록; 재귀호출)
 * 결론: 부모가 같으면 같은 그래프에 속하는 것이 된다.
 * 
 * *union시 parent배열이 전체적으로 업데이트 되지는 않음. getParent호출 시 배열이 업데이트 된다.
 */
public class Unionfind {
	public static void main(String[] args) {
		int[] parent = new int[11];
		
		// 10개의 노드가 따로 떨어져서 존재한다.
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
	
	// 특정 노드의 부모를 찾는 메소드
	public static int getParent(int[] parent, int n) {
		if (parent[n] == n) {
			return n;
		}
		parent[n] = getParent(parent, parent[n]);
		return parent[n];
	}
	
	// 두 노드를 연결하는 메소드
	public static void unionParent(int[] parent, int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
	
	// 두 노드가 같은 부모를 가지는지 확인한다. = 두 노드가 같은 그래프에 속한다. = 두 노드가 연결되어 있다.
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
