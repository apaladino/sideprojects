package com.andyp.java8;

import com.andyp.java8.interfaces.UserInterface;
import com.andyp.java8.misc.DataHelper;
import com.andyp.java8.model.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/*
 * With java 8, you can add default and static methods to an interface, 
 * which allows you to define a method body for each.
 */
public class DefaultAndStaticMethodExamples {

	public void run(){
	
		/*
		 * Example using interface default method (getUserInfo)
		 */
		System.out.println("-Unsorted-");
		List<User> users = DataHelper.buildUserList();
		users.forEach(u -> {
			System.out.println(u.getUserInfo());
		});
		
		
		/*
		 * Example using an interface's static method
		 */
		System.out.println("\n-Sorted-");
		Collections.sort(users, new Comparator<User>(){

			@Override
			public int compare(User user1, User user2) {
				return UserInterface.compareTo(user1, user2);
			}
		});
		users.forEach(u -> System.out.println(u.getUserInfo()));
	}
	
	

	public static void main(String args[]){
		new DefaultAndStaticMethodExamples().run();
	}
}
