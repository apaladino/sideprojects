package com.andyp.regsvc.controller;

/**
 * Created by apaladino on 9/28/14.
 */

import java.util.Date;

import com.andyp.regsvc.Application;
import com.andyp.regsvc.domain.Registrant;
import com.andyp.regsvc.domain.social.facebook.profile.FacebookProfile;
import com.andyp.regsvc.domain.social.linkedin.profile.LinkedInCompanyProfile;
import com.andyp.regsvc.domain.social.linkedin.profile.LinkedInProfile;
import com.andyp.regsvc.service.registrant.RegistrantService;
import com.andyp.regsvc.util.LoggingUtil;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class RegistrantControllerTests {



    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Autowired
    private RegistrantService registrantService;

    @Autowired
    private RegistrantController registrantController;



    @Value("${local.server.port}")
    int port;


    @Before
    public void setUp(){
        RestAssured.port = port;

        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testCreateRegistrant() throws Exception{

        Registrant registrant = createMockRegistrant();

        String content = LoggingUtil.toJSON(registrant);

        mockMvc.perform(post("/registrant").contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.registrantKey").value("1"));

        Registrant newRegistrant = registrantService.findRegistrantByEmail(registrant.getEmail());
        assertNotNull(newRegistrant);

    }

    @Test
    public void testGetByRegistrantId() throws Exception {

        Registrant registrant = createMockRegistrant();
        Long registrantKey = registrantService.createRegistrant(registrant);


        this.mockMvc.perform(get("/registrant/" + registrantKey).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(jsonPath("$.registrantId").value(registrantKey.intValue()))
                .andExpect(jsonPath("$.email").value(registrant.getEmail()))
                .andExpect(jsonPath("$.linkedInProfile.email").value(registrant.getEmail()))
                .andExpect(jsonPath("$.facebookProfile.email").value(registrant.getEmail()));

    }


    private Registrant createMockRegistrant() {
        Registrant registrant = new Registrant();
        registrant.setCreateTime(new Date());
        registrant.setEmail("test@jedix.com");
        registrant.setFirstName("firstName");
        registrant.setLastName("lastName");

        LinkedInProfile linkedInProfile = new LinkedInProfile();
        linkedInProfile.setEmail(registrant.getEmail());
        linkedInProfile.setFirstName(registrant.getFirstName());
        linkedInProfile.setLastName(registrant.getLastName());

        LinkedInCompanyProfile p1 = new LinkedInCompanyProfile();
        p1.setCompanySize("10-50");
        p1.setIndustry("computer");
        p1.setName("andyp");
        p1.setStartDate(new Date());
        p1.setIsCurrent(true);
        p1.setSummary("summary info");
        p1.setTitle("chief cook and bottle washer");
        p1.setType("Saas");

        LinkedInCompanyProfile p2 = new LinkedInCompanyProfile();
        p2.setCompanySize("10-50");
        p2.setIndustry("computer");
        p2.setName("Yahoo");
        p2.setStartDate(new Date());
        p2.setIsCurrent(true);
        p2.setSummary("summary info");
        p2.setTitle("chief cook and bottle washer");
        p2.setType("Saas");


        linkedInProfile.getPositions().add(p1);
        linkedInProfile.getPositions().add(p2);
        registrant.setLinkedInProfile(linkedInProfile);

        FacebookProfile facebookProfile = new FacebookProfile();
        facebookProfile.setEmail(registrant.getEmail());
        facebookProfile.setFirstName(registrant.getFirstName());
        facebookProfile.setLastName(registrant.getLastName());
        facebookProfile.setAgeRange("10-40");
        facebookProfile.setCreateTime(new Date());
        facebookProfile.setFbLink("fbLink");
        facebookProfile.setLocale("en_US");
        facebookProfile.setPictureUrl("http://someaddress.com");
        facebookProfile.setTimezone("Los_Angeles/Pacific");
        registrant.setFacebookProfile(facebookProfile);

        return registrant;
    }

}

/*
    Links:

    http://www.jayway.com/2014/07/04/integration-testing-a-spring-boot-application/
    https://code.google.com/p/rest-assured/
 */