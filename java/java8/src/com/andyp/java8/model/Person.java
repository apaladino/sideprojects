package com.andyp.java8.model;

//sample class 
public class Person {

	private String firstName, lastName, soc;
	private int age;

	public Person(String fn, String ln, String soc, int age) {
		this.firstName = fn;
		this.lastName = ln;
		this.soc = soc;
		this.age = age;
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

	public String getSoc() {
		return soc;
	}

	public void setSoc(String soc) {
		this.soc = soc;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return firstName + " " + lastName + ", " + age;
	}
	
	public static int compareTo(Person p1, Person p2){
		int result = p1.getLastName().compareTo(p2.getLastName());
		
		if(result == 0)
			result = p1.getFirstName().compareTo(p2.getFirstName());
		
		if(result == 0)
			result = Integer.valueOf(p1.getAge()).compareTo(p2.getAge());
		
		return result;
	}
}
