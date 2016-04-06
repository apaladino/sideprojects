package com.andyp.algorithms.arrays;

import java.util.Arrays;
import java.util.HashMap;

/*
 * Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

For example:

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

 */
public class TwoSum {

	public static void main(String a []){
		new TwoSum().run();
	}
	
	public void run(){
		int target = 15;
		int[] numbers = { 2, 7, 11, 15, 4};
		
		int [] indexes = twoSum(numbers,  target);
		System.out.println(Arrays.toString(indexes));
		
	}

	private int[] twoSum(int[] a, int target) {
		
		HashMap<Integer, Integer> numbersMap= new HashMap<>();
		
		int[] result = new int[2];
		
		for(int i=0; i < a.length; i++){
			
			if(numbersMap.containsKey(a[i])){
				result[0] = numbersMap.get(a[i]);
				result[1] = i;
				break;
			}else{
				numbersMap.put((target - a[i]), i);
			}
		}
		
		return result;
	}
}
