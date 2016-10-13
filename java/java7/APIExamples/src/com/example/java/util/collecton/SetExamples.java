package com.example.java.util.collecton;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.print.attribute.standard.JobStateReasons;


public class SetExamples {

	
	private void showExamples() {
		
		/*
		 * Custom Set - extends AbstractSet and uses a custom Iterator.  
		 *   - Set Interface - A collection that contains no duplicate elements. More formally, sets contain no pair of elements e1 and e2
		 *     such that e1.equals(e2), and at most one null element. As implied by its name, this interface models the mathematical set abstraction. 
		 */
		Set<String> set = new CustomSet<>();
		set.add("A");
		
		/*
		 * HashSet - This class implements the Set interface, backed by a hash table (actually a HashMap instance). It makes no guarantees as to the 
		 * 			 iteration order of the set; in particular, it does not guarantee that the order will remain constant over time. This class permits 
		 * 			 the null element. This class offers constant time performance for the basic operations (add, remove, contains and size), assuming 
		 * 			 the hash function disperses the elements properly among the buckets. 
		 */
		set = new HashSet<>();
		set.add("B");
		set.add(null); // allows up to 1 null element
		
		/*
		 * LinkedHashSet - Hash table and linked list implementation of the Set interface, with predictable iteration order. This implementation differs 
		 * 				   from HashSet in that it maintains a doubly-linked list running through all of its entries. This linked list defines the 
		 * 				   iteration ordering, which is the order in which elements were inserted into the set (insertion-order).
		 * 				   This class provides all of the optional Set operations, and permits null elements. Like HashSet, it provides constant-time 
		 * 				   performance for the basic operations (add, contains and remove), assuming the hash function disperses elements properly among 
		 * 				   the buckets. Performance is likely to be just slightly below that of HashSet, due to the added expense of maintaining the linked 
		 * 				   list, with one exception: Iteration over a LinkedHashSet requires time proportional to the size of the set, regardless of its 
		 * 				   capacity. Iteration over a HashSet is likely to be more expensive, requiring time proportional to its capacity. 
		 */
		set = new LinkedHashSet<>();
		
		/*
		 * TreeSet - A NavigableSet implementation based on a TreeMap. The elements are ordered using their natural ordering, or by a Comparator provided at 
		 * 			 set creation time, depending on which constructor is used. This implementation provides guaranteed log(n) time cost for the basic operations 
		 * 			 (add, remove and contains). 
		 */
		set = new TreeSet<>();
		
		/*
		 * ConcurrentSkipListSet - A scalable concurrent NavigableSet implementation based on a ConcurrentSkipListMap. The elements of the set are kept sorted 
		 * 						   according to their natural ordering, or by a Comparator provided at set creation time, depending on which constructor is used.
								   This implementation provides expected average log(n) time cost for the contains, add, and remove operations and their variants. 
								   Insertion, removal, and access operations safely execute concurrently by multiple threads. Iterators are weakly consistent, 
								   returning elements reflecting the state of the set at some point at or since the creation of the iterator. They do not throw 
								   ConcurrentModificationException, and may proceed concurrently with other operations. Ascending ordered views and their iterators 
								   are faster than descending ones. 
		 */
		set = new ConcurrentSkipListSet<>();
		
		/*
		 * CopyOnWriteArraySet - A Set that uses an internal CopyOnWriteArrayList for all of its operations. 
		 * 							Thus, it shares the same basic properties:
										-It is best suited for applications in which set sizes generally stay small, read-only operations vastly outnumber mutative 
										operations, and you need to prevent interference among threads during traversal.
    									-It is thread-safe.
    									-Mutative operations (add, set, remove, etc.) are expensive since they usually entail copying the entire underlying array.
    									-Iterators do not support the mutative remove operation.
    									-Traversal via iterators is fast and cannot encounter interference from other threads. Iterators rely on unchanging snapshots 
    									 of the array at the time the iterators were constructed. 
		 */
		set = new CopyOnWriteArraySet<>();
		
		/*
		 * EnumSet - A specialized Set implementation for use with enum types. All of the elements in an enum set must come from a single enum type that is specified, 
		 * 			 explicitly or implicitly, when the set is created. Enum sets are represented internally as bit vectors. This representation is extremely compact 
		 * 			 and efficient. The space and time performance of this class should be good enough to allow its use as a high-quality, typesafe alternative to 
		 * 			 traditional int-based "bit flags." Even bulk operations (such as containsAll and retainAll) should run very quickly if their argument is also an enum set.
		 */
		Set enumSet = EnumSet.noneOf(MyEnum.class);
		
		/*
		 * JobStateReasons - Class JobStateReasons is a printing attribute class, a set of enumeration values, that provides additional information about the job's current
		 * 					 state, i.e., information that augments the value of the job's JobState attribute. Instances of JobStateReason do not appear in a Print Job's 
		 * 					 attribute set directly. Rather, a JobStateReasons attribute appears in the Print Job's attribute set. The JobStateReasons attribute contains 
		 * 					 zero, one, or more than one JobStateReason objects which pertain to the Print Job's status. The printer adds a JobStateReason object to the 
		 * 					 Print Job's JobStateReasons attribute when the corresponding condition becomes true of the Print Job, and the printer removes the JobStateReason 
		 * 					 object again when the corresponding condition becomes false, regardless of whether the Print Job's overall JobState also changed.
		 * 					 - Class JobStateReasons inherits its implementation from class java.util.HashSet. Unlike most printing attributes which are immutable once 
		 * 					   constructed, class JobStateReasons is designed to be mutable; you can add JobStateReason objects to an existing JobStateReasons object 
		 * 					   and remove them again. However, like class java.util.HashSet, class JobStateReasons is not multiple thread safe. If a JobStateReasons object 
		 * 				      will be used by multiple threads, be sure to synchronize its operations (e.g., using a synchronized set view obtained from class java.util.Collections).
		 */
		Set jsr = new JobStateReasons();
	}
	

	public static void main(String args[]){
		
		new SetExamples().showExamples();
		
	}
	
	enum MyEnum{
		VAL1,
		VAL2
	}

	class CustomSet<T> extends AbstractSet<T>{

		private List arr = new ArrayList();
		private CustomIterator iterator;
		private int nullCount;
		
		public CustomSet(){
		
			iterator = new CustomIterator();
			nullCount = 0;
		}
		
		@Override
		public boolean add(Object e){
			if(e == null && nullCount > 0)
				throw new NullPointerException();
			
			if(!arr.contains(e)){
				arr.add(e);
				return true;
			}
			
			return false;
		}
		
		@Override
		public Iterator iterator() {
			return iterator;
		}

		@Override
		public int size() {
			return arr.size();
		}
		
		class CustomIterator implements Iterator{

			int index;
			int nextCount;
			int removeCount;
			
			public CustomIterator(){
				index = nextCount = removeCount = 0;
			}
			
			@Override
			public boolean hasNext() {
				return index < arr.size();
			}

			@Override
			public Object next() {
				if(index < arr.size()){
					index ++;
					nextCount++;
					return arr.get(index);
				}else
					throw new NoSuchElementException("");
				
			}

			@Override
			public void remove() {
				if(removeCount < nextCount){
					removeCount++;
					arr.remove(index);
				}
			}
			
		}
	}

	
	
}
