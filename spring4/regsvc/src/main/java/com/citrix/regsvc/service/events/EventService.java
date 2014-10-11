package com.citrix.regsvc.service.events;

import com.citrix.regsvc.domain.Event;
import com.citrix.regsvc.domain.Organizer;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by apaladino on 10/11/14.
 */
public interface EventService {

    @Transactional
    Long createEvent(Event newEvent);

    @Transactional
    Long createOrganizer(Organizer organizer);
}
