package com.andyp.regsvc.domain;

import com.andyp.regsvc.domain.social.linkedin.profile.LinkedInCompanyProfile;

import javax.persistence.*;
import java.util.List;

/**
 * Created by apaladino on 10/10/14.
 */
@Entity
@Table(name = "Organizers")
public class Organizer {

    @Id
    @GeneratedValue
    private Long organizerId;

    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "EVENTS")
    private List<Event> events;


    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
