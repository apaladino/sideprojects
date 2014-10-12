package com.andyp.regsvc.service.events;

import com.andyp.regsvc.data.events.EventsDataManager;
import com.andyp.regsvc.domain.Event;
import com.andyp.regsvc.domain.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by apaladino on 10/11/14.
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventsDataManager eventsDataManager;


    @Override
    public Long createEvent(Event newEvent) {

        // TODO: Look up event by title, start and endDate and throw an error if exists.

       return eventsDataManager.createEvent(newEvent);
    }

    @Override
    public Long createOrganizer(Organizer organizer) {

        return eventsDataManager.createOrganizer(organizer);
    }

    @Override
    public Event getEventById(Long eventId) {
        return eventsDataManager.findEventById(eventId);
    }
}
