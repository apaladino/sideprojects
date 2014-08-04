package org.hibernate.tutorial;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.domain.Person;
import org.hibernate.tutorial.util.HibernateUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Andy on 8/2/2014.
 */
public class EventManager {

    public static void main(String [] args){
        EventManager mgr = new EventManager();

        Long eventId = mgr.createAndStoreEvent("My Event - " + System.currentTimeMillis(), new Date());
        Long eventId2 = mgr.createAndStoreEvent("My Event - " + System.currentTimeMillis(), new Date());
        Long personId = mgr.createAndStorePerson("first - "  + System.currentTimeMillis(),
                "last - " + System.currentTimeMillis(), 22);

        // add person to event
        mgr.addPersonToEvent(personId, eventId);
        mgr.addPersonToEvent(personId, eventId2);
        mgr.addEmailToPerson(personId, "test@jedix.com");
        mgr.addEmailToPerson(personId, "test2@jedix.com");

        Person updatedPerson = mgr.getPersonById(personId);

        System.out.println("Person Events: " );
        for(Event event : (Set<Event>) updatedPerson.getEvents()){
            System.out.println(toJSON(event));
        }

        System.out.println("\nAll Events: ");
        List<Event> events = mgr.listEvents();

        for(Event event : events){
            System.out.println(toJSON(event));
        }
        HibernateUtil.getSessionFactory().close();
    }

    private Person getPersonById(Long personId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

        return (Person) session.load(Person.class, personId);
    }

    private void addEmailToPerson(Long personId, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);

        // adding to the emailAddress collection might trigger a lazy load of the collection
        aPerson.getEmailAddresses().add(emailAddress);

        session.getTransaction().commit();
    }

    private Long createAndStorePerson(String firstName, String lastName, int age) {
        Person p = new Person();
        p.setAge(age);
        p.setFirstName(firstName);
        p.setLastName(lastName);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(p);

        session.getTransaction().commit();

        return p.getId();
    }

    private static String toJSON(Event event) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

        List<Event> result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }

    private Long createAndStoreEvent(String title, Date date) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(new Date());
        session.save(theEvent);

        session.getTransaction().commit();

        return theEvent.getId();
    }

    private void addPersonToEvent(Long personId, Long eventId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person)session.load(Person.class, personId);
        Event anEvent = (Event)session.load(Event.class, eventId);
        aPerson.getEvents().add(anEvent);

        session.getTransaction().commit();
    }
}
