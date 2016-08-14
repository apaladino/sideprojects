package com.apaladino.peoplesearch.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apaladino.peoplesearch.dao.PersonDataManager;
import com.apaladino.peoplesearch.domain.Person;
import com.apaladino.peoplesearch.util.PersonComparator;
import com.apaladino.peoplesearch.util.PersonIterable;

/**
 * Main service for Person entity 
 * 
 * @author Andy.Paladino
 * @version 8/14/2016
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDataManager personDataManager;
	
	public List<Person> findPeople(int startIndex, int maxResults, String sortField, Boolean ascending) {
		
		if(sortField == null)
			sortField = "lastName";
		
		if(ascending == null)
			ascending = Boolean.TRUE;
		
		List<Person> people = personDataManager.findAllPeople(startIndex, maxResults);
		
		
		/*
		 * Sort the list of people.
		 * 
		 * NOTE:  because the requirement is to use the static sort method which takes an Iterable parameter, I 
		 *        will create a new Iterable object and pass in the fully populated list of people.  
		 *        
		 *        ** I would not use an iterable in this case since i already have a populated collection
		 *           returned by the DataManager.
		 */
		PersonIterable<Person> personIterable = new PersonIterable(people);
		
		List<Person> sortedList = sort(personIterable, sortField, ascending);
		
		return sortedList;
	}

	/**
	 * Sorts a list of Person entities.
	 * 
	 * @param people
	 * @param sortField
	 * @param ascending
	 * @return
	 * 
	 * ** NOTE: I know that using the Iterable object is a requirement, but I wouldn't use this
	 *          since the DataManager will return a valid Collection. You'll notice that I just
	 *          call the Iterable's getPeople method to get access to the fully populated list 
	 *          of people, rather than using the Iterable in it's usual context to build a list.
	 *          
	 * ** NOTE: I know that the spec required ascending to be a String for the ascending parameter, 
	 *          but it makes better sense to just use a boolean. 
	 */
	static List<Person> sort(Iterable<Person> iterable, String sortField, boolean ascending){  
		
		
		List<Person> people = ((PersonIterable)iterable).getPeople();
		
		Collections.sort(people, new PersonComparator(sortField, ascending));
		
		return people;
	}

}
