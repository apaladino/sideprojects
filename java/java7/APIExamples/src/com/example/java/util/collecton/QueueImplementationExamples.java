package com.example.java.util.collecton;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import com.sun.jmx.remote.internal.ArrayQueue;

/**
 * Examples of implementations of the Queue interface. 
 */
public class QueueImplementationExamples {

	public static void main(String args[]){
		
		/* ########
		 * 
		 *  Queue implementations
		 *  
		 * ######## */
		/*
		 * Custom queue - subclasses Abstract Queue
		 */
		QueueImplementationExamples mainClass = new QueueImplementationExamples();
		Queue customQueue = mainClass.new CustomQueue(10);
		
		/*
		 * Linked List - A doubly linked list implementation of the List and Dequeue interfaces which implements all List operations and permits all elements (including null)
		 * 				- Not Synchronized
		 * 				- Implements the List, Queue, and Dequeue interfaces
		 */
		Queue<String> myList = new LinkedList<String>();
		myList.add("A");  // append to end of list
		
		/*
		 * PriorityQueue - An unbounded priority queue based on a priority heap. The elements of the priority queue are ordered according to their natural ordering, 
		 * or by a Comparator provided at queue construction time, depending on which constructor is used. A priority queue does not permit null elements. 
		 * A priority queue relying on natural ordering also does not permit insertion of non-comparable objects (doing so may result in ClassCastException).
		 *				  - Not Synchronized
		 *				  - The head of this queue is the least element with respect to the specified ordering. If multiple elements are tied for 
		 *					least value, the head is one of those elements
		 */
		Queue<String> pQueue = new PriorityQueue<>();
		
		/*
		 * PriorityBlockingQueue - An unbounded blocking queue that uses the same ordering rules as class PriorityQueue and supplies blocking retrieval operations. 
		 * 							While this queue is logically unbounded, attempted additions may fail due to resource exhaustion (causing OutOfMemoryError). 
		 * 							This class does not permit null elements. A priority queue relying on natural ordering also does not permit insertion of non-comparable
		 * 						    objects (doing so results in ClassCastException).
		 */
		Queue<String> pbQueue = new PriorityBlockingQueue<>();
		
		/*
		 * SyncrhonousQueue - A blocking queue in which each insert operation must wait for a corresponding remove operation by another thread, and vice versa.
		 * 						- A synchronous queue does not have any internal capacity, not even a capacity of one. You cannot peek at a synchronous queue 
		 * 						  because an element is only present when you try to remove it; you cannot insert an element (using any method) unless another 
		 * 						  thread is trying to remove it; you cannot iterate as there is nothing to iterate. The head of the queue is the element that 
		 * 						  the first queued inserting thread is trying to add to the queue; if there is no such queued thread then no element is available
		 * 						  for removal and poll() will return null. For purposes of other Collection methods (for example contains), a SynchronousQueue 
		 * 						  acts as an empty collection. This queue does not permit null elements.
		 */
		Queue<String> syncQueue = new SynchronousQueue<>(true);
		syncQueue.offer("B");
		
		/*
		 * ArrayBlockingQueue - A bounded blocking queue backed by an array. This queue orders elements FIFO (first-in-first-out). The head of the queue is that 
		 * 						element that has been on the queue the longest time. The tail of the queue is that element that has been on the queue the shortest 
		 * 						time. New elements are inserted at the tail of the queue, and the queue retrieval operations obtain elements at the head of the queue.
		 */
		Queue<String> abQueue = new ArrayBlockingQueue<>(10);
		
		/*
		 * ConcurrentLinkedQueue - An unbounded thread-safe queue based on linked nodes. This queue orders elements FIFO (first-in-first-out). The head of the queue 
		 * 						  	is that element that has been on the queue the longest time. The tail of the queue is that element that has been on the queue the 
		 * 							shortest time. New elements are inserted at the tail of the queue, and the queue retrieval operations obtain elements at the head 
		 * 							of the queue. A ConcurrentLinkedQueue is an appropriate choice when many threads will share access to a common collection. Like 
		 * 							most other concurrent collection implementations, this class does not permit the use of null elements
		 */
		Queue<String> clQueue = new ConcurrentLinkedQueue<>();
		
		/*
		 * DelayQueue - An unbounded blocking queue of Delayed elements, in which an element can only be taken when its delay has expired. The head of the queue is 
		 * 				that Delayed element whose delay expired furthest in the past. If no delay has expired there is no head and poll will return null. Expiration
		 * 				occurs when an element's getDelay(TimeUnit.NANOSECONDS) method returns a value less than or equal to zero.
		 */
		Queue<CustomDelay> delayQueue = new DelayQueue<>();
		
		/*
		 * LinkedBlockingQueue - An optionally-bounded blocking queue based on linked nodes. This queue orders elements FIFO (first-in-first-out). The head of the 
		 * 						 queue is that element that has been on the queue the longest time. The tail of the queue is that element that has been on the queue 
		 * 						 the shortest time. 
		 */
		Queue<String> lbQueue = new LinkedBlockingQueue<>();
		
		/*
		 * LinkedTransferQueue - An unbounded TransferQueue based on linked nodes. This queue orders elements FIFO (first-in-first-out) with respect to any given 
		 * 						 producer. The head of the queue is that element that has been on the queue the longest time for some producer. The tail of the 
		 * queue is that element that has been on the queue the shortest time for some producer.
		 */
		Queue<String> ltQueue = new LinkedTransferQueue<>();
		
		
		/*
		 * ArrayDeQueue - Resizable-array implementation of the Deque interface. Array deques have no capacity restrictions; they grow as necessary to support usage. 
		 * 				  They are not thread-safe; in the absence of external synchronization, they do not support concurrent access by multiple threads. Null 
		 * 				  elements are prohibited. This class is likely to be faster than Stack when used as a stack, and faster than LinkedList when used as a queue. 
		 */
		Queue<String> arrDeQueue = new ArrayDeque<>();
		
		
		
	}
	
	class CustomDelay implements Delayed{

		@Override
		public int compareTo(Delayed arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getDelay(TimeUnit arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	class CustomQueue extends AbstractQueue{

		private Object[] queue;
		private int maxSize = 100;
		private int head = 0;
		private int tail = 0;
		private int size = 0;
		private Iterator iterator;
		
		public CustomQueue(int maxSize){
			super();
			
			assert maxSize > 0;
			queue = new Object[maxSize];
			iterator  = new CustomIterator();
		}
		
		/**
		 * The offer method inserts an element if possible, otherwise returning false. This differs from the Collection.add method, 
		 * which can fail to add an element only by throwing an unchecked exception. The offer method is designed for use when failure is a normal,
		 *  rather than exceptional occurrence, for example, in fixed-capacity (or "bounded") queues.
		 */
		@Override
		public boolean offer(Object e) {

			try{
				assert size + 1 < maxSize;
				
				int newTail = (tail + 1 >= maxSize) ? ((tail +1) % maxSize) : tail + 1;
				
				if(newTail == head)
					return false; // queue is full
				
				tail = newTail;
				queue[tail] = e;
				size++;
				return true;
				
			}catch(Throwable t){
				t.printStackTrace();
			}
			return false;
		}

		@Override
		public Object peek() {
			if(size == 0)
				return null;
			
			return queue[head];
		}

		@Override
		public Object poll() {
			if(size == 0)
				return null;
			
			Object o = queue[head];
			int newHead = (head + 1 >= maxSize) ? (head+1)%maxSize : head + 1;
			size--;
			head = newHead;
			return 0;
		}

		@Override
		public Iterator iterator() {
			return iterator;
		}

		@Override
		public int size() {
			return size;
		}
		
		class CustomIterator implements Iterator{

			@Override
			public boolean hasNext() {
				return size < maxSize;
			}

			@Override
			public Object next() {
				if(size == 0 || head == tail)
					throw new NoSuchElementException("No Element");
				
				// TBD
				return null;
				
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		}

	}
	
	
	
	class Widget{
		private String name;
		
		public Widget(String n){
			this.name = n;
		}
		
		public String toString(){
			return name;
		}
	}
}
