package com.andyp.algorithms.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 7/29/18.
 */
public class MaxSubArraySumEqualsK {

    public static void main(String [] args){
        new MaxSubArraySumEqualsK().run();
    }

    public void run(){
        int[] nums = { -2, -1, 2, 1};
        int k = 1;


        //System.out.println("Max: K="+k + ", is " + maxSubArrayLen2(nums, k));

        int[] nums2 = {1, -1, 5, -2, 3};
        int k2 = 3;
        System.out.println("Max: K="+k + ", is " + maxSubArrayLen2(nums2, k2));

    }

    public int maxSubArrayLen2(int[] nums, int k){
        if( nums == null || nums.length == 0)
            return 0;

        // make a new array of incremental sums
        int [] incSums = new int[nums.length];
        incSums[0] = nums[0];
        for(int i=1; i < nums.length; i++){
            incSums[i] = nums[i] + incSums[i-1];
        }

        Map<Integer,Integer> sumsLengthMap = new HashMap<>();  // mapping for incremental sum to sub array length
        sumsLengthMap.put(0, -1);
        int max = 0;
        for(int i=0; i < nums.length; i++){
            int sumDiff = incSums[i] - k;

            if(sumsLengthMap.containsKey(sumDiff)){
                int subArrayLen = sumsLengthMap.get(sumDiff);
                max = Math.max(max, i - subArrayLen);
            }
            if(!sumsLengthMap.containsKey(incSums[i]))
                sumsLengthMap.put(incSums[i], i);  // set current sub array length

        }

        return max;
    }

    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        for (int i = 1; i < n; i++)
            nums[i] += nums[i - 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // add this fake entry to make sum from 0 to j consistent
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i] - k))
                max = Math.max(max, i - map.get(nums[i] - k));
            if (!map.containsKey(nums[i])) // keep only 1st duplicate as we want first index as left as possible
                map.put(nums[i], i);
        }
        return max;
    }
}
