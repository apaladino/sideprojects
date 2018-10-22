package com.andyp.algorithms.linkedLists;

/**
 * Created by andy on 3/28/17.
 */
public class FindNthNodeFromEndOfLinkedList {

    public static void main(String[] a){
        new FindNthNodeFromEndOfLinkedList().run();
    }

    public void run(){

        ListNode six = new ListNode(6);
        ListNode five = new ListNode(5, six);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);

        ListNode result = findNnthFromEnd(one, 2);
        System.out.println(result);
    }

    public ListNode findNnthFromEnd(ListNode node, int n){

        if(node == null || n <= 0)
            return null;

        ListNode current = node;
        ListNode next = current.next;
        int k = 1;
        while(k < n && next != null){
            next = next.next;
            k++;
        }

        if(k < (n-1))
            return null;

        return next;
    }

    class ListNode{
        int data;
        ListNode next;

        public ListNode(int d){
            this.data = d;
        }

        public ListNode(int d, ListNode n){
            this.data = d;
            this.next = n;
        }

        public String toString(){
            String output = "[" + data + "]";

            ListNode n = next;
            while(n != null){
                output += " -> [" + n.data + "]";
                n = next.next;
            }

            return output;
        }
    }
}
