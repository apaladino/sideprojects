package com.andyp.algorithms.tree.BST;

/**
 * Created by andy on 3/6/17.
 */
public class KthLargestBinarySearchTreeProblem {

    public void run(){

        Node ten = new Node(10);
        Node svn = new Node(7);
        Node eight = new Node(8, svn, ten);
        Node three = new Node(3);
        Node two = new Node(2);
        Node four = new Node(4, two, three);
        Node five = new Node(5, four, eight);

        Node root = five;

        int k = 6;
        Node n = findKthLargest(root, k);

        System.out.println(String.format("K: %s, Node: %s", k, n));
        /*
        for(int k=7; k > 0; k--) {
            Node n = findKthLargest(root, k);

            System.out.println(String.format("K: %s, Node: %s", k, n));
        }*/


    }

    private Node findKthLargest(Node node, int k){

        int[] count = new int[1];

        Node result = findKthLargest(node, k, count);
        return result;
    }

    private Node findKthLargest(Node node, int k, int[] count) {

        if(node == null || count[0] >= k)
            return node;

        Node largestNode = null;

        largestNode = findKthLargest(node.right, k, count);

        if(largestNode == null){
            count[0] ++;

            if(count[0] == k)
                largestNode = node;
        }

        if(largestNode == null) {
            largestNode = findKthLargest(node.left, k, count);

        }

        return largestNode;
    }


    public static void main(String[] args){
        new KthLargestBinarySearchTreeProblem().run();
    }

    class CounterObj{
        int index;
        public CounterObj(int val){
            index = val;
        }

        public void increment(){
            index++;
        }
        public int getIndex(){
            return index;
        }
        public String toString(){
            return "" + index;
        }
    }

    class Node{
        int value;
        Node left;
        Node right;

        public Node(int val){
            this.value = val;
        }

        public Node(int val, Node l, Node r){
            this.value = val;
            this.left = l;
            this.right = r;
        }

        public String toString(){
            return "Node: " + value;
        }

    }
}
