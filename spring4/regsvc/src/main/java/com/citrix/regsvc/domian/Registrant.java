package com.citrix.regsvc.domian;

import java.util.Date;

/**
 * Created by apaladino on 9/28/14.
 */
public class Registrant {

    private Long registrantId;
    private String firstName;
    private String lastName;
    private String email;
    private Date createDate;

    public Long getRegistrantId() {
        return registrantId;
    }

    public void setRegistrantId(Long registrantId) {
        this.registrantId = registrantId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

