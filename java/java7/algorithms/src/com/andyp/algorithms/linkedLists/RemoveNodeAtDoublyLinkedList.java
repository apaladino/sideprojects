package com.andyp.algorithms.linkedLists;

/**
 * Given a doubly-linked list, write a method to delete the node at a given position
 * (starting from 1 as the head position) and return the modified list's head. Do nothing if the input position is out of range.
 Examples:
 1<=>2<=>3<=>4, pos=6 ==> 1<=>2<=>3<=>4
 1<=>2<=>3<=>4, pos=3 ==> 1<=>2<=>4
 */
public class RemoveNodeAtDoublyLinkedList {

    public static void main(String[] args){
        new RemoveNodeAtDoublyLinkedList().run();
    }

    private void run(){
        DoublyLinkedNode four = new DoublyLinkedNode(4);
        DoublyLinkedNode three = new DoublyLinkedNode(3, null, four);
        four.prev = three;
        DoublyLinkedNode two = new DoublyLinkedNode(2, null, three);
        three.prev = two;
        DoublyLinkedNode one = new DoublyLinkedNode(1, null, two);
        two.prev = one;

        DoublyLinkedNode result = removeNodeFromPos(one, 5);
        System.out.println(result);
    }

    private DoublyLinkedNode removeNodeFromPos(DoublyLinkedNode head, int pos) {
        if(head == null || pos < 1) return head;

        int count = 1;
        DoublyLinkedNode current = head;
        DoublyLinkedNode next = current.next;
        DoublyLinkedNode prev = current.prev;

        if(pos == 1){

            if(prev != null)
                prev.next = next;

            if(next != null)
                next.prev = prev;

            return next;
        }

        while(count < pos){
            current = next;
            count++;

            if((current == head || current == null)
                    && count <= pos) // pos > length of list
                return head;

            next = current.next;
            prev = current.prev;

            if(count == pos){
                // remove node
                if(prev != null)
                    prev.next = next;

                if(next != null)
                    next.prev = prev;

                return head;
            }



        }

        return head;
    }
}
