package com.andyp.algorithms.arrays;

import java.util.Arrays;

/*
 * Problem: Rotate an array of n elements to the right by k steps. 
 * 		For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].  
 */
public class RotateArray {
	
	public static void main(String args[]){
		new RotateArray().run();
	}

	public void run(){
		
		String [] a = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		
		int numIndexes = 3;
		
		System.out.println("Before: " + Arrays.toString(a));
		
		
		String [] a2 = rotate(a, numIndexes);
		System.out.println("After: " + Arrays.toString(a2));
	}

	private String[] rotate(String[] a, int k) {
		
		if(k > a.length)
			k = k % a.length;
		
		String [] tmp = new String[a.length];
		
		// copy from end of array - k to start of array
		for(int i=0; i < k; i++){
			tmp[i] = a[(a.length - k) + i ];
		}
		
		int j=0;
		for(int i=k; i < a.length; i++){
			tmp[i] = a[j];
			j++;
		}
		
		return tmp;
	}
}
