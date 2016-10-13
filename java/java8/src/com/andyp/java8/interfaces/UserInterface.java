package com.andyp.java8.interfaces;

public interface UserInterface {

	String getUserName();
	String getUserId();
	String getEmail();
	
	/*
	 * Default method example
	 * 
	 *   - New to java 8, you can add a default method to an interface, which allows you to define a method body.
	 */
	default String getUserInfo(){
		return getUserName() + ", " + getUserId() + ", " + getEmail();
	}
	
	/*
	 * Static method example
	 * 
	 *   - New to java 8, you can add static methods to an interface.
	 */
	static int compareTo(UserInterface u1, UserInterface u2){
		int result = u1.getUserName().compareTo(u2.getUserName());
		
		return result;
	}
}
