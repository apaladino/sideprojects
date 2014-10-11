package com.citrix.regsvc.service.events;

import com.citrix.regsvc.data.events.EventsDataManager;
import com.citrix.regsvc.domain.Event;
import com.citrix.regsvc.domain.Organizer;
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
}
