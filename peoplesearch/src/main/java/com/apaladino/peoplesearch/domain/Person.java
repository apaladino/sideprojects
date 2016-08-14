package com.apaladino.peoplesearch.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * 
 * @author Andy.Paladino
 * @version 8/14/2016
 */
@Entity
@Table(name="PERSON", uniqueConstraints = {
        @UniqueConstraint(columnNames = "SSN")
})
public class Person {

	@Id
    @GeneratedValue
    @Column(name = "PERSON_ID")
    private Long personId;
	private String ssn;
	
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "HEIGHT_IN")
	private Double heightIn;
	
	@Column(name = "WEIGHT_LB")
	private Double weightLb;
	
	// getters/setters
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public Double getHeightIn() {
		return heightIn;
	}
	public void setHeightIn(Double heightIn) {
		this.heightIn = heightIn;
	}
	public Double getWeightLb() {
		return weightLb;
	}
	public void setWeightLb(Double weightLb) {
		this.weightLb = weightLb;
	}

}
