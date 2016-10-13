package com.andyp.java8.model;

import com.andyp.java8.interfaces.UserInterface;

public class User implements UserInterface{

	private String userName;
	private String userId;
	private String email;
	
	public User(String userName, String userId, String email){
		this.userName = userName;
		this.userId = userId;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
