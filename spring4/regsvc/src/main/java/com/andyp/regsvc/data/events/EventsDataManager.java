package com.andyp.regsvc.data.events;

import com.andyp.regsvc.domain.Event;
import com.andyp.regsvc.domain.Organizer;

/**
 * Created by apaladino on 10/11/14.
 */
public interface EventsDataManager {


    Long createEvent(Event newEvent);

    Long createOrganizer(Organizer organizer);

    Event findEventById(Long eventId);
}
