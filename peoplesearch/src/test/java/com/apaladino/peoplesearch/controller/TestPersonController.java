package com.apaladino.peoplesearch.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.apaladino.peoplesearch.Application;
import com.apaladino.peoplesearch.dao.PersonDataManager;
import com.apaladino.peoplesearch.domain.Person;
import com.apaladino.peoplesearch.service.PersonService;
import com.jayway.restassured.RestAssured;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TestPersonController {
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private SimpleDateFormat formatter;
	

    @Autowired
    private PersonService personService;
    
    @Autowired
    private PersonDataManager personDataManager;

    @Autowired
    private PersonController personController;

    @Value("${local.server.port}")
    int port;


    @Before
    public void setUp(){
        RestAssured.port = port;

        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.formatter = new SimpleDateFormat("MM-DD-YYYY");
    }

    @Test
    public void testFindPeople() throws Exception{

    	Person p1 = createPerson("005-01-1001", "bob", "brooks", 5.5D, 180.4D, "04-01-1970");
    	Person p2 = createPerson("005-01-1002", "calvin", "cobbs", 5.5D, 180.4D, "01-01-1975");
    	Person p3 = createPerson("005-01-1003", "angela", "andrews", 5.5D, 180.4D, "06-03-1973");
    	Person p4 = createPerson("005-01-1004", "frank", "fairbanks", 5.5D, 180.4D, "06-05-1973");
    	
    	String sortField = "lastName";
    	boolean ascending = true;
    	HttpServletResponse response = new MockHttpServletResponse();
    	
    	// test sort by last name ascending
		List<Person> sortedPeople = personController.findPeople(sortField, ascending, response );
		verifySortedList(sortedPeople, ascending, p1, p2, p3, p4);
		
		// test sort by last name descending
		ascending = false;
		sortedPeople = personController.findPeople(sortField, ascending, response );
		verifySortedList(sortedPeople, ascending, p1, p2, p3, p4);
		
		// test sort by first name ascending
		ascending = true;
		sortField = "firstName";
		sortedPeople = personController.findPeople(sortField, ascending, response );
		verifySortedList(sortedPeople, ascending, p1, p2, p3, p4);
		
		// test sort by first name descending
		ascending = false;
		sortedPeople = personController.findPeople(sortField, ascending, response );
		verifySortedList(sortedPeople, ascending, p1, p2, p3, p4);
			
    }

	private void verifySortedList(List<Person> sortedPeople, boolean ascending,
			Person p1, Person p2, Person p3, Person p4) {
		if(ascending){
			assertEquals("should return 4 people", 4, sortedPeople.size());
			assertEquals("incorrect sort order", p3.getLastName(), sortedPeople.get(0).getLastName());
			assertEquals("incorrect sort order", p1.getLastName(), sortedPeople.get(1).getLastName());
			assertEquals("incorrect sort order", p2.getLastName(), sortedPeople.get(2).getLastName());
			assertEquals("incorrect sort order", p4.getLastName(), sortedPeople.get(3).getLastName());
		}else{
			assertEquals("should return 4 people", 4, sortedPeople.size());
			assertEquals("incorrect sort order", p4.getLastName(), sortedPeople.get(0).getLastName());
			assertEquals("incorrect sort order", p2.getLastName(), sortedPeople.get(1).getLastName());
			assertEquals("incorrect sort order", p1.getLastName(), sortedPeople.get(2).getLastName());
			assertEquals("incorrect sort order", p3.getLastName(), sortedPeople.get(3).getLastName());
		}
	}

	private Person createPerson(String ssn, String firstName, String lastName,
			double height, double weight, String dob) throws ParseException {
		Person p = new Person();
		p.setSsn(ssn);
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setHeightIn(height);
		p.setWeightLb(weight);
		
		Date dateOfBirth = formatter.parse(dob);
		p.setDateOfBirth(dateOfBirth);
		return p;
	}
    
}
