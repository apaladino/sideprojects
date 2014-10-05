package com.citrix.regsvc.controller;

/**
 * Created by apaladino on 9/28/14.
 */

import java.util.Date;

import com.citrix.regsvc.Application;
import com.citrix.regsvc.domain.Registrant;
import com.citrix.regsvc.service.RegistrantService;
import com.jayway.restassured.RestAssured;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class RegistrantControllerTests {



    private MockMvc mockMvc;


    @Autowired
    private RegistrantService registrantService;


    @Value("${local.server.port}")
    int port;


    @Before
    public void setUp(){
        RestAssured.port = port;
    }

    @Test
    public void testCreateRegistrant() throws Exception{
        Registrant registrant = new Registrant();
        registrant.setCreateTime(new Date());
        registrant.setEmail("test@jedix.com");
        registrant.setFirstName("firstName");
        registrant.setLastName("lastName");


        given().parameters("firstName", "John", "lastName", "Doe", "email" , "test2@jedix.com")
                .when()
                .expect().statusCode(201)
                .post("/registrant")
                .then()
                .body("registrantKey", equalTo("1"));

    }

    @Test
    public void testGetByRegistrantId() throws Exception {

        Long registrantId = 1L;
        when().
                get("/registrant/{registrantId}", registrantId).
                then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.is(""));
    }
}

/*
    Links:

    http://www.jayway.com/2014/07/04/integration-testing-a-spring-boot-application/
    https://code.google.com/p/rest-assured/
 */