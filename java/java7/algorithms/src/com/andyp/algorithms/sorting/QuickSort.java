package com.andyp.algorithms.sorting;

import java.util.Arrays;

public class QuickSort {
	
	public static void main(String a[]){
		
		new QuickSort().run();
	}

	public void run(){
		
		int [] a = {17, 23, 4, 8, 2, 13, 44, 5};
		
		System.out.println(Arrays.toString(a));
		
		quickSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
	}
	
	public void quickSort(int [] a, int low, int high){
		
		if(a == null || a.length == 0 || low > high)
			return;
		
		printArray(a, low, high);
		// select a pivot to be the value of the middle of the array
		int middle = low + (high-low)/2;
		int pivot = a[middle];
		int i=low;
		int j=high;
		
		// starting at both ends of the array, make a[i] < pivot and a[j] > pivot
		while(i<=j){
			
			while(a[i] < pivot)
				i++;
			
			while(a[j] > pivot)
				j--;
			
			if(i <= j){
				swap(a, i, j);
				i++;
				j--;
			}
		}
		
		if(low < j)
			quickSort(a, low, j);
		
		if(high > i)
			quickSort(a, i, high);
	}

	

	private void printArray(int[] a, int low, int high) {
		System.out.print("A=[");
		
		for(int i=low; i < high; i++){
			if(i > low)
				System.out.print(", ");
			
			System.out.print(a[i]);
		}
		
		System.out.print("] \n");
	}

	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
