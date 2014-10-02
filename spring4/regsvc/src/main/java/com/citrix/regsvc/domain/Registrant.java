package com.citrix.regsvc.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by apaladino on 9/28/14.
 */
@Entity
@Table(name="REGISTRANTS")
public class Registrant {

    private Long registrantId;
    private String firstName;
    private String lastName;
    private String email;
    private Date createTime;

    @Id
    @GeneratedValue
    @Column(name = "REGISTRANTID")
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

