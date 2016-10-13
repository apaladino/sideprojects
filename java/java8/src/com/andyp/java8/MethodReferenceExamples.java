package com.andyp.java8;

import com.andyp.java8.misc.DataHelper;
import com.andyp.java8.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MethodReferenceExamples {

	public void run(){
		
		List<Person> peeps = DataHelper.genPersonList();
		
		/*
		 * Example using static method reference
		 * 
		 *    - Person's compareTo method's method reference.
		 * 
		 *    - Collections.sort expects a Comparator functional interface, but we're passing in 
		 *      a static method (Person.compareTo). This works because the sort is expecting a 
		 *      an abstract method which expects two parameters and the static method reference 
		 *      that we are using passes in a method with two parameters.
		 */
		Collections.sort(peeps, Person :: compareTo);
		peeps.forEach(p -> System.out.println(p));
		
		/*
		 * Example using instance method reference
		 */
		Collections.sort(peeps, this :: compareFirstNamesOnly);
		peeps.forEach(p -> System.out.println(p));

	}
	
	public int compareFirstNamesOnly(Person p1, Person p2){
		return p1.getFirstName().compareTo(p2.getFirstName());
	}
	
	public static void main(String args[]){
		new MethodReferenceExamples().run();
	}
}
