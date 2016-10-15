package com.andyp.java8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.andyp.java8.misc.DataHelper;

/*
 * Examples of Java 8's new date time objects in java.time package
 */
public class DateTimeExamples {

	public static void main(String args[]){
		
		/*
		 * Example using Instants
		 */
		Instant start = Instant.now();
		DataHelper.doSomeWork(10);
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);
		System.out.println("Duration: "  + duration.toMillis() + " millis");
		
		/*
		 * LocalDate example. LocalDate objects just represent the date value (month-day-year, etc), not date and time values.
		 */
		LocalDate ld = LocalDate.now();
		System.out.println("\nLocaldDate: " + ld);
		
		LocalDate specificDate = LocalDate.of(2016, 1, 1);  // LocalDate objects start with 1, not 0, so this is January 1st, 2016
		System.out.println("\nSpecific date: " + specificDate);
		
		/*
		 * LocalTime example. LocalTime objects just represent the time portion of a date.
		 */
		LocalTime lt = LocalTime.now();
		System.out.println("\nLocalTime now = " + lt);
		
		LocalTime t2 = LocalTime.of(14, 23, 54, 999);
		System.out.println("\nSpecific local time: " + t2);
		
		/*
		 * LocalDateTime example
		 */
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		System.out.println("\nLocalDateTime: " + ldt);
	}
}
