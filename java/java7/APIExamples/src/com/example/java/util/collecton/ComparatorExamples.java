package com.example.java.util.collecton;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ComparatorExamples {

	public static void main(String [] a){
		new ComparatorExamples().showExamples();
	}

	private void showExamples() {
		
		/*
		 * Custom Comparator
		 */
		List<ComparatorExamples.Widget> widgets = new ArrayList<>();
		widgets.add(this.new Widget("Zack"));
		widgets.add(this.new Widget("Jenny"));
		widgets.add(this.new Widget("Alan"));
		
		Collections.sort(widgets, this.new WidgetComparator());
		
		/*
		 * Collator - The Collator class performs locale-sensitive String comparison. You use this class to build searching and sorting routines for natural language text.
		 */
		Collator collator = Collator.getInstance(Locale.US);
		if(collator.compare("abc", "ABC") < 0){
			System.out.println("abc is less than ABC");
		}
		
		
	}
	
	class WidgetComparator implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			
			ComparatorExamples.Widget w1 = (Widget)arg0;
			ComparatorExamples.Widget w2 = (Widget)arg1;
			
			return w1.getName().compareTo(w2.getName());
		}
		
	}
	
	class Widget{
		private String name;
		
		public Widget(String name){
			this.name = name;
		}
		
		public String getName(){
			return name;
		}
		
		public void setName(String n){
			this.name = n;
		}
	}
	
}
