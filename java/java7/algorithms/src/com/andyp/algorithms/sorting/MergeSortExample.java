package com.andyp.algorithms.sorting;

import java.util.Arrays;

public class MergeSortExample {

	public static void main(String[] args){
		
		new MergeSortExample().run();
		
	}
	
	public void run(){
		Integer [] arr = new Integer[] {15, 2, 7, 11, 9, 7, 4, 1, 5};
		
		mergeSort(arr, 0, arr.length);
		
		System.out.println("Final: " + Arrays.toString(arr));
	}
	
	public void mergeSort(Integer[] a, int p, int r){
	
		if(p < r){
			int q = p+r / 2;
			mergeSort(a, p, q);
			mergeSort(a, q+1, r);
			merge(a, p, q, r);
		}
	}
	
	public void merge(Integer[] a, int p, int q, int r){
		System.out.println("merge: " + Arrays.toString(a) + ", p: " + p + ", q:" + q + ", r: " + r);
		
		int n1 = q-p +1;
		int n2 = r-q;
		Integer[] larr = new Integer[n1];
		Integer[] rarr = new Integer[n2];
		
		for(int i=0; i < n1; i++){
			larr[i] = a[p+i];
		}
		
		for(int j=0; j < n2; j++){
			rarr[j] = a[q+1+j];
		}
		
		System.out.println("larr: " + Arrays.toString(larr) + ", rarr: " + Arrays.toString(rarr) );
		
		int i = 0,
			j = 0,
			k = p;
		
		while(i < n1 && j < n2){
			if(larr[i] < rarr[j]){
				a[k] = larr[i];
				i++;
			}else{
				a[k] = rarr[j];
				j++;
			}
			k++;
		}
		
		while (i < n1)
	    {
	        a[k] = larr[i];
	        i++;
	        k++;
	    }
	    
	    while (j < n2)
	    {
	        a[k] = rarr[j];
	        j++;
	        k++;
	    }
		
	}
	
		/*
		 * MERGE.A; p; q; r/
1 n1 D q  p C 1
2 n2 D r  q
3 letLOE1: : n1 C 1 and ROE1 : : n2 C 1 be new arrays
4 for i D 1 to n1
5 LOEi D AOEp C i  1
6 for j D 1 to n2
7 ROEj  D AOEq C j 
8 LOEn1 C 1 D 1
9 ROEn2 C 1 D 1
10 i D 1
11 j D 1
12 for k D p to r
13 if LOEi  ROEj 
14 AOEk D LOEi
15 i D i C 1
16 else AOEk D ROEj 
17 j D j C 1
		 */
	
	
	
}
