package com.andyp.algorithms.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * Example of breadth first search algorithm.
 * 
 */
public class BreadthFirstSearchExample extends GraphExample {

	public static void main(String a[]){
		new BreadthFirstSearchExample().run();
	}
	
	public void run(){
		
		Node root = buildGraph();
		
		Node val = bfs(root, new Node("Q"));
		System.out.println(val.getVal());
		
		// find shortest path
		Stack<Node> s = new Stack<>();
		Node x = val;
		while(x != null){
			s.push(x);
			x = x.getParent();
		}
		
		System.out.print("Shortest path: [ ");
		int count = 0;
		while(!s.isEmpty()){
			if(count > 0)
				System.out.print(", ");
			
			x = s.pop();
			System.out.print(x.getVal());
			count++;
		}
		
		System.out.println(" ]");
			
	}

	private Node bfs(Node root, Node s) {
		
		if(root == null || s == null)
			return null;
		
		// place root node onto queue
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		while(!queue.isEmpty()){
			Node x = queue.remove();
			System.out.println("Visiting node: " + x.getVal());
			x.setVisited(true);
			if(x.getVal().equals(s.getVal()))
				return x;
			
			// put all of x's children onto the queue
			for(Node child : x.getChildren()){
				if(!child.isVisited()){
					child.setParent(x);
					queue.add(child);
				}
			}
			
		}
		
		return null;
	}
	
}
