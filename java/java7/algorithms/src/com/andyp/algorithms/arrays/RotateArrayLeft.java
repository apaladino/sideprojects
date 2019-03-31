package com.andyp.algorithms.arrays;

import java.util.Arrays;

/**
 * Array: [1, 2, 3, 4, 5, 6, 7, 8, 9]
 Positions: 2

 [3, 4, 5, 6, 7, 8, 9, 1, 2]

 [7, 8, 9, 1, 2, 3, 4, 5, 6]
 Fail
 Array: [1, 2, 3, 4, 5, 6, 7, 8, 9]
 Positions: 4

 [5, 6, 7, 8, 9, 1, 2, 3, 4]

 [5, 6, 7, 8, 9, 1, 2, 3, 4]
 Pass
 Array: [1, 2, 3, 4, 5, 6, 7, 8, 9]
 Positions: 10

 [2, 3, 4, 5, 6, 7, 8, 9, 1]

 [8, 9, 1, 2, 3, 4, 5, 6, 7]
 Fail
 Array: [1, 2, 3, 4, 5, 6, 7, 8, 9]
 Positions: 14

 [6, 7, 8, 9, 1, 2, 3, 4, 5]

 [4, 5, 6, 7, 8, 9, 1, 2, 3]
 */
public class RotateArrayLeft {

    public static void main(String [] args) {

        new RotateArrayLeft().run();
    }

    private void run(){

        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 2;

        System.out.println(Arrays.toString(a));

        int [] a2 = rotateArrayLeft(a, k);

        System.out.println("final: \t" + Arrays.toString(a));
    }

    private int[] rotateArrayLeft(int[] arr, int k) {

        int len = arr.length;
        if(k > len)
            k = k % len;

        int [] tmp = new int[len];

        for(int i=0; i < len; i++){
            int arrIndex = (i+k) % len;
            tmp[i] = arr[arrIndex];
        }

        return tmp;
    }
}
