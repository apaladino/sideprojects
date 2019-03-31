package com.andyp.algorithms.linkedLists;

import java.util.Arrays;

/**
 * Created by andy on 3/26/17.
 */
public class ReverseLinkedListExample {


    public void run(){

        int matrix[][] = {{1,0,1},{1,0,1}};

        int w = matrix[0].length;
        int h = matrix.length;

        for(int row = 0; row < h; row++)
            for(int col = 0; col < w/2; col++){
                int temp = matrix[row][col];
                int maxCol = w - 1 - col;
                matrix[row][col] = matrix[row][maxCol];
                matrix[row][maxCol] = temp;
            }

        for(int i =0; i < h; i++)
            System.out.println(Arrays.toString(matrix[i]));

    }

    public void runOld(){

        ListNode svn = new ListNode(7);
        ListNode six = new ListNode(6, svn);
        ListNode five = new ListNode(5, six);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);


        ListNode result = reverseList(one);
        System.out.println(result);
    }

    public ListNode reverseList(ListNode head) {

        if(head == null || head.next == null)
            return head;

        ListNode current = head;
        ListNode next = current.next;

        current.next = null;

        if(next.next == null){
            next.next = current;
            return next;
        }

        while(next != null){
            ListNode temp = next.next;
            next.next = current;

            current = next;
            next = temp;
        }

        return current;
    }

    public static void main(String [] args){
        new ReverseLinkedListExample().run();
    }

    class ListNode{
        int data;
        ListNode next;

        public ListNode(int d, ListNode n){
            this.data = d;
            this.next = n;
        }

        public ListNode(int d){
            this.data = d;
        }

        public String toString(){
            String val =  "[" + data + "]";
            ListNode nextVal = next;

            while(nextVal != null){

                val += " -> [" + nextVal.data + "]";
                nextVal = nextVal.next;
            }

            return val;
        }
    }
}
