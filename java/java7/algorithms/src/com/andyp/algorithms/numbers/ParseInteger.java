package com.andyp.algorithms.numbers;

/**
 * Problem:  Given an input String generate a valid integer value if able to.
 * 
 * @author andy
 *
 */
public class ParseInteger {

	public static void main(String [] args){
		
		/*
		 * Step 1 define your inputs:  
		 * 		ex:  "44", "-444", null, "", "0.001", "TEXT"
		 */
		
		String inputs[] = { "44", "-444", null, "", "30.9", "TEXT"};
		for(String str : inputs){
			try{
				int intVal = parseInt(str);
				
				System.out.println("Input Str: '" + str + "'\t int value: " + intVal);
			}catch(Exception e){
				System.out.println("Exception thrown for input: " + str + ". Error: " + e.getMessage());
			}
		}
	}
	
	public static int parseInt(String str){
		
		// in cases where str == "" we throw a NumberFormatException
		if(str.isEmpty())
			throw new NumberFormatException("Empty String");
		
		int val = 0;
		int decimalAddition = 0;
		// check to see if string is a negative value or a decimal
		boolean isNeg =  str.charAt(0) == '-';
		
		String strVal = str;
		if(isNeg){
			strVal = strVal.substring(1); // strip off beginning '-'
		}
		
		// check to see if str is a decimal value
		String [] strVals = strVal.split("\\.");
		if(strVals.length == 2){ // string is a decimal
			strVal = strVals[0];
			
			if(isRoundedUp(strVals[1])){
				decimalAddition = 1;
			}
		}
		
		// parse integer section of str
		int decimalPlace = 0;
		for(int i= strVal.length() - 1; i >= 0; i--){
			int ival = getIntVal(strVal.charAt(i));
			val += ival * (new Double(Math.pow(10, decimalPlace)).intValue());
			decimalPlace++;
		}
		
		if(decimalAddition > 0)
			val += decimalAddition;
		
		if(isNeg)
			val = -1 * val;
		
		return val;
	}
	public static int getIntVal(char ch){
	
		
		switch(ch){
			case '0' : return 0;
			case '1' : return 1;
			case '2' : return 2;
			case '3' : return 3;
			case '4' : return 4;
			case '5' : return 5;
			case '6' : return 6;
			case '7' : return 7;
			case '8' : return 8;
			case '9' : return 9;
		default:
			throw new NumberFormatException("Unable to convert char: " + ch);
		}
	}
	
	public static boolean isRoundedUp(String str){
		boolean isRoundUp = false;
		
		for(int i=str.length()-1; i>= 0; i--){
			int val = getIntVal(str.charAt(i));
			
			if(isRoundUp)
				val += 1;
			
			isRoundUp = (val >= 5);
			
		}
		
		return isRoundUp;
	}
	
	public static boolean isDecimal(String str){
		for(int i= str.length()-1; i >= 0; i--){
			if(str.charAt(i) == '.')
				return true;
		}
		
		return false;
	}
}
