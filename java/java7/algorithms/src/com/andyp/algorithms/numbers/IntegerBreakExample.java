package com.andyp.algorithms.numbers;

/*
 * Problem: Given a positive integer n, break it into the sum of at least two 
 * positive integers and maximize the product of those integers. Return the 
 * maximum product you can get.
 * 
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * 
 * Note: you may assume that n is not less than 2. 
 */
public class IntegerBreakExample {
	
	public static void main(String args[]){
		
		IntegerBreakExample ex = new IntegerBreakExample();
		int n= 17;
		assert ex.isAPowerOfFour(64);
		assert ex.isAPowerOfFour(256);
		assert !ex.isAPowerOfFour(8);
		
	}

	public boolean isAPowerOfFour(int n) {

		if(n!= 1 && n < 4) 
	        return false;
	 
	    for(int i=1; i< n; i++){
	    	double pow = Math.pow(i, 4);
	    	if(pow == n)
	    		return true;
	    }
	    
	    return false;
	}

	private static int integerBreak(int n) {

		int[] prodArr = new int[n+1];
		
		for(int i=1; i < n+1; i++){
			for(int j=1; j < i+1; j++){
				if(i+j<=n){
					int maxI = Math.max(prodArr[i], i);
					int maxJ = Math.max(prodArr[j], j);
					int prod = maxI * maxJ;
					int newProd = Math.max(prodArr[i+j], prod);
					prodArr[i+j] = newProd;
					
	            }
			}
		}

		return prodArr[n];
	}

}
