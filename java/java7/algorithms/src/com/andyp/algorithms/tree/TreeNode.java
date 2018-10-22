package com.andyp.algorithms.tree;

/**
 * Created by andy on 5/2/17.
 */
public class TreeNode {

    public int data;
    public TreeNode left,
                     right;

    public TreeNode(int data){
        this.data = data;
    }

    public TreeNode(int data, TreeNode l, TreeNode r){
        this.data  = data;
        this.left = l;
        this.right = r;
    }

    public String toString(){
        return "" + data;
    }
}
