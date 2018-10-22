package com.andyp.algorithms.arrays;


/*
 * Problem: Given a string, write a function to check if it is a permutation of a palindrome.  
 */
public class PalendromePermutation {

	
	public static void main(String a[]){
		
		String input = "tact coa";
		
		new PalendromePermutation().run();
	}
	
	public void run(){
		String str = "abc";
		
		permutation(str);
	}
	
	public void permutation(String str){
		permutation(str, "");
	}
	
	public void permutation(String str, String prefix){
		if(str.length() == 0){
			System.out.println(prefix);
		}else{
			for(int i=0; i < str.length(); i++){
				String rem = str.substring(0,i) + str.substring(i+1);
				permutation(rem, prefix + str.charAt(i));
			}
		}
	}
	
}
