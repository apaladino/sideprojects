package com.andyp.algorithms.strings;

/**
 * Created by andy on 7/30/18.
 *
 *
 *             B  A  N  A  N  A
 *         ---------------------
 *         B | T
 *         A |    T
 *         N |       T
 *         A |          T
 *         N |             T
 *         A |                T
 */
public class LongestPalindrome {

    public static void main(String[] args){
        new LongestPalindrome().run();
    }

    public void run(){
        String input = "bananas";

        String lpn = lpn(input);
        System.out.println("result: " + lpn);
    }

    String lpn(String s){

        int n = s.length();
        int palindronBeginsAt = 0;
        int max_len = 1;
        boolean palindrone[][] = new boolean[n][n];

        // simple case: length 1
        for(int i=0; i < n; i++)
            palindrone[i][i] = true;

        // simple case: length 2
        for(int i=0; i < n-1; i++){
            if(s.charAt(i) == s.charAt(i+1)){
                palindrone[i][i+1] = true;
                palindronBeginsAt = i;
                max_len = 2;
            }
        }

        // find palindrones of length 3 or more
        for(int curr_len = 3; curr_len <= n; curr_len++){
            for(int i=0; i < n - curr_len + 1; i++){
                int j = i + curr_len - 1;
                if(s.charAt(i) == s.charAt(j) && // 1. 1st and last chars must match
                        palindrone[i+1][j-1]){      // 2. the rest of the substring should be a palindrone
                    palindrone[i][j] = true;
                    palindronBeginsAt = i;
                    max_len = curr_len;
                }
            }
        }

        printMatrix(palindrone, s);
        // at this point we should have the first and last index for the longest palindrone
        return s.substring(palindronBeginsAt, max_len + 1);
    }

    void printMatrix(boolean[][] matrix, String s){

        for(int i=0; i < s.length(); i++){
            System.out.print(s.charAt(i) + "\t");
        }
        System.out.println("");
        for(int i=0; i < s.length(); i++){
            System.out.print("-\t");
        }
        System.out.println("");

        for(int row = 0; row < matrix[0].length; row++){
            for(int col = 0; col < matrix.length; col++){
                if(col > 0)
                    System.out.print("\t");

                System.out.print( (matrix[row][col]) ? 1 : 0);
            }
            System.out.println("");
        }

    }
}
