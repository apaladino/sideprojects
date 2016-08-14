package com.apaladino.peoplesearch.service;

import java.util.List;

import com.apaladino.peoplesearch.domain.Person;

public interface PersonService {

	
	/**
	 * Method to look up a list of Persons.  It will incorporate paging to prevent any undo strain on the DB server
	 * 
	 * @param startIndex - result set index to start with
	 * @param maxResults - result set index to end on
	 * @param sortField - Field to sort on.
	 * @param ascending - sort method.
	 * 
	 * @returns a list of sorted Person objects.
	 */
	List<Person> findPeople(int startIndex, int maxResults, String sortField, Boolean ascending);

}
