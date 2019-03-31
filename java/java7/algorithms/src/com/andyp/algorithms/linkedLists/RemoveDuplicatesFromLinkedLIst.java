package com.andyp.algorithms.linkedLists;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by andy on 5/1/17.
 */
public class RemoveDuplicatesFromLinkedLIst {

    public static void main(String[] args){
        new RemoveDuplicatesFromLinkedLIst().run();
    }

    public void run(){

        ListNode root = new ListNodeBuilder().with(1)
                                .with(2)
                                .with(3)
                                .with(4)
                                .with(1)
                                .build();

        ListNode filtered = removeDuplicates(root);


    }

    public ListNode removeDuplicates(ListNode head) {

        if(head == null) return head;

        ListNode current = head;
        ListNode runner = current.next;

        while(runner != null){

            if(runner.data == current.data) {

                // go through the rest of the linked list and check for dups
                ListNode next = runner;
                while(next != null && next.next != null){
                    if(next.next.data == current.data){
                        next.next = next.next.next;
                    }
                    next = next.next;
                }
            }

            runner = runner.next;
            current = current.next;
        }

        return head;


    }

}
