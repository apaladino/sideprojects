package com.andyp.java8;

import com.andyp.java8.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/*
 * Predicate is a new Java functional interface that represents a predicate (boolean-valued function) of one argument. 
 */
public class PredicateExamples {

	
	public void run(){
	
		List<Person> peeps = new ArrayList();
		peeps.add(new Person("bob", "jones", "001", 34));
		peeps.add(new Person("sally", "jenkins", "002", 27));
		peeps.add(new Person("alex", "johnson", "003", 43));
		peeps.add(new Person("paul", "jeebs", "004", 55));
		
		/*
		 * Predicate using inner class
		 */
		Predicate<Person> pred = new Predicate<Person>() {

			@Override
			public boolean test(Person p) {
				return p.getAge() > 30;
			}
		};
		
		for(Person p : peeps){
			if(pred.test(p)){
				System.out.println(p);
			}
		}
		
		/*
		 * Predicate using Lambda expression
		 */
		Predicate<Person> predLambda = (p) -> p.getAge() > 30;
		peeps.forEach(p -> {
			if(predLambda.test(p))
				System.out.println(p);
		});
	}
	
	public static void main(String args[]){
		new PredicateExamples().run();
	}
	
}
