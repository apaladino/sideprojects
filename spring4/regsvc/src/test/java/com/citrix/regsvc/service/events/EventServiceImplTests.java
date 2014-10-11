package com.citrix.regsvc.service.events;

import com.citrix.regsvc.Application;
import com.citrix.regsvc.domain.Event;
import com.citrix.regsvc.domain.Organizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * Created by apaladino on 10/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class EventServiceImplTests {

    @Autowired
    private EventService eventService;



    @Test
    public void testCreateEvent(){

        Organizer organizer = new Organizer();
        organizer.setFirstName("firstName");
        organizer.setLastName("lastName");
        organizer.setEmail("test@jedix.com");

        Long organizerId = eventService.createOrganizer(organizer);

        assertNotNull(organizerId);
        Event newEvent = new Event();
        newEvent.setOrganizerId(organizerId);
        newEvent.setTitle("new event");
        newEvent.setDescription("description ");
        newEvent.setStartDate(new Date());
        newEvent.setEndDate(new Date());

        Long eventId = eventService.createEvent(newEvent);


    }
}
