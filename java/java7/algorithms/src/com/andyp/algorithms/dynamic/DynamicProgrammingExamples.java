package com.andyp.algorithms.dynamic;

import java.awt.Point;
import java.util.*;

/**
 * Created by andy on 11/3/16.
 */
public class DynamicProgrammingExamples {

    private void run(){

        /*
            Triple Step: A child is running up a staircase with n steps and can hop either 1 steps, 2 steps,
                         3 steps at a time. Implement a method to count how many possible ways the hild can run
                         up the stairs.

               Hint: We can compute the number of steps to 100 by the number of steps to 99, 98, and 97.
                     this corresponds to the child hipping 1, 2, or 3 steps at the end. Do we add those or multiype them?
                     THat is, is it f(100) = f(99) + f(98) + f(97) or f(99) * f(98) * f(97)?

         */

        int numSteps = findTripleSteps(10);
        System.out.println("NumSteps:" + numSteps);

        /*
            Robot in a grid:  Imagine a robot sitting on the upper left corner of grid with r rows and c collumns.
                              The robot can only move in two directiosn, right and down, but certain cells are
                              "off limits" such that the robot cannot step on them. Design an algorithm to find a
                              path for the robot from the top left to the bottom right.

                              #330, #359, ##87

                              Question: What other problem would we need to solve to know how to get to grid[r][c]?
                              Answer:   We'd have to know how to get to either grid[r-1][c] or grid[r][c-1].


         */

        int[][] grid = new int[5][5];
        grid[0][4] = 1;
        grid[1][0] = 1;
        grid[1][1] = 1;
        grid[2][2] = 1;
        grid[3][4] = 1;

        List<Point> path = findPath(grid);
        path.stream().forEach(p -> System.out.println(p.toString()));


        /*
            Write a method to return all subsets of a set.
         */
        List<String> s = new ArrayList();
        s.add("A");
        s.add("B");
        s.add("C");

        List<List<String>> powerSet = genSubsetsRecursive(s, 0);
        printSet(powerSet);

        /*
            Write a recursive function to multiply two numbers without using the * operator (or / operator).
            You can use addition, subtraction, and bit shifting, but you should minimize the number of these
            operations.

         */
        int prod = multiply(9, 7);
        System.out.println("Product of 9 and 7: " + prod);

        prod = multiplyWithBitShift(9,7);
        System.out.println("Product of 9 and 7: " + prod);

        prod = multiplyWithMemoization(9,7);
        System.out.println("Product of 9 and 7: " + prod);

        prod = minProduct(9,7);

        /* Towers of Hanoi */
      //  runTowersOfHanoi();


        String str = "abcd";

        List<String> allPerms = genPerms(str);
        allPerms.stream().forEach(s2 -> System.out.println(s2) );

        System.out.println("\n\nBuild Parens");
        Set<String> allPars = buildPars(3);
        allPars.stream().forEach(p -> System.out.println(p));

        /*
            Coins: Given an infinite number of quarters, dimes, nickels, and pennies, write code to calculate
                    the number of ways of representing n cents.
         */
        int ways = countWaysToMakeChange(43);
        System.out.println("MakeChange for 43: " + ways);

        /*
            Eight Queens: Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board
                          so that none of them share the same row, column, or diagnoal (all diagonal rows and cols).
         */

        Position[] solution = solveQueensEightProblem(8);
        System.out.println("\n\nEight Queen Solution: ");
        Arrays.stream(solution).forEach(p -> System.out.println(p));
    }

    private Position[] solveQueensEightProblem(int n) {

        Position[] positions = new Position[n];

        // start at row 0
        boolean foundSolution = solveQueensEightProblemHelper(n, 0, positions);

        if(foundSolution)
            return positions;
        else
            return new Position[0];

    }

    private boolean solveQueensEightProblemHelper(int n, int row, Position[] positions) {

        if(row == n) // reached the end of the rows
            return true;

        // loop through each column on current row and check to see if queen is under attack
        for(int col=0; col < n; col++){

            boolean foundValid = true;

            // check against any previous queen to see if we are under attack
            for(int queen = 0; queen < row; queen++){

                if(positions[queen].col == col ||  // same column
                       positions[queen].row - positions[queen].col == row - col ||
                        positions[queen].row + positions[queen].col == row + col){  // diag
                    foundValid = false;
                    break;
                }

            }

            if(foundValid){
                positions[row] = new Position(row, col);

                if(solveQueensEightProblemHelper(n, row+1, positions))
                    return true;
            }
        }

        return false;
    }

    class Position{

        int row, col;

        public Position(int x, int y){
            this.row = x;
            this.col = y;
        }

        public String toString(){
            return "(" + row + ", " + col + ")";
        }
    }

    private Set<String> buildPars(int n) {

        Set<String> allPars = new HashSet<>();

        if(n == 1){
            allPars.add("()");
        }else{

            Set<String> subPars = buildPars(n-1);

            for(String sp : subPars){

                // before
                allPars.add("()" + sp);

                // around
                allPars.add("(" + sp + ")");

                // after
                allPars.add(sp + "()");
            }
        }

        return allPars;
    }

    private int countWaysToMakeChange(int amt) {

        int [] coins = new int[] { 25, 10, 5,1 };

        return makeChange(amt, coins, 0);
    }

    private int makeChange(int amt, int[] coins, int index) {
        if(index >= coins.length - 1) return 1;

        int count = 0;

        int coin = coins[index];

        for(int i=0; i * coin < amt; i++){
            int rem = amt - (i * coin);
            count += makeChange(rem, coins, index+1);
        }

        return count;
    }

    private List<String> genPerms(String remainder) {
        int len = remainder.length();
        List<String> result = new ArrayList<>();

        // base case
        if(len == 0){
            result.add("");
            return result;
        }

        for(int i=0; i < len; i++){

            // remove ith char and find permutations of remaining chars
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1, len);

            List<String> partials = genPerms(before + after);

            // for each partial try charAt(i) + partial
            for(String sp : partials){
                result.add(remainder.charAt(i) + sp);
            }
        }

        return result;
    }



    private List<String> findPerms(String str, int n) {

        List<String> allPerms = new ArrayList<>();
        if(n == 1) {
            allPerms.add(str);
            return allPerms;
        }



        char ch = str.charAt(n);

        List<String> subPerms = findPerms(str.substring(0,n), n-1);

        for(String sp : subPerms){

            // generate all permutations of ch + sp
            for(int i=0; i <= sp.length(); i++){
                allPerms.add(sp.substring(0,i) + ch + sp.substring(i));
            }
        }

        return allPerms;
    }


    private void runTowersOfHanoi() {

        Stack<Integer>[] towers = new Stack[3];
        towers[0] = new Stack<>();
        towers[1] = new Stack<>();
        towers[2] = new Stack<>();
        towers[0].push(3);
        towers[0].push(2);
        towers[0].push(1);

        System.out.println("Starting towers of hanoi game. ");
        printTowers(towers);

        while(towers[2].size() < 3){

            // move as many peices as possible right
            boolean canMoveRight = true;
            while(canMoveRight){
                canMoveRight = movePiecesRight(towers);
            }

            boolean canMoveLeft = true;
            while(canMoveLeft){
                canMoveLeft = movePiecesLeft(towers);
            }
        }

    }

    private boolean movePiecesLeft(Stack<Integer>[] towers) {

        for(int i=towers.length - 1; i >= 0; i--){

            if(!towers[i].isEmpty()
                    && i > 0
                    && (towers[i-1].isEmpty() ||
                        towers[i-1].peek() > towers[i].peek())){
                int val = towers[i].pop();
                towers[i-1].push(val);
                return true;
            }
        }
        return false;
    }

    private boolean movePiecesRight(Stack<Integer>[] towers) {

        for(int i=0; i < towers.length; i++){

            if(!towers[i].isEmpty() &&
                    i < towers.length - 1 &&
                    (towers[i+1].isEmpty()
                            || towers[i+1].peek() > towers[i].peek())){
                int val = towers[i].pop();
                towers[i+1].push(val);
                return true;
            }
        }
        return false;
    }

    private void printTowers(Stack[] towers) {
        int count = 1;

        for(Stack tower : towers){
            System.out.println("Tower: " + count);
            tower.stream().forEach(t -> System.out.println(t));
            count++;
        }
    }

    private int minProduct(int n, int m) {
        int a = (n < m) ? n : m;
        int b = (n < m) ? m : n;

        return minProductHelper(a, b);
    }

    private int minProductHelper(int a, int b) {

        if(a == 0) return 0;
        if(a == 1) return b;

        int a2 = a >> 1;  // a divided by 2
        int halfProd = minProductHelper(a2, b);

        if(a % 2 == 0) // even
            return halfProd + halfProd;
        else
            return halfProd + halfProd + b;

    }

    private int multiplyWithMemoization(int n, int m) {
        int a = (n < m) ? n : m;
        int b = (n < m) ? m : n;

        int [] cache = new int[a + 1];

        return multiplyWithMemoHelper(a, b, cache);
    }

    private int multiplyWithMemoHelper(int a, int b, int[] cache) {

        if(a == 0) return 0;
        if(a == 1) return b;
        if(cache[a] > 0) return cache[a];

        // compute half. If uneven, compute other half. If even, double it.
        int a2 = a >> 1;
        int half = multiplyWithMemoHelper(a2, b, cache);
        int otherHalf = half;

        if(a % 2 == 1) { // odd
            otherHalf = multiplyWithMemoHelper(a - a2, b, cache);
        }

        cache[a] = half + otherHalf;
        return cache[a];
    }

    private int multiplyWithBitShift(int n, int m) {

        int a = (n < m) ? n : m;
        int b = (n < m) ? m : n;

        int prod = 0;

        if(a==0) return 0;
        if(a==1) return b;

        int a2 = a >> 1;                                // divide by 2
        int half = multiplyWithBitShift(a2, b);
        int otherHalf = half;

        if(a % 2 == 1) { // odd
            otherHalf = multiplyWithBitShift(a - a2, b);
        }

        return half + otherHalf;
    }

    private int multiply(int n, int m) {

        int a = (n < m) ? n : m;
        int b = (n < m) ? m : n;

        int prod = 0;

        if(a == 0) return 0;

        if(a == 1)
            return b;

        prod = multiply(a-1, b) + b;

        return prod;
    }


    private void printSet(List<List<String>> powerSet) {
        StringBuffer sb = new StringBuffer();

        for(int i=0; i< powerSet.size(); i++){
            sb.append("[");

            List<String> set = powerSet.get(i);

            for(int j=0; j < set.size(); j++){
                if(j > 0)
                    sb.append(",");

                sb.append(set.get(j));
            }
            sb.append("]\n");
        }
        System.out.println("Subsets of {A, B, C}\n" + sb.toString());
    }

    private List<List<String>> genSubsetsRecursive(List<String> set, int index) {

        List<List<String>> allSubsets;

        // base case: n = 0
        if(set.size() == index){
            allSubsets = new ArrayList<>();
            allSubsets.add(new ArrayList<String>());
        }else {
            // all over cases: n > 0
            allSubsets = genSubsetsRecursive(set, index + 1);

            // get next element in set
            String nextItem = set.get(index);

            // build subsets for nextItem
            List<List<String>> nextSubsets = new ArrayList<>();

            // go through each set for n+1, clone it and add nextItem
            for(List<String> subset : allSubsets){
                List<String> newSet = new ArrayList<>();
                newSet.addAll(subset);
                newSet.add(nextItem);
                nextSubsets.add(newSet);
            }

            // add all new subsets for next item to allSubsets
            allSubsets.addAll(nextSubsets);
        }

        return allSubsets;
    }

    private List<Point> findPath(int[][] grid) {

        if(grid == null || grid.length == 0)
            return null;

        List<Point> path = new ArrayList<>();

        if(getPath(grid, grid[0].length - 1, grid.length - 1, path))
            return path;
        else
            return null;
    }

    private boolean getPath(int[][] grid, int r, int c, List<Point> path) {
        System.out.println("*getPath(" + r + ", " + c + ")");
        // check to see if we are out of bounds
        if(r < 0 || c < 0 || isOffLimits(grid, r, c))
            return false;

        // if we are at the beginning or if not off limits add current point
        if(isAtBeginning(r, c) ||
                getPath(grid, r -1, c, path) ||
                getPath(grid, r, c - 1, path)){
            path.add(new Point(r,c));
            return true;
        }

        return false;
    }

    private boolean isOffLimits(int[][] grid, int r, int c) {
        return grid[r][c] == 1;
    }

    private boolean isAtBeginning(int r, int c) {
        return r == 0 && c == 0;
    }

    private int findTripleSteps(int n) {
        System.out.println("N: " + n);
        if(n < 0) return 0;
        else if(n == 1)
            return 1;
        else
            return findTripleSteps(n-1) + findTripleSteps(n-2) + findTripleSteps(n-3);
    }



    public static void main(String[] a){
        new DynamicProgrammingExamples().run();
    }
}
