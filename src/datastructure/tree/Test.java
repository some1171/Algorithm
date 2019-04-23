package datastructure.tree;

/* 
 * Binary Tree Traversals
 * 
 *        (1)
 *       /   \
 *     (2)   (3)
 *     / \
 *   (4) (5)
 *   
 * 1. Inorder	Left - Root - Right		4 - 2 - 5 - 1 - 3
 * 2. Preorder	Root - Left - Right		1 - 2 - 4 - 5 - 3
 * 3. Postorder	Left - Right - Root		4 - 5 - 2 - 3 - 1
 * 
 * */
@SuppressWarnings("rawtypes")
public class Test {
	public static void main(String[] args) {
		Tree tree = new Tree();
		
		Node n5 = tree.makeNode(null, 5, null);
		Node n4 = tree.makeNode(null, 4, null);
		Node n3 = tree.makeNode(null, 3, null);
		Node n2 = tree.makeNode(n4, 2, n5);
		Node n1 = tree.makeNode(n2, 1, n3);
		
		tree.setRoot(n1);
		
		tree.inorder(n1);
		//tree.preorder(n1);
		//tree.postorder(n1);
	}
}
