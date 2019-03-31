package com.andyp.algorithms.bitmanipulation;

import java.util.ArrayList;
import java.util.List;


/*
 * The unary bitwise complement operator "~" inverts a bit pattern; it can be applied to any of the integral types, 
 * making every "0" a "1" and every "1" a "0". For example, a byte contains 8 bits; applying this operator to a 
 * value whose bit pattern is "00000000" would change its pattern to "11111111".
 * 
 * The signed left shift operator "<<" shifts a bit pattern to the left, and the signed right shift operator ">>" 
 * shifts a bit pattern to the right. The bit pattern is given by the left-hand operand, and the number of positions 
 * to shift by the right-hand operand. The unsigned right shift operator ">>>" shifts a zero into the leftmost 
 * position, while the leftmost position after ">>" depends on sign extension.
 * 
 * The bitwise & operator performs a bitwise AND operation.
 * The bitwise ^ operator performs a bitwise exclusive OR operation.
 * The bitwise | operator performs a bitwise inclusive OR operation.
 * 
 */
public class BitMapExamples {

	public static void main(String[] args){
		
		// determine if i is a power of 2
        System.out.println(9 == Integer.highestOneBit(9)); // == false
        System.out.println(2 == Integer.highestOneBit(2)); // == true
        
        
        int number=9;
        int divisor = 3;
        int val = binaryDivide(number, divisor);
        
        
        // print missing characters in string
        char [] alpha = { 'a', 'b', 'c', 'd', 'e', 'f', 'x', 'y', 'z'};
        
        boolean[] flags = new boolean[26]; // number of lower case letters in english alphabet
        String str = "abcdefgxyz";
        
        for(int i=0; i < str.length(); i++){
        	char ch = str.charAt(i);
        	flags[ch - 'a'] = true;
        }
        
        System.out.print("Missing Letters: [");
        
        for(int i=0; i < flags.length; i++){
        	if(!flags[i])
        		System.out.print((char)('a' + i));
        }
        
        System.out.print("]\n");

        
        
	}

	private static int binaryDivide(int number, int divisor) {
		int quotient = 1;
		int remainder = 0;
		
		if(number == divisor)
			return 1;
		
		if(number < divisor)
			return 0;
		
		
		
		return 0;
	}
}
