package com.andyp.algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Route Between Nodes Problem: Given a directed graph, design an algorithm to find out
 * 								whether there is a route between two nodes.
 */
public class RouteBetweenNodesProblem {

	public static void main(String[]a){
		new RouteBetweenNodesProblem().run();
	}

	public void run() {
		
		Graph g = createSampleGraph();
		
		Node startNode = new Node("0");
		Node targetNode = new Node("5");
		
		boolean isDirectPath = checkIfDirectPathExists(g, startNode, targetNode);
		System.out.println("Is Direct Path [" + startNode + ", " + targetNode + "] = " + isDirectPath);
		
		g = createSampleGraph();
		targetNode = new Node("3");
		isDirectPath = checkIfDirectPathExists(g, startNode, targetNode);
		System.out.println("Is Direct Path [" + startNode + ", " + targetNode + "] = " + isDirectPath);
	}
	
	private boolean checkIfDirectPathExists(Graph g, Node startNode,
			Node targetNode) {
		
		Queue<Node> queue = new LinkedList<>();
		// look for startNode
		for(int i=0; i < g.nodes.length; i++){
			Node n = g.nodes[i];
			if(n.name.equals(startNode.name)){
				queue.add(n);
				break;
			}
		}
		
		while(!queue.isEmpty()){
			Node n = queue.remove();
			if(n.name.equals(targetNode.name))
				return true;
			
			for(int i=0; i < n.children.length; i++){
				Node c = n.children[i];
				
				if(!c.visited)
					queue.add(c);
			}
			
			n.visited = true;
		}
		
		return false;
	}

	private Graph createSampleGraph() {
		
		Node n0 = new Node("0");
		Node n1 = new Node("1");
		Node n2 = new Node("2");
		Node n3 = new Node("3");
		Node n4 = new Node("4");
		Node n5 = new Node("5");
		Node n6 = new Node("6");
		n0.children = new Node[] { n1 };
		n1.children = new Node[] { n2 };
		n2.children = new Node[] { n0, n3 };
		n3.children = new Node[] { n2 };
		n4.children = new Node[] { n6 };
		n5.children = new Node[] { n4 };
		n6.children = new Node[] { n5 };
		
		Node[] nodes = new Node[] { n0, n1, n2, n3, n4, n5, n6 };
		return new Graph(nodes);		
	}

	class Graph{
		public Node[] nodes;
		
		public Graph(Node[] nodes){
			this.nodes = nodes;
		}
	}
	
	class Node{
		public String name;
		public boolean visited;
		public Node[] children;
		
		public Node(String name, Node[] children){
			this.name = name;
			this.children = children;
			this.visited = false;
		}

		public Node(String name) {
			this.name = name;
			this.visited = false;
		}
		
		public String toString(){
			return this.name;
		}
	}
}

