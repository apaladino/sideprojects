package com.andyp.algorithms.recursion;

/*
 * Problem:  find the largest sub array with the greatest sum total.
 */
public class LargestSubArrayProblem {
	
	public static void main(String args[]){
		
		new LargestSubArrayProblem().run();
	}

	public void run(){
		
		int [] a = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
		
		SubArraySum maxSub = findMaxSubArray(a, 0, a.length - 1);
		System.out.println(maxSub);
	}
	
	public SubArraySum findMaxSubArray(int [] a, int low, int high){
		
		if(high == low){
			return new SubArraySum(low, high, a[low]);
		}else{
			
			// divide the problem in half and find the max sub array for left and right halfs, and also 
			// for max sub array crossing both halfs
			int mid = ((Double)Math.floor((low + high)/2)).intValue();
			
			SubArraySum leftSum = findMaxSubArray(a, low, mid);
			SubArraySum rightSum = findMaxSubArray(a, mid+1, high);
			SubArraySum crossSum = findMaxCrossingSubArray(a, low, mid, high);
			
			// check which branch is greater
			if(leftSum.getSum() > rightSum.getSum() && leftSum.getSum() > crossSum.getSum()){
				return leftSum;
			}else if(rightSum.getSum() > leftSum.getSum() && rightSum.getSum() > crossSum.getSum()){
				return rightSum;
			}else{
				return crossSum;
			}
		}
		
	}
	
	public SubArraySum findMaxCrossingSubArray(int [] a, int low, int mid, int high){
		int leftSum = -1 * Integer.MAX_VALUE;
		int sum = 0;
		int maxLeft = 0;
		
		// find max for left half of array
		for(int i=mid; i >= 0; i--){
			sum = a[i];
			
			if(sum > leftSum){
				leftSum = sum;
				maxLeft= i;
			}
		}
		
		// find max for right half of array
		int rightSum = -1 * Integer.MAX_VALUE;
		sum = 0; 
		int maxRight = 0;
		for(int i = mid+1; i < high; i++){
			sum = a[i];
			
			if(sum > rightSum){
				rightSum = sum;
				maxRight = i;
			}
		}
		
		// consolide all into a SubArraySum object and return
		return new SubArraySum(maxLeft, maxRight, (leftSum + rightSum));
	}
	
	/*
	 * Private class to hold summation information for each sub array
	 */
	class SubArraySum{
		private int maxLeft;
		private int maxRight;
		private int sum;
		
		public SubArraySum(int mLeft, int mRight, int sum){
			this.maxLeft = mLeft;
			this.maxRight = mRight;
			this.sum = sum;
		}
		protected int getMaxLeft() {
			return maxLeft;
		}
		protected void setMaxLeft(int maxLeft) {
			this.maxLeft = maxLeft;
		}
		protected int getMaxRight() {
			return maxRight;
		}
		protected void setMaxRight(int maxRight) {
			this.maxRight = maxRight;
		}
		protected int getSum() {
			return sum;
		}
		protected void setSum(int sum) {
			this.sum = sum;
		}
		
		public String toString(){
			return String.format("MaxLeft: %d\tMaxRight: %d\t Sum: %d", maxLeft, maxRight, sum);
		}
		
	}
}
