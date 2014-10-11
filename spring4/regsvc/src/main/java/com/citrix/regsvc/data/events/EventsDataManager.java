package com.citrix.regsvc.data.events;

import com.citrix.regsvc.domain.Event;
import com.citrix.regsvc.domain.Organizer;

/**
 * Created by apaladino on 10/11/14.
 */
public interface EventsDataManager {


    Long createEvent(Event newEvent);

    Long createOrganizer(Organizer organizer);
}
