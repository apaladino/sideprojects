package com.andyp.java8;

import com.andyp.java8.interfaces.UserInterface;
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
		List<User> users = buildUserList();
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
	
	private List<User> buildUserList() {
		List<User> users = new ArrayList<>();
		users.add(new User("clint.eastwood", "uid001", "clint.eastwood@andyp.com"));
		users.add(new User("jackie.chan", "uid002", "jackie.chan@andyp.com"));
		users.add(new User("charles.bronson", "uid003", "charles.bronsonh@andyp.com"));
		users.add(new User("sylvester.stallone", "uid004", "sylvester.stallone@andyp.com"));
		users.add(new User("chuck.norris", "uid005", "chuck.norris@andyp.com"));
		
		return users;
	}

	public static void main(String args[]){
		new DefaultAndStaticMethodExamples().run();
	}
}
