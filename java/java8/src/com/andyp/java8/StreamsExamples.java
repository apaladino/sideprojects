package com.andyp.java8;

import com.andyp.java8.misc.DataHelper;
import com.andyp.java8.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsExamples {

	public void run(){
		
		/*
		 * Example sequential streams.
		 */
		List<User> users = DataHelper.buildUserList();
		System.out.println("-All users-");
		users.stream().forEach(u -> System.out.println(u.getUserInfo()));
		
		/*
		 * Example sequential stream using filter method
		 */
		System.out.println("\n-Filtered users-");
		Predicate<User> p = (u) -> u.getUserName().startsWith("c");
		users.stream().filter(p).forEach(u -> System.out.println(u.getUserInfo()));
		
		/*
		 * Example parallel stream
		 */
		System.out.println("\n-Paralel stream-");
		users.parallelStream().forEach(u -> System.out.println(u.getUserInfo()));
		
		/*
		 * Example converting an array to a stream using Stream.of
		 */
		User[] userArr = DataHelper.buildUserArray(5);
		Stream<User> stream = Stream.of(userArr);
		stream.forEach(u -> System.out.println(u.getUserInfo()));
		
		/*
		 * Example converting an array to a stream using Array.stream
		 */
		Stream<User> stream2 = Arrays.stream(userArr);
		stream2.filter(p).forEach(u -> System.out.println(u.getUserInfo()));
		
		/*
		 * Example using Stream aggregate functions: sum and ave 
		 *   - uses mapToInt with lambda expression
		 */
		List<Person> peeps = DataHelper.genPersonList();
		int sumOfAllAges = peeps.stream()
				.mapToInt(person -> person.getAge())
				.sum();
		System.out.println("\nSum of all ages is " + sumOfAllAges);
		
		OptionalDouble ave = peeps.stream()
				.mapToInt(person -> person.getAge())  // passes in lambda expression to map each person to an int value
				.average();
		
		if(ave.isPresent())
			System.out.println("\nAverage age is " + ave.getAsDouble());
		
		
	}
	
	public static void main(String [] args){
		new StreamsExamples().run();
	}
}
