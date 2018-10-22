package com.andyp.algorithms.linkedLists;

/**
 * Created by andy on 5/4/17.
 */
public class DoublyLinkedNode {
    public int data;
    public DoublyLinkedNode prev;
    public DoublyLinkedNode next;

    public DoublyLinkedNode(int d){
        this.data = d;
    }

    public DoublyLinkedNode(int d, DoublyLinkedNode p, DoublyLinkedNode n){
        this.data = d;
        this.prev = p;
        this.next = n;
    }

    public String toString(){
        return "" + data;
    }
}
