package com.andyp.algorithms.sorting;

public class HeapSort {

	public static void main(String args[]){
		new HeapSort().run();
	}
	
	public void run(){
	
		// initial array
		int [] a = { 16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
		
		Heap heap = new Heap(a, 0, a.length);
		
	}
	
	private void buildHeap(Heap heap){
	
		heap.heapSize = heap.length;
		
		int mid = Double.valueOf(Math.floor(heap.length/2)).intValue();
		
		for(int i = mid; i > 0; i--){
			maxHeapify(heap, i);
		}
	}
	
	private void maxHeapify(Heap heap, int i){
		
		int l = left(i);
		int r = right(i);
		int largest = -1;
		
		if(l <= heap.heapSize && heap.a[l] > heap.a[i])
			largest = l;
		else
			largest = r;
		
		if( r <= heap.heapSize && heap.a[r] > heap.a[largest])
			largest = r;
		
		if(largest != i){
			// swap a[i] with a[largest]
			int tmp = heap.a[i];
			heap.a[i] = heap.a[largest];
			heap.a[largest] = tmp;
			
			maxHeapify(heap, largest);
		}
	}
	
	private int parent(int i){
		return Double.valueOf(Math.floor(i/2)).intValue();
	}
	
	private int left(int i){
		return 2*i;
	}
	
	private int right(int i){
		return (2 * i) +  1;
	}
	
	class Heap{
		public int [] a;
		public int heapSize;
		public int length;
		
		public Heap(int [] arr, int heapSize, int length){
			this.a = arr;
			this.heapSize = heapSize;  // number of elements in heap
			this.length = length;		// number of elements in array
		}
	}
}

/*
 

*/
