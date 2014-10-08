package com.citrix.regsvc.domain.social.linkedin.profile;



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
    @Column(name = "LNPROFILEID")
    private Long lnProfileId;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFILEID")
    List<LinkedInCompanyProfile> positions;


    public Long getLnProfileId() {
        return lnProfileId;
    }

    public void setLnProfileId(Long lnProfileId) {
        this.lnProfileId = lnProfileId;
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

}
