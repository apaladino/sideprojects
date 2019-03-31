package com.andyp.algorithms.linkedLists;

/**
 * Created by andy on 4/26/17.
 */
public class CheckForLoopInSingleLinkedList {


    public static void main(String[] args){
        new CheckForLoopInSingleLinkedList().run();
    }

    private void run() {


    }

    class Node{

        int val;
        Node next;

        public Node(int val, Node n){
            this.val = val;
            this.next = n;
        }
    }

}
