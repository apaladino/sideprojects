package com.andyp.algorithms.arrays;

import java.util.Arrays;
import java.util.Stack;

/*
 * Problem: Evaluate the value of an arithmetic expression in Reverse Polish Notation. Valid operators are +, -, *, /. 
 * Each operand may be an integer or another expression. For example:

  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

 */
public class ReversePolishNotationEval {
	
	public static void main(String [] args){
		new ReversePolishNotationEval().run();
	}
	
	public void run(){
		
		String[] input = { "2", "1", "+", "3", "*" };
		
		int result = evalReversePolishNotation(input);
		System.out.println("Input: " + Arrays.toString(input) + " -> " + result);
		
		input = new String[] {"4", "13", "5", "/", "+"};
		result = evalReversePolishNotation(input);
		System.out.println("Input: " + Arrays.toString(input) + " -> " + result);
		
	}

	private int evalReversePolishNotation(String[] input) {
		

		Stack<String> opperandStack = new Stack<>();

		for(String val : input){
			
				switch(val){
				case "+": {
					int op2 = Integer.valueOf(opperandStack.pop());
					int op1 = Integer.valueOf(opperandStack.pop());
					String result = Integer.toString(op1 + op2);
					opperandStack.push(result);	
					break;
				}
				case "-": {
					int op2 = Integer.valueOf(opperandStack.pop());
					int op1 = Integer.valueOf(opperandStack.pop());
					String result = Integer.toString(op1 - op2);
					opperandStack.push(result);	
					break;
				}
				case "*": {
					int op2 = Integer.valueOf(opperandStack.pop());
					int op1 = Integer.valueOf(opperandStack.pop());
					String result = Integer.toString(op1 * op2);
					opperandStack.push(result);	
					break;
				}
				case "/": {
					int op2 = Integer.valueOf(opperandStack.pop());
					int op1 = Integer.valueOf(opperandStack.pop());
					String result = Integer.toString(op1 / op2);
					opperandStack.push(result);	
					break;
				}
				default:
					opperandStack.push(val);
			}
				
		}
				
			return Integer.valueOf(opperandStack.pop());
		}
			
}
