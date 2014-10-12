package com.andyp.regsvc.domain.social.facebook.profile;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Andy Paladino
 * @version: 10/6/14
 */
@Entity
@Table(name = "FACEBOOKPROFILE")
public class FacebookProfile {

    @Id
    @GeneratedValue
    @Column(name = "FBPROFILEID")
    private Long fbProfileId;

    private String firstName;
    private String lastName;
    private String email;
    private String pictureUrl;
    private String ageRange;
    private String fbLink;
    private String locale;
    private String timezone;
    private Date createTime;


    public Long getFbProfileId() {
        return fbProfileId;
    }

    public void setFbProfileId(Long fbProfileId) {
        this.fbProfileId = fbProfileId;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getFbLink() {
        return fbLink;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
