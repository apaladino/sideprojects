package com.andyp.algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Problem:  Given k sorted lists, each of length n, provide an algorithm
 * 			 to merge the k sorted lists into a single list of length kn. What data structure
 * 			 might you want to use?  What is the time complexity of your solution?
 */
public class MergeKSortedListsProblem {
	
	public static void main(String a[]){
		new MergeKSortedListsProblem().run();
	}

	public void run(){
		//runTestsWithIntLists();
		Node l1 = buildLinkedList(new int[] { 1, 2, 3, 4});
		Node l2 = buildLinkedList(new int[] { 8, 10, 12, 14});
		Node l3 = buildLinkedList(new int[] { 5, 7, 9, 11});
		List<Node> lists = Arrays.asList(l1, l2, l3);
		
		Node mergedList = mergeKLists(lists);
		printLinkedList(mergedList);
	}

	private void printLinkedList(Node n) {
		
		int count = 0;
		while(n != null){
			if(count > 0){
				System.out.print(", ");
			}
			
			System.out.print(n);
			n = n.getNext();
			count++;
		}
	}

	private Node mergeKLists(List<Node> lists) {
		
		PriorityQueue <Node>pq = new PriorityQueue<>(lists.size(), new NodeComparator());
		
		// loop through each list
		for(Node n : lists){
			while(n!=null){
				pq.add(n);
				n = n.next;
			}
		}
		
		// now start polling from priorty queu and rebuilding linked list in order
		Node root = (Node) pq.poll();
		root.setNext(null);
		Node p = root;
		while(!pq.isEmpty()){
			Node current = pq.poll();
			current.setNext(null);
			p.setNext(current);
			p=current;
		}
		
		return root;
	}
	
	class NodeComparator implements Comparator<Node>{

		@Override
		public int compare(Node n1, Node n2) {
			return Integer.valueOf(n1.getVal()).compareTo(Integer.valueOf(n2.getVal()));
		}
		
	}

	private Node buildLinkedList(int[] l) {
		
		if(l == null || l.length == 0)	return null;
		
		Node head = new Node(l[0]);
		Node p = head;
		if(l.length > 1){
			for(int i=1; i < l.length; i++){
				Node next = new Node(l[i]);
				p.setNext(next);
				p = next;
			}
		}
		
		return head;
	}

	class Node{
		int val;
		Node next;
		
		public Node(int val){
			this.val = val;
		}
		
		public int getVal(){
			return val;
		}
		
		public Node getNext() {
			return next;
		}
		
		public void setNext(Node next){
			this.next = next;
		}
		
		public String toString(){
			return Integer.toString(val);
		}		
	}
	
	protected void runTestsWithIntLists() {
		int[] a = new int[]{ 1, 2, 3, 4};
		int[] b = new int[]{ 8, 10, 12, 14};
		int[] c = new int[]{ 5, 7, 9, 11};
		
		List<int[]> sortedLists = Arrays.asList(a, b, c);
		
		int[] mergesList = mergeSortedIntegerLists(sortedLists);
		System.out.println(Arrays.toString(mergesList));
	}

	private int[] mergeSortedIntegerLists(List<int[]> sortedLists) {

		// determine combined size for k lists
		int combinedSize = 0;
		
		for(int [] l : sortedLists){
			combinedSize += l.length;
		}
		
		// create new array of length combinedSize
		int[] combinedList = new int[combinedSize];
		
		// now loop through each list and add to new array
		int count = 0;
		for(int [] l : sortedLists){
			for(int i=0; i < l.length; i++){
				combinedList[count] = l[i];
				count++;
			}
		}

		// call Arrays.sort on combined list. Arrays.sort (O n log(n)) 
		Arrays.sort(combinedList);
		return combinedList;
	}
}
