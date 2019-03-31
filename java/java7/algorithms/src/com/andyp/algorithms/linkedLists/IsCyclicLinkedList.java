package com.andyp.algorithms.linkedLists;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andy on 3/26/17.
 */
public class IsCyclicLinkedList {


    public void run(){



        ListNode four = new ListNode(4);
        ListNode three = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode one = new ListNode(1);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = new ListNode(2);

        System.out.println(isCyclic(one));
    }

    public boolean isCyclic(ListNode root){

        if(root == null)
            return false;

        Set<Integer> s = new HashSet<>();
        boolean foundDups = false;

        s.add(Integer.valueOf(root.data));
        ListNode current = root;
        while(current.next != null){
            current = current.next;
            if(s.contains(Integer.valueOf(current.data))){
                foundDups = true;
                break;
            }
            s.add(Integer.valueOf(current.data));
        }

        return foundDups;
    }

    public static void main(String[] a){
        new IsCyclicLinkedList().run();
    }

    class ListNode{
        int data;
        ListNode next;

        public ListNode(int d){
            this.data = d;
        }
    }

}
