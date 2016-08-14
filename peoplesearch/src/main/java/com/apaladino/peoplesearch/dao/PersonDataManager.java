package com.apaladino.peoplesearch.dao;

import java.util.List;

import com.apaladino.peoplesearch.domain.Person;

/**
 * Data manager for the Person entity.
 * 
 * @author Andy.Paladino
 * @version 8/14/2016
 *
 */
public interface PersonDataManager {

	Person findPersonById(Long personId);

	Long createPerson(Person person);

	List<Person> findAllPeople(int startIndex, int maxResults);

}
