package com.andyp.algorithms.strings;

/*
 * Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede". 
 */
public class ReverseVowelsExample {

	public static void main(String args[]){
		
		String str = "leetcode";
		
		String revStr = reverseVowels(str);
		System.out.println(revStr);
		
	}

	private static String reverseVowels(String str) {
		
		// [ l e e t c o d e ]
		if(str == null)
			return str;
		
		char [] charArray = str.toCharArray();
		int i = 0;
		int j = charArray.length - 1;
		
		while(i < j){
			
			char ch = charArray[i];
			
			if(isVowel(ch)){
				j = findNextVowel(charArray, i, j);
				swap(charArray, i, j);
				j--;
			}
			i++;
		}
		
		return new String(charArray);
	}

	private static void swap(char[] charArray, int i, int j) {
		char temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
	}

	private static int findNextVowel(char[] charArray, int i, int j) {
		
		while(j >= i){
			char ch = charArray[j];
			if(isVowel(ch))
				break;
			
			j--;
		}
		
		return j;
	}

	private static boolean isVowel(char ch) {
		switch(ch){
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
			case 'A':
			case 'E':
			case 'I':
			case 'O':
			case 'U':
				return true;
			default:
			return false;
		}
	}
}
