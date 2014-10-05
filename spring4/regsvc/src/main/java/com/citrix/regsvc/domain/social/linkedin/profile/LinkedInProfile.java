package com.citrix.regsvc.domain.social.linkedin.profile;



import com.citrix.regsvc.domain.Registrant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apaladino on 10/5/14.
 */
@Entity
@Table(name="LINKEDINPROFILE")
public class LinkedInProfile {

    @Id
    @GeneratedValue
    @Column(name = "PROFILEID")
    private Long profileId;
    private Long registrantId;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany
    @JoinColumn(name = "PROFILEID")
    List<LinkedInCompanyProfile> positions;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Registrant registrant;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

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

    public List<LinkedInCompanyProfile> getPositions() {
        if(positions == null){
            positions = new ArrayList<LinkedInCompanyProfile>();
        }
        return positions;
    }

    public void setPositions(List<LinkedInCompanyProfile> positions) {
        this.positions = positions;
    }

    public Registrant getRegistrant() {
        return registrant;
    }

    public void setRegistrant(Registrant registrant) {
        this.registrant = registrant;
    }
}
