package com.andyp.regsvc.service.events;

import com.andyp.regsvc.Application;
import com.andyp.regsvc.data.events.EventsDataManager;
import com.andyp.regsvc.domain.Event;
import com.andyp.regsvc.domain.Organizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.junit.Assert.assertEquals;
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

    @Autowired
    private EventsDataManager eventsDataManager;



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
        assertNotNull(eventId);


        Event createdEvent = eventService.getEventById(eventId);
        assertNotNull(eventId);
        assertEquals(eventId, createdEvent.getEventId());
        assertEquals(newEvent.getOrganizerId(), createdEvent.getOrganizerId());
        assertEquals(newEvent.getDescription(), createdEvent.getDescription());
        assertEquals(newEvent.getTitle(), createdEvent.getTitle());
        assertNotNull(newEvent.getStartDate());
        assertNotNull(newEvent.getEndDate());
    }
}
