package com.andyp.algorithms.strings;

/*
    Problem: Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
 */
public class LongestCommonSubstring {

    public static void main(String [] a){

        LongestCommonSubstring lcs = new LongestCommonSubstring();

        String s1 = "GeeksforGeeks";
        String s2 = "GeeksQuiz";

        int longestSubstringLength = lcs.findLongestCommonSubString(s1, s2);
        System.out.println("LCS: " + longestSubstringLength);
    }

    private int findLongestCommonSubString(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        int[][] suffixes = new int[m+1][n+1];
        int result = 0;

        for(int i=0; i < m; i++){

            for(int j=0; j < n; j++){

                if(i==0 || j== 0)
                    suffixes[i][j] = 0;
                else if(s1.charAt(i-1) == s2.charAt(j-1)){
                    suffixes[i][j] = suffixes[i-1][j-1] + 1;
                    result = Math.max(result, suffixes[i][j]);
                }else{
                    suffixes[i][j] = 0;
                }
            }
        }
        return result;
    }
/*

eeksforGeeks
 */

}
