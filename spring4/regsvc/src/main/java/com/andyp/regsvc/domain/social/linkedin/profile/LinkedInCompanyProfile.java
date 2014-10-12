package com.andyp.regsvc.domain.social.linkedin.profile;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents a LinkedIn profile's past position.
 *
 * Created by apaladino on 10/5/14.
 */
@Entity
@Table(name="LINKEDINCOMPANYPROFILE")
public class LinkedInCompanyProfile {

    @Id
    @GeneratedValue
    @Column(name = "COMPANYPROFILEID")
    private Long companyProfileId;


    private Long profileId;
    private String name;
    private String title;
    private String summary;
    private String industry;
    private String companySize;
    private String ticker;
    private String type;
    private Boolean isCurrent;
    private Date startDate;

    public Long getCompanyProfileId() {
        return companyProfileId;
    }

    public void setCompanyProfileId(Long companyProfileId) {
        this.companyProfileId = companyProfileId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
