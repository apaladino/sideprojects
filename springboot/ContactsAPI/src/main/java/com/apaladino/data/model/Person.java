package com.apaladino.data.model;

import java.util.List;

/**
 * Person domain object
 *
 * @author Andy Paladino
 * @version June 28, 2020
 */
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;

    private List<Address> addresses;

    public Person(){}

    public Person(Integer id, String fname, String lname) {
        this.id = id;
        this.firstName = fname;
        this.lastName = lname;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
