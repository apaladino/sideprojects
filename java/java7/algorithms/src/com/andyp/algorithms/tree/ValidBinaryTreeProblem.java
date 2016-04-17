package com.andyp.algorithms.tree;

public class ValidBinaryTreeProblem {

	public static void main(String[] args) {
		new ValidBinaryTreeProblem().run();
	}

	public void run() {

		// test with valid binary tree
		Node root = buildValidBTree();

		boolean result = isValidBinaryTree(root);
		System.out.println("Result with valid binary tree: " + result);

		// test with invalid binary tree
		root = buildInValidBTree();
		result = isValidBinaryTree(root);
		System.out.println("Result with invalid binary tree: " + result);
		
		System.out.println("Result with node with no branches: " + isValidBinaryTree(new Node(44)));
		
		// test with node with only left branch
		Node n = new Node(7);
		n.setLeft(new Node(4));
		System.out.println("Result with node with no right branch: " + isValidBinaryTree(n));
		
		
	}

	public boolean isValidBinaryTree(Node n) {
		
		
		return (n != null) && (n.getLeft() != null) && n.getRight() != null 
				&& isValidLeftBranch(n) && isValidRightBranch(n);
	}

	// Left branch is valid if n.left < n for all n.
	public boolean isValidLeftBranch(Node n) {
		if (n == null || n.getLeft() == null)
			return true;

		return (n.getLeft().getVal() < n.getVal())
				&& isValidLeftBranch(n.getLeft());

	}

	public boolean isValidRightBranch(Node n) {

		if (n == null || n.getRight() == null)
			return true;

		return (n.getRight().getVal() > n.getVal())
				&& isValidRightBranch(n.getRight());
	}

	public Node buildValidBTree() {
		Node root = new Node(7);
		Node l1 = new Node(5);
		Node r1 = new Node(10);
		Node l2l = new Node(4);
		Node l2r = new Node(6);
		l1.setLeft(l2l);
		l1.setRight(l2r);
		root.setLeft(l1);
		Node r21 = new Node(8);
		Node r2r = new Node(12);
		root.setRight(r1);
		r1.setLeft(r21);
		r1.setRight(r2r);
		return root;
	}

	public Node buildInValidBTree() {
		Node root = new Node(7);
		Node l1 = new Node(5);
		Node r1 = new Node(10);
		Node l2l = new Node(4);
		Node l2r = new Node(6);
		l1.setLeft(l2l);
		l1.setRight(l2r);
		root.setLeft(l1);
		Node r21 = new Node(3);
		Node r2r = new Node(2);
		root.setRight(r1);
		r1.setLeft(r21);
		r1.setRight(r2r);
		return root;
	}

}

class Node {

	private int val;
	private Node left;
	private Node right;

	public Node(int v) {
		this.val = v;
	}

	public void setVal(int v) {
		this.val = v;
	}

	public int getVal() {
		return val;
	}

	public void setLeft(Node l) {
		this.left = l;
	}

	public Node getLeft() {
		return left;
	}

	public void setRight(Node r) {
		this.right = r;
	}

	public Node getRight() {
		return right;
	}

}
