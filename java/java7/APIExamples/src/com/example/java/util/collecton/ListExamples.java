package com.example.java.util.collecton;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.relation.Role;
import javax.management.relation.RoleList;
import javax.management.relation.RoleUnresolved;
import javax.management.relation.RoleUnresolvedList;

public class ListExamples {

	public static void main(String [] args){
		new ListExamples().showExamples();
	}
	
	public void showExamples(){
		
		/*
		 * CustomList extends AbstractList
		 */
		List list = new CustomList();
		list.add("test");
		
		/*
		 * ArrayList - 	Resizable-array implementation of the List interface. Implements all optional list operations, 
		 * 			   	and permits all elements, including null. In addition to implementing the List interface, this 
		 * 				class provides methods to manipulate the size of the array that is used internally to store the list. 
		 * 				(This class is roughly equivalent to Vector, except that it is unsynchronized.) 
		 */
		list = new ArrayList();
		list.add(Integer.valueOf(100));
		list.add(null);
		
		/*
		 * AttributeList = 	Represents a list of values for attributes of an MBean. See the getAttributes and setAttributes
		 * 					methods of MBeanServer and MBeanServerConnection.
		 */
		list = new AttributeList();
		list.add(new Attribute("name", "Sam"));
		
		/*
		 * CopyOnWriteArrayList - A thread-safe variant of ArrayList in which all mutative operations (add, set, and so on) 
		 * 							are implemented by making a fresh copy of the underlying array.
		 * 							- thread safe and synchronized, but slower than ArrayList
		 */
		List<String> copyList = new CopyOnWriteArrayList<>();
		copyList.add("test1");
		copyList.add("test2");
		
		/*
		 * LinkedList - Doubly-linked list implementation of the List and Deque interfaces. Implements all optional list 
		 * 				operations, and permits all elements (including null).
		 *   			All of the operations perform as could be expected for a doubly-linked list. Operations that index 
		 *   			into the list will traverse the list from the beginning or the end, whichever is closer to the specified index. 
		 */
		list = new LinkedList();
		list.add("test");
		
		/*
		 * RoleList - A RoleList represents a list of roles (Role objects). It is used as parameter when creating a relation, and when trying 
		 * 			  to set several roles in a relation (via 'setRoles()' method). It is returned as part of a RoleResult, to provide roles successfully retrieved.
		 */
		list = new RoleList();
		list.add(new Role("CAN-DO-STUFF-ROLE", Collections.EMPTY_LIST));
		
		/*
		 * RoleUnresolvedList - A RoleUnresolvedList represents a list of RoleUnresolved objects, representing roles not retrieved from a relation due 
		 * 						to a problem encountered when trying to access (read or write) the roles.
		 */
		list = new RoleUnresolvedList();
		list.add(new RoleUnresolved("CAN-DO-STUFF-ROLE", list, 0));
		
		/*
		 * Stack - The Stack class represents a last-in-first-out (LIFO) stack of objects. It extends class Vector with five operations that allow 
		 * 		   a vector to be treated as a stack. The usual push and pop operations are provided, as well as a method to peek at the top item 
		 * 		   on the stack, a method to test for whether the stack is empty, and a method to search the stack for an item and discover how far 
		 * 		   it is from the top.
		 */
		Stack stack = new Stack();
		stack.push("A");
		stack.push("B");
		System.out.println(stack.pop());
		
		/*
		 * Vector - The Vector class implements a growable array of objects. Like an array, it contains components that can be accessed using an integer index. 
		 * 			However, the size of a Vector can grow or shrink as needed to accommodate adding and removing items after the Vector has been created.
		 * 			Each vector tries to optimize storage management by maintaining a capacity and a capacityIncrement. The capacity is always at least as large 
		 * 			as the vector size; it is usually larger because as components are added to the vector, the vector's storage increases in chunks the size of 
		 * 			capacityIncrement. An application can increase the capacity of a vector before inserting a large number of components; this reduces the amount 
		 * 			of incremental reallocation.
		 */
		Vector v = new Vector();
		v.add("A");
		v.add("B");
		
		/*
		 * ListIterator - 	An iterator for lists that allows the programmer to traverse the list in either direction, modify the list during iteration, and obtain 
		 * 					the iterator's current position in the list. A ListIterator has no current element; its cursor position always lies between the element 
		 * 					that would be returned by a call to previous() and the element that would be returned by a call to next().
		 */
		ListIterator li = v.listIterator();
		while(li.hasNext()){
			System.out.println(li.next());
		}
		while(li.hasPrevious()){
			System.out.println(li.previous());
		}
	}
	
	class CustomList extends AbstractList{

		private int size;
		private int index;
		private Object[] list;
		
		public CustomList(){
			index = 0;
			size = 10;
			list = new Object[size];
		}
		
		@Override
		public boolean add(Object arg0){
			try{
				if(index >= size){
					int doubleSize = ((Integer.MAX_VALUE - size) < size) ? Integer.MAX_VALUE :  size * 2;
					Object[] newList = new Object[doubleSize];
					for(int i=0; i < size; i++){
						newList[i] = list[i];
					}
					list = newList;
				}
				
				index++;
				size++;
				list[index] = arg0;
				return true;
			}catch(Throwable t){
				return false;
			}
		}
		
		@Override
		public Object get(int index) {
			
			return list[index];
		}

		@Override
		public int size() {
			return size;
		}
		
	}
}
