package com.andyp.regsvc.service.events;

import com.andyp.regsvc.domain.Event;
import com.andyp.regsvc.domain.Organizer;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by apaladino on 10/11/14.
 */
public interface EventService {

    @Transactional
    Long createEvent(Event newEvent);

    @Transactional
    Long createOrganizer(Organizer organizer);

    @Transactional
    Event getEventById(Long eventId);
}
