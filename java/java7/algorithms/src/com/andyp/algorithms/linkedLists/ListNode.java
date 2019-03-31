package com.andyp.algorithms.linkedLists;

/**
 * Created by andy on 5/1/17.
 */
public class ListNode {
    int data;
    ListNode next;

    public ListNode(int data){
        this.data = data;
    }

    public ListNode(int data, ListNode n){
        this.data = data;
        this.next = n;
    }
}
