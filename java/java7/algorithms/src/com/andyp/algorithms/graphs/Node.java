package com.andyp.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;


public class Node{
	private boolean visited = false;
	private String val;
	List<Node> children;
	Node parent;
	
	public Node(String v){
		this.val = v;
		children = new ArrayList<>();
	}
	
	protected boolean isVisited() {
		return visited;
	}
	protected void setVisited(boolean visited) {
		this.visited = visited;
	}
	protected String getVal() {
		return val;
	}
	protected void setVal(String val) {
		this.val = val;
	}
	protected List<Node> getChildren() {
		return children;
	}
	protected void setChildren(List<Node> children) {
		this.children = children;
	}
	public String toString(){
		return "[ " + val + " ]";
	}
	public void setParent(Node p){
		this.parent = p;
	}
	public Node getParent(){
		return this.parent;
	}
}