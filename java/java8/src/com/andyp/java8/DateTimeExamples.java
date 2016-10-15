package com.andyp.java8;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

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
		LocalDate currentDate = LocalDate.now();
		System.out.println("\nLocaldDate: " + currentDate);
		
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
		LocalDateTime ldt = LocalDateTime.of(currentDate, lt);
		System.out.println("\nLocalDateTime: " + ldt);
		
		/*
		 * ZonedDateTime examples
		 */
		ZonedDateTime gmt = ZonedDateTime.now(ZoneId.of("GMT+0"));
		System.out.println(gmt);
		ZonedDateTime ladt = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
		System.out.println(ladt);
		
		System.out.println("\n-All available time zones-");
		ZoneId.getAvailableZoneIds().forEach(z -> System.out.println(z));
		
		/*
		 * Clock Examples
		 */
		Clock clock = Clock.systemDefaultZone();
		System.out.println(clock);
		
		/*
		 * DateTimeFormatter Example -  formatting a LocalDate
		 */
		DateTimeFormatter df = DateTimeFormatter.ISO_DATE;
		System.out.println(df.format(currentDate));						// formats a LocalDate instance
		
		df = DateTimeFormatter.ISO_TIME;
		System.out.println(df.format(lt));								// formats a LocalTime instance
		
		df = DateTimeFormatter.ISO_DATE_TIME;
		System.out.println(df.format(ldt));								// formats a LocalDateTime instance
		
		df = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);		// Formatter with date style from the locale
		System.out.println(df.format(ldt));
		
		System.out.println(df.withLocale(Locale.FRENCH).format(ldt));	// Formatter using French Locale and Long FormatStyle
		
		/*
		 * Custom DateTimeFormatter using DateTimeFormatterBuilder
		 */
		DateTimeFormatterBuilder dtb = new DateTimeFormatterBuilder()
											.appendValue(ChronoField.MONTH_OF_YEAR)
											.appendLiteral("~")
											.appendValue(ChronoField.DAY_OF_MONTH)
											.appendLiteral("~")
											.appendValue(ChronoField.YEAR);
		df = dtb.toFormatter();
		System.out.println(df.format(ldt));
		
		
	}
}
