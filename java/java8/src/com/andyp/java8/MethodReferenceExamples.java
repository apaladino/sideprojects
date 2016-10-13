package com.andyp.java8;

import com.andyp.java8.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MethodReferenceExamples {

	public void run(){
		
		List<Person> peeps = genPersonList();
		
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
	
	protected List<Person> genPersonList() {
		List<Person> peeps = new ArrayList<>();
		peeps.add(new Person("Sally", "Smith", "001", 23));
		peeps.add(new Person("Stanley", "Smith", "002", 25));
		peeps.add(new Person("Percy", "Pickler", "003", 33));
		peeps.add(new Person("Abigail", "Bresley", "004", 35));
		peeps.add(new Person("Donald", "Duck", "005", 19));
		return peeps;
	}
	public static void main(String args[]){
		new MethodReferenceExamples().run();
	}
}
