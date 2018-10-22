package com.andyp.algorithms.tree.BST;

import com.andyp.algorithms.tree.TreeNode;

import java.util.*;

/**
 * Given a binary tree, write a method to perform the inorder traversal iteratively. Append the data of each node visited to an ArrayList. Return an empty Arraylist in the case of an empty tree.

 Example:
      1
     / \
    2   3     ==> 4251637
   / \ / \
  4  5 6  7


 -- failing
 [3, 4, 6, 8, 9]

 Exception : Exception in thread "Thread-0" java.lang.OutOfMemoryError: Java heap space...



 */
public class IterativeInOrderTraversalBST {

    public static void main(String[] args){
        new IterativeInOrderTraversalBST().run();
    }

    public void run(){

        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode root = buildTree();

        ArrayList<Integer> nodes = iterativeInorderTraversal(root);
        System.out.println(Arrays.toString(nodes.toArray(new Integer[nodes.size()])));
    }

    private ArrayList<Integer> iterativeInorderTraversal(TreeNode root) {
        ArrayList<Integer> values = new ArrayList<>();
        if(root == null) return values;

        Stack<TreeNode> stack = new Stack<>();

        while(true){

            if(root != null) {
                stack.push(root);
                root = root.left;
            }else{
                if(stack.isEmpty()) break;

                root = stack.pop();
                values.add(root.data);
                root = root.right;
            }
        }


        return values;
    }

    private TreeNode buildTree() {
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode three = new TreeNode(3, six, seven);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode two = new TreeNode(2, four, five);
        TreeNode one = new TreeNode(1, two, three);
        return one;
    }
}
