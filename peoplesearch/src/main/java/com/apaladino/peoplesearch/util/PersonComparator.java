package com.apaladino.peoplesearch.util;

import java.util.Comparator;

import com.apaladino.peoplesearch.domain.Person;

import org.apache.commons.lang3.StringUtils;

/**
 * Custom comparator to allow for different sort fields and sort modes.
 * 
 * @author Andy.Paladino
 * @version 8/14/2016
 */
public class PersonComparator implements Comparator{

	private String sortField;
	private boolean ascending;
	
	public PersonComparator(String sortField, boolean ascending){
		assert !StringUtils.isEmpty(sortField) : "sortField must be defined for comparator to work.";
		
		this.sortField = sortField;
		this.ascending = ascending;
	}
	
	@Override
	public int compare(Object obj1, Object obj2) {
		
		Person p1 = (Person)obj1;
		Person p2 = (Person)obj2;
		
		switch(sortField){
			case "firstName": {
					if(ascending)
						return p1.getFirstName().compareTo(p2.getFirstName());
					else
						return p2.getFirstName().compareTo(p1.getFirstName());
				}
			case "lastName":{
				if(ascending)
					return p1.getLastName().compareTo(p2.getLastName());
				else
					return p2.getLastName().compareTo(p1.getLastName());
			}
			case "ssn": {
				if(ascending)
					return p1.getSsn().compareTo(p2.getSsn());
				else
					return p2.getSsn().compareTo(p1.getSsn());	
			}
			case "dateOfBirth": {
				if(ascending)
					return p1.getDateOfBirth().compareTo(p2.getDateOfBirth());
				else
					return p2.getDateOfBirth().compareTo(p1.getDateOfBirth());
			}
			case "heightIn": {
				if(ascending)
					return p1.getHeightIn().compareTo(p2.getHeightIn());
				else
					return p2.getHeightIn().compareTo(p1.getHeightIn());
			}
			case "weightLb":{
				if(ascending)
					return p1.getWeightLb().compareTo(p2.getWeightLb());
				else
					return p2.getWeightLb().compareTo(p1.getWeightLb());
			}
		};

		throw new IllegalStateException("Invalid sortField provided. [" + sortField + "]");
	}
}
