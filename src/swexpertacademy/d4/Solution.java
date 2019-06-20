package swexpertacademy.d4;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int problem = 0;
		
		while (problem++ < 10) {
			int half, index, answer = 0;
			int count = sc.nextInt();
			Node[] tree = new Node[count + 1];
			
			for (int i = 0; i < count; i++) {
				index = sc.nextInt();
				half = count / 2;
				
				if (index < half) {
					tree[index] = new Node(sc.next(), sc.nextInt(), sc.nextInt());
				} else if (index == half) {
					if (count % 2 == 0) {
						tree[index] = new Node(sc.next(), sc.nextInt());
					} else {
						tree[index] = new Node(sc.next(), sc.nextInt(), sc.nextInt());
					}
				} else {
					tree[index] = new Node(sc.next());
				}
			}
			
			System.out.println("#" + problem + " " + answer);
		}
		sc.close();
	}
	
	private static boolean isNumeric(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	private static int inOrder(Node[] tree, Node node) {
		if (node != null) {
			System.out.print(node.data);
			if (isNumeric(node.data)) {
				if (tree[node.left] != null || tree[node.right] != null) {
					return 0;
				}
			}
			inOrder(tree, tree[node.left]);
			inOrder(tree, tree[node.right]);
		}
		
		return 1;
	}
}