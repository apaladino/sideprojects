package com.andyp.algorithms.arrays;

import java.util.Arrays;

/*
 * Problem: Given two sorted integer arrays A and B, merge B into A as one sorted array.
 */
public class MergeTwoSortedArrays {

	public static void main(String args[]){
		
		new MergeTwoSortedArrays().run();
	}
	
	public void run(){
		int[] a = {1, 3, 5, 7};
		int [] b = {2, 4, 6, 8, 10, 12};
		
		int [] mergedArr = mergeArrs(a,b);
		System.out.println(Arrays.toString(mergedArr));
	}

	private int[] mergeArrs(int[] a, int[] b) {
		
		if(a== null || a.length == 0)
			return b;
		
		if(b == null || b.length == 0)
			return a;
		
		int[] c = new int[a.length + b.length];
		
		int i=0; 
		int j=0;
		int k=0;
		while(i < a.length && j < b.length){
			if(a[i] < b[j]){
				c[k] = a[i];
				i++;
				k++;
			}else{
				c[k] = b[j];
				j++;
				k++;
			}
		}
		
		// guaranteed to only go into one or zero of these two loops
		if(i < a.length-1){
			while(i < a.length){
				c[k] = a[i];
				i++;
				k++;
			}
		}
		
		if(j < b.length-1){
			while(j < b.length){
				c[k] = b[j];
				j++;
				k++;
			}
		}
		
		return c;
	}
}
