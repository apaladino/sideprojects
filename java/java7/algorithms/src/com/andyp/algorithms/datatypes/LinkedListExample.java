package com.andyp.algorithms.datatypes;

public class LinkedListExample {
	
	public void run(){
		
		DoubleLinkedList l = new DoubleLinkedList();
	
		listInsert(l, new DoubleNode("Eve", null, null));
		listInsert(l, new DoubleNode("Willie", null, null));
		listInsert(l, new DoubleNode("Sam", null, null));
		
	}
	
	private void deleteFromList(DoubleLinkedList list, DoubleNode x){
	
		/*
		 * Use cases:  x is head, x is tail, x is in middle, x is not in list
		 */
		
		if(x == null)
			return;
		
		if(x.getPrev() != null)
			x.getPrev().setNext(x.getNext());
		else
			list.setHead(x.getNext());
		
		if(x.getNext() != null)
			x.getNext().setPrev(x.getPrev());
			
	}
	
	private void listInsert(DoubleLinkedList list, DoubleNode x){
		
		// x.next points to current list head
		x.next = list.getHead();
		
		// current list head.prev will point to x
		if(list.getHead() != null)
			list.getHead().setPrev(x); 
		
		// x will be new head
		list.setHead(x);
		
		x.setPrev(null);
		
	}
	
	
	private DoubleNode listSearch(DoubleLinkedList list, String key){
		
		DoubleNode n = list.getHead();
		
		while(n != null && n.getKey() != key)
			n = n.next;
		
		return n;
	}
	
	
	
	public static void main(String a[]){
		new LinkedListExample().run();
	}
	
	class DoubleLinkedList{
		
		private DoubleNode head;
	
		protected DoubleNode getHead() {
			return head;
		}
		protected void setHead(DoubleNode head) {
			this.head = head;
		}
	}
	
	class Node{
		private Node next;

		protected Node getNext() {
			return next;
		}

		protected void setNext(Node next) {
			this.next = next;
		}
		
	}
	
	class DoubleNode{
		private String key;
		private DoubleNode next;
		private DoubleNode prev;
		
		public DoubleNode(String k, DoubleNode n, DoubleNode p){
			this.key = k;
			this.next = n;
			this.prev = p;
		}
		
		protected String getKey() {
			return key;
		}
		protected void setKey(String key) {
			this.key = key;
		}
		protected DoubleNode getNext() {
			return next;
		}
		protected void setNext(DoubleNode next) {
			this.next = next;
		}
		protected DoubleNode getPrev() {
			return prev;
		}
		protected void setPrev(DoubleNode prev) {
			this.prev = prev;
		}
		
	}

}
