package com.apaladino.peoplesearch.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apaladino.peoplesearch.domain.Person;



/**
 * Data manager implementation for the Person entity
 * 
 * @author Andy.Paladino
 * @version 8/14/2016
 */
@Repository
public class PersonDataManagerImpl implements PersonDataManager {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
    public Person findPersonById(Long personId){

    	assert (personId != null) : "Missing personId parameter.";
    	
        return (Person) sessionFactory.getCurrentSession()
                .get(Person.class, personId);

    }

    @Transactional
    public Long createPerson(Person person){

        Session session = sessionFactory.getCurrentSession();

        return (Long) session.save(person);

    }

    @Transactional
	public List<Person> findAllPeople(int startIndex, int maxResults) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Person.class);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(maxResults);
		
		@SuppressWarnings("unchecked")
		List<Person> people = (List<Person>)criteria.list();
		
		return people;
	}
 	
}
