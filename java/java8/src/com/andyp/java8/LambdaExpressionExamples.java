package com.andyp.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.andyp.java8.interfaces.FunctionalInterfaceWithArgs;
import com.andyp.java8.interfaces.SimpleFunctionalInterface;

public class LambdaExpressionExamples {

	public void run(){
	
		// Function interface example with void return type
		SimpleFunctionalInterface sfi = () -> System.out.println("Test function interface.");
		sfi.performAction();
		sfi = () -> {
			System.out.println("Test functional interface step A");
			System.out.println("Test functional interface step B\r");
		};
		sfi.performAction();
		
		// Functional interface with String return type and takes arguments
		FunctionalInterfaceWithArgs fiwa = (A, B) -> {
			return (String)A + (String)B;
		};
		String result = fiwa.getResult("alpha-", "beta");
		System.out.println(result + "\r");
		
		// Example using Java's built in Functional interfaces
		Runnable r = () -> System.out.println("Using Runnable (Functional) interface using a lambda expression\r");
		new Thread(r).start();
		
		List<String> someList = Arrays.asList( new String[] { "Duck", "Goose", "Chicken", "Pidgeon", "Penguin"});
		// Comparator is a functional interface since it only has one method.
		Comparator comp = (str1, str2) -> {
			return ((String)str1).compareTo((String)str2);
		};
		
		Collections.sort(someList, comp);
		
		/*
		 * Example using the new Consumer interface, which is a functional
		 * interface and thus can be used by lambda expressions.
		 *   - also uses the new forEach method added to the Collections interface, which will iterated over each item in the collection
		 */
		someList.forEach((str) -> System.out.println(str));
		
		
	}
	
	public static void main(String[] args){
		new LambdaExpressionExamples().run();
	}
}
