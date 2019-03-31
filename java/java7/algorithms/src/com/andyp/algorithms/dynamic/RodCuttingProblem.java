package com.andyp.algorithms.dynamic;

/*
 * Given a rod of length n inches and a table of prices pi for i D 1; 2; : : : ; n, 
 * determine the maximum revenue rn obtain- able by cutting up the rod and selling the pieces. 
 * Note that if the price pn for a rod of length n is large enough, an optimal solution may require no cutting at all.
 */
public class RodCuttingProblem {
	
	public static void main(String args[]){
		new RodCuttingProblem().run();
	}
	
	public void run(){
		
		int[] prices = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
		int n = 4; 
		
		long start = System.currentTimeMillis();
		int maxVal = cutRod(prices, n);
		long end = System.currentTimeMillis();
		System.out.println(String.format("MaxVal: %d, Time for n=%d is: %d millis", maxVal, n, (end-start)));
		
		n = 8;
		start = System.currentTimeMillis();
		maxVal = cutRod(prices, n);
		end = System.currentTimeMillis();
		System.out.println(String.format("MaxVal: %d, Time for n=%d is: %d millis", maxVal, n, (end-start)));
		
		
		start = System.currentTimeMillis();
		maxVal = optimizedCutRod(prices, n);
		end = System.currentTimeMillis();
		System.out.println(String.format("*MaxVal: %d, Time for n=%d is: %d millis", maxVal, n, (end-start)));
		
		
	}

	private int optimizedCutRod(int[] prices, int n){
	
		int [] solved = new int[n+1];
		for(int i=0; i <= n; i++){
			solved[i] = -1 * Integer.MAX_VALUE;
		}
		
		return doOptimizedCutRod(prices, n, solved);
		
	}
	private int doOptimizedCutRod(int[] prices, int n, int[] solved) {
		
		if(solved[n] >= 0)
			return solved[n];
		
		int q = -1 * Integer.MAX_VALUE;
		if(n == 0)
			q = 0;
		else{
			
			for(int i=0; i < n; i++){
				q = Math.max(q, prices[i] + doOptimizedCutRod(prices, n-i + 1, solved));
			}
			
		}
		
		solved[n] =  q;
		return q;
	}

	private int cutRod(int[] prices, int n) {
		
		if(n == 0) return 0;
		
		int q = -1 * Integer.MAX_VALUE;
		
		for(int i=0; i < n; i++){
			
			q = Math.max(q, prices[i] + cutRod(prices, n-1));
		}
		
		return q;
	}

}
