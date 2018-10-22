package com.andyp.algorithms.linkedLists;

/**
 * Created by andy on 4/28/17.
 */
public class RemoveLargeNodesFromLinkedList {

    public static void main(String args[]){
        new RemoveLargeNodesFromLinkedList().run();
    }

    private void run(){
        Node three1 = new Node(3,null);
        Node five1 = new Node(5, three1);
        Node four = new Node(4, five1);
        Node three = new Node(3, four);
        Node two = new Node(2, three);
        Node one = new Node(1, two);
        Node five = new Node(5,one);

        System.out.println("Start: " + five);

        Node head = removeLargeNodes(five, 3);
        System.out.println("Results: " + head);
    }

    private Node removeLargeNodes(Node node, int x) {

        if(node == null) return node;

        // find head of list
        Node head = node;
        while(head != null && head.val > x)
            head = head.next;

        if(head == null) return null;

        Node current = head;
        Node next = current.next;

        while(next != null){

            // find the next node < x
            while(next != null && next.val > x){
                next = next.next;
            }

            current.next = next;

            if(next != null) {
                current = next;
                next = next.next;
            }

        }

        return head;
    }


    class Node{
        protected int val;
        protected Node next;

        public Node(int v, Node n){
            this.val = v;
            this.next = n;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(val);

            if(next != null){
                int i = 0;
                Node c = next;

                while(c != null){
                    sb.append(" -> ");

                    sb.append(c.val);
                    c = c.next;
                    i++;
                }
            }

            return sb.toString();
        }
    }
}
