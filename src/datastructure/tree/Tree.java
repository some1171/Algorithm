package datastructure.tree;

@SuppressWarnings("rawtypes")
public class Tree {
	private Node root;

	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	public <T> Node makeNode(Node left, T item, Node right) {
		Node<T> node = new Node<T>();
		
		node.setItem(item);
		node.setLeft(left);
		node.setRight(right);
		
		return node;
	}
	
	public void inorder(Node node) {
		if (node != null) {
			inorder(node.getLeft());
			System.out.print(node.getItem() + " ");
			inorder(node.getRight());
		}
	}
	
	public void preorder(Node node) {
		if (node != null) {
			System.out.print(node.getItem() + " ");
			preorder(node.getLeft());
			preorder(node.getRight());
		}
	}
	
	public void postorder(Node node) {
		if (node != null) {
			postorder(node.getLeft());
			postorder(node.getRight());
			System.out.print(node.getItem() + " ");
		}
	}
}
