package com.andyp.java8.misc;

import java.util.ArrayList;
import java.util.List;

import com.andyp.java8.model.Person;
import com.andyp.java8.model.User;

/*
 * Sample helper class to generate some sample data.
 */
public class DataHelper {

	public static List<User> buildUserList() {
		List<User> users = new ArrayList<>();
		users.add(new User("clint.eastwood", "uid001", "clint.eastwood@andyp.com"));
		users.add(new User("jackie.chan", "uid002", "jackie.chan@andyp.com"));
		users.add(new User("charles.bronson", "uid003", "charles.bronsonh@andyp.com"));
		users.add(new User("sylvester.stallone", "uid004", "sylvester.stallone@andyp.com"));
		users.add(new User("chuck.norris", "uid005", "chuck.norris@andyp.com"));
		
		return users;
	}
	
	public static List<Person> genPersonList() {
		List<Person> peeps = new ArrayList<>();
		peeps.add(new Person("Sally", "Smith", "001", 23));
		peeps.add(new Person("Stanley", "Smith", "002", 25));
		peeps.add(new Person("Percy", "Pickler", "003", 33));
		peeps.add(new Person("Abigail", "Bresley", "004", 35));
		peeps.add(new Person("Donald", "Duck", "005", 19));
		return peeps;
	}

	public static User[] buildUserArray(int numUsers) {
		User[] users = new User[numUsers];
		String[] firstNames = {"bob", "cindy", "sarah", "Kyle", "jacob", "mary", "derek"};
		String[] lastNames = {"jones", "moody", "smith", "jones", "mcnugget", "roberts", "eastwoord"};
		
		for(int i=0; i < numUsers; i++){
			String fn = firstNames[i % firstNames.length];
			String ln = lastNames[i % lastNames.length] + i;
			String userName = String.format("%s.%s", fn, ln);
			String userId = String.format("UID3d", i);
			String email = String.format("%s@andyp.com", userId);
			users[i] = new User(userName, userId, email);
		}
		return users;
	}
}
