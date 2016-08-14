package com.apaladino.peoplesearch.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apaladino.peoplesearch.domain.Person;
import com.apaladino.peoplesearch.service.PersonService;

/**
 * Main controller for Person web requests.
 * 
 * @author Andy.Paladino
 * @version 8/14/2016
 */
@RestController
@Component
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	private final int MAX_RESULTS = 100;
	
	@RequestMapping(value = "/people", method = GET)
    public List<Person> findPeople(@RequestParam("sortField") String sortField, @RequestParam("ascending") Boolean ascending,
            HttpServletResponse response){

		
		List<Person> people = personService.findPeople(0, MAX_RESULTS, sortField, ascending);
		return people;
	}
}
