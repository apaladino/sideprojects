package com.andyp.regsvc.data.events;

import com.andyp.regsvc.domain.Event;
import com.andyp.regsvc.domain.Organizer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by apaladino on 10/11/14.
 */
@Repository
public class EventsDataManagerImpl implements EventsDataManager {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Long createEvent(Event newEvent) {

        Session session = sessionFactory.getCurrentSession();

        session.save(newEvent);

        return newEvent.getEventId();
    }

    @Override
    public Long createOrganizer(Organizer organizer) {
        Session session = sessionFactory.getCurrentSession();

        session.save(organizer);

        return organizer.getOrganizerId();
    }

    @Override
    public Event findEventById(Long eventId) {
        Session session = sessionFactory.getCurrentSession();

        Event event =  (Event) session.get(Event.class, eventId);

        return event;
    }
}
