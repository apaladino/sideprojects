package com.citrix.regsvc.domain;

import com.citrix.regsvc.domain.social.linkedin.profile.LinkedInProfile;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by apaladino on 9/28/14.
 */
@Entity
@Table(name="REGISTRANTS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "EMAIL")
})
public class Registrant {

    @Id
    @GeneratedValue
    @Column(name = "REGISTRANTID")
    private Long registrantId;
    private String firstName;
    private String lastName;
    private String email;
    private Date createTime;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "registrant", optional = true)
    private LinkedInProfile linkedInProfile;

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

    public LinkedInProfile getLinkedInProfile() {
        return linkedInProfile;
    }

    public void setLinkedInProfile(LinkedInProfile linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }

}

