package com.citrix.regsvc.controller;

/**
 * Created by apaladino on 9/28/14.
 */

import com.citrix.regsvc.Application;
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

import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class RegistrantControllerTests {



    private MockMvc mockMvc;


    @Autowired
    private RegistrantController registrantController;

    @Value("${local.server.port}")
    int port;


    @Before
    public void setUp(){

        RestAssured.port = port;
    }

    @Test
    public void testStuff() throws Exception {

        Long registrantId = 1L;
        when().
                get("/registrant/{id}", registrantId).
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