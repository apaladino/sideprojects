package com.andyp.algorithms.graphs;

import java.util.Stack;

/*
 * O ( V + E )
 */
public class DepthFirstSearchExample extends GraphExample{
	
	public static void main(String a[]){
		new DepthFirstSearchExample().run();
	}
	
	public void run(){
		
		Node root = buildGraph();
		
		Node result = dfs(root, new Node("M"));
		System.out.println(result);
	}

	private Node dfs(Node root, Node s) {
		
		Stack<Node> stack = new Stack<>();
		
		stack.push(root);

		// 
		while(!stack.isEmpty()){
			Node x = stack.pop();
			x.setVisited(true);
			System.out.println("Visiting " + x);
			
			if(x.getVal().equals(s.getVal()))
				return x;
			
			for(Node child : x.getChildren())
				stack.push(child);
			
		}
		return null;
	}

}
