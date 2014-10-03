package com.citrix.regsvc.controller;

/**
 * Created by apaladino on 9/28/14.
 */

import com.citrix.regsvc.config.ContextConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContextConfig.class)
public class RegistrantControllerTests {



    private MockMvc mockMvc;


    @Autowired
    private RegistrantController registrantController;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(registrantController).build();
    }

    @Test
    public void testStuff() throws Exception {

        mockMvc.perform(get("/registrant/1"));
    }
}
