package com.andyp.algorithms.strings;

/**
 * Find length of the longest consecutive path from a given starting character
 */
public class LongestConsecutivePathOfCharacters {

    public static void main(String [] a){
        LongestConsecutivePathOfCharacters lcp = new LongestConsecutivePathOfCharacters();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for(int i=0; i < alphabet.length(); i++){
            System.out.println(String.format("%s - %s", alphabet.charAt(i), (alphabet.charAt(i) - 'a')));
        }

            char[][] m = {{'a', 'b', 'o'}, {'m', 'c', 'd'}, {'n', 'p', 'q'}};

        int longestPath = lcp.findLongestPath(m);
        System.out.println(longestPath);
    }

    private int findLongestPath(char[][] m) {

        int max = 0;


        for(int row=0; row < m[0].length; row++){
            for(int col=0; col < m.length; col++){
                char current = m[row][col];

                max = Math.max(max, findMax(m,row, col));
            }
        }

        return max;
    }

    private int findMax(char[][] m, int row, int col){
        if(row < 0 || col < 0 || row > m[0].length || col > m.length)
            return 0;

        char current = m[row][col];
        int max = findMax(m, row - 1, col, current);
        max = Math.max(max, findMax(m, row + 1, col, current));
        max = Math.max(max, findMax(m, row, col - 1, current));
        max = Math.max(max, findMax(m, row, col + 1, current));
        return (max > 0) ? max + 1: max;
    }

    private int findMax(char[][] m, int row, int col, char last) {
        System.out.println("findMax. \tlast:" + last + ",\trow: " + row + ",\tcol:" + col);
        // base case
        if(row < 0 || col < 0 || row > m[0].length -1 || col > m.length - 1)
            return 0;

        char current = m[row][col];
        int currentVal = charToInt(current);
        int lastVal = charToInt(last);
        int diff = currentVal - lastVal;

        if(diff == 1){  // this is a consecutive char to last char
            System.out.println("\t\tMatch: " + last + ", " + current);
            int max = findMax(m, row, col -1, current);         // left
            max = Math.max(max,findMax(m, row - 1, col, current));            // above
            max = Math.max(max,findMax(m, row +1, col, current));             // below
            max = Math.max(max,findMax(m, row, col + 1, current));            // right
            return max + 1;
        }

        return 0;
    }

    private int charToInt(char ch){
        return ch - 'a';
    }
}
