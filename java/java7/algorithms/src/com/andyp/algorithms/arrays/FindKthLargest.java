package com.andyp.algorithms.arrays;

import java.util.PriorityQueue;

/**
 * Created by andy on 7/31/18.
 */
public class FindKthLargest {

    public void run(){

        int [] nums = { 110, 17, 2, 23, 18, 22, 34,  1,4 , 5};

        int result = findKthLargest2(new int[] { 1}, 1);
        System.out.println(result);

    }

    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pqueue = new PriorityQueue<>();

        for(int i=0; i < nums.length; i++){
            if(i < k){
                pqueue.add(nums[i]);
            }else{

                // if current number is > head of priority queue, then replace it
                if(nums[i] > pqueue.peek()){
                    pqueue.poll();
                    pqueue.add(nums[i]);
                }
            }
        }

        return pqueue.poll();
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i=0; i < k; i++){
            queue.add(nums[i]);
        }
        for(int i = k; i < nums.length; i++){
            if(nums[i] > queue.peek()){
                queue.poll();
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }


    public static void main(String [] args){
        new FindKthLargest().run();
    }
}
