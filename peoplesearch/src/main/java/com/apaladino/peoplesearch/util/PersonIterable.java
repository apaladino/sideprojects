package com.apaladino.peoplesearch.util;

import java.util.Iterator;
import java.util.List;

/**
 * Main Iterable class for the Person entity.
 * 
 * @author Andy.Paladino
 * @version 8/14/2016
 */
public class PersonIterable<Person> implements Iterable<Person> {

	private List<Person> people;
	
	public PersonIterable(List<Person> people){
		this.people = people;
	}
	
	public Iterator<Person> iterator() {
		return people.iterator();
	}

	public List<Person> getPeople(){
		return this.people;
	}
}
