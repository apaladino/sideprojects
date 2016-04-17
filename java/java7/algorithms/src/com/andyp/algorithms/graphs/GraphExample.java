package com.andyp.algorithms.graphs;

import java.util.Arrays;

public class GraphExample {

	public GraphExample() {
		super();
	}

	public Node buildGraph() {
		Node root = new Node("A");
		Node c = new Node("C");
		Node g = new Node("G");
		Node f = new Node("F");
		f.getChildren().add(new Node("M"));
		c.getChildren().addAll(Arrays.asList(g,f));
		Node e = new Node("E");
		Node k = new Node("K");
		Node j = new Node("J");
		Node l = new Node("L");
		Node p = new Node("P");
		Node q = new Node("Q");
		j.getChildren().addAll(Arrays.asList(p,q));
		e.getChildren().addAll(Arrays.asList(k,j,l));
		root.getChildren().addAll(Arrays.asList(c,e));
		return root;
	}

}