package com.andyp.java8;

import com.andyp.java8.misc.DataHelper;
import com.andyp.java8.model.*;

import java.util.Arrays;
import java.util.List;
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
		 * Example parallell stream
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
		
		
	}
	
	public static void main(String [] args){
		new StreamsExamples().run();
	}
}
