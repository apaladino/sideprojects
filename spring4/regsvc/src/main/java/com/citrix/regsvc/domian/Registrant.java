package com.citrix.regsvc.domian;

/**
 * Created by apaladino on 9/28/14.
 */
public class Registrant {

    private String firstName;
    private String lastName;
    private String email;

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


/*
	firstname: String,
    lastName: String,
    email: String,
    events : [{ type: Schema.Types.ObjectId, ref: 'Event' }],
    linkedInProfile : {type: Schema.Types.ObjectId, ref: 'LinkedInProfile'},
    facebookProfile : {type: Schema.Types.ObjectId, ref: 'FacebookProfile'}

 */




}

