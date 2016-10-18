package com.andyp.java8;

import com.andyp.java8.misc.DataHelper;
import com.andyp.java8.model.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsExamples {

	public void run() throws IOException{
		
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
		
		
		/*
		 * Example searching through files using streams
		 */
		Path path = FileSystems.getDefault().getPath("files", "starwars.txt");
		String searchTerm = "These are not the droids your looking for.";
		Optional<String> targetLine = null;
		
		try(Stream<String> lines = Files.lines(path)){  			// Files.lines is new to Java 8
			
			targetLine = lines.filter(ln -> ln.contains(searchTerm)).findFirst();
			
			if(targetLine.isPresent())
				System.out.println(new StringJoiner(" ", "[", "]").add("Search term found!").add(targetLine.get()));		// StringJoiner is new to Java 8
			else
				System.out.println("Search term not found.");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		/*
		 * Turning a text pattern into a stream
		 */
		Pattern pattern = Pattern.compile(" ");
		String speech = "Four score and seven years ago";
		Stream<String> sp = pattern.splitAsStream(speech);
		sp.forEach(System.out::println);
		
		/*
		 * Reducing a Stream
		 */
		sp.reduce("", (s1, s2) -> s1 + s2);
		
		
		/*
		 * Creating a stream of random integers
		 */
		IntStream intStream = new Random().ints();
		
		/*
		 * Iterating on a stream
		 */
		Stream.iterate(1, i -> i=i+1).limit(10).forEach(System.out::println);
		
		/*
		 * Stream range example
		 */
		IntStream.range(1, 10).forEach(System.out::println);
		

	}
	
	public static void main(String [] args){
		try {
			new StreamsExamples().run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
