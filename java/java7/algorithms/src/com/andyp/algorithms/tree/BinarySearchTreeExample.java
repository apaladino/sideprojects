package com.andyp.algorithms.tree;

public class BinarySearchTreeExample {

	public static void main(String a[]){
		
		new BinarySearchTreeExample().run();
	}
	
	public void run(){
		
		BinarySearchTree tree = new BinarySearchTree();
		
		tree.insert(new Node(12, null, null, null));
		tree.insert(new Node(5, null, null, null));
		tree.insert(new Node(16, null, null, null));
	}
	
	class BinarySearchTree{
		
		private Node root;
		
		
		public void insert(Node n){
			Node y = null;
			Node x = root;
			
			while(x != null){
				y = x;
				if(n.getKey() < x.getKey()){
					x = n.getLeft();
				}else{
					x = n.getRight();
				}
			}
			
			// insert n into correct spot
			n.setParent(y);
			
			if(y == null)	// tree was empty
				root = n;
			else if(n.getKey() < y.getKey()){
				y.setLeft(n);
			}else{
				y.setRight(n);
			}
			
		}
		
		public Node getRoot(){ 
			return root;
		}
		
		public void setRoot(Node r){
			this.root = r;
		}
	}
	
	
	class Node{
		
		private int key;
		private Node parent,
					 left,
					 right;
		
		public Node(int k, Node p, Node left, Node r){
			this.key = k;
			this.parent = p;
			this.left = left;
			this.right = r;
		}
		
		protected int getKey() {
			return key;
		}
		protected void setKey(int key) {
			this.key = key;
		}
		protected Node getParent() {
			return parent;
		}
		protected void setParent(Node parent) {
			this.parent = parent;
		}
		protected Node getLeft() {
			return left;
		}
		protected void setLeft(Node l) {
			this.left = l;
		}
		protected Node getRight() {
			return right;
		}
		protected void setRight(Node r) {
			this.right = r;
		}
	}
}
