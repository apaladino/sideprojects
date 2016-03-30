package com.andyp.algorithms;

/**
 * Problem:  Given a singly linked list, starting from the head node, reverse the list
 */
public class ReverseSingleLinkedList {

	public static void main(String [] args){
		new ReverseSingleLinkedList().run();
	}
	
	public void run(){
		
		Node head = genLinkedList();
		
		Node reversedHead = reverse(head);
		
		printNode(reversedHead);
		
		System.out.println("Finished.");
	}
	
	private Node reverse(Node node) {
		
		Node lastNode = null;
		Node next = null;
		
		while(node != null){
			
			next = node.next;
			node.next = lastNode;
			
			lastNode = node;
			node = next;
		}
		
		return lastNode;
	}

	private void printNode(Node node) {
		
		while(node != null){
			System.out.println("Node: " + node);
			node = node.next;
		}
	}

	private Node genLinkedList() {
		
		String[] values = { "a", "b", "c", "e", "f", "g", "h", "i"};
		
		Node head = null;
		Node lastNode = null;
		
		for(int i=0; i < values.length; i++){
			
			Node node = new Node();
			
			if(lastNode != null){
				lastNode.next = node;
			}
			
			node.value = values[i];
			
			
			if(i==0)
				head = node;
			
			lastNode = node;
			
		}

		return head;
	}

	// Node class
	class Node{
		public String value;
		public Node next;
		
		public String toString(){
			return "[ value=" + value + "\tnext: " + ((next != null) ? next.value : "NULL") + "]";
		}
	}
}
