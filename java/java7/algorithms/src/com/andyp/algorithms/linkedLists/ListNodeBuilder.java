package com.andyp.algorithms.linkedLists;

/**
 * Created by andy on 5/1/17.
 */
public class ListNodeBuilder {

    ListNode root;
    ListNode last;


    public ListNodeBuilder with(int data){
        if(root == null) {
            root = new ListNode(data);
            last = root;
        }else{
            ListNode newNode = new ListNode(data);
            last.next = newNode;
            last = newNode;
        }

        return this;
    }

    public ListNode build(){
        return root;
    }
}
