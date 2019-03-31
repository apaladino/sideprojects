package com.andyp.algorithms.matrix;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a method findSpiral to traverse a 2D matrix of ints in a clockwise spiral order and append the elements to an output ArrayList if Integers.
 */
public class FindSpiralMatrix {

    public static enum Direction{
        UP, DOWN, LEFT, RIGHT
    };

    public static void main(String[] args){
        new FindSpiralMatrix().run();
    }

    public void run(){

        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};


        ArrayList<Integer> result = findSpiral(matrix);
        printMatrix(matrix);

        System.out.println("\nRESULTS: \t " + Arrays.toString(result.toArray(new Integer[result.size()])));
    }

    private void printMatrix(int[][] matrix) {
        for(int r=0; r < matrix.length; r++){
            StringBuilder sb = new StringBuilder();
            sb.append("{");

            int count = 0;
            for(int c=0; c < matrix[0].length; c++){
                if(count > 0)
                    sb.append(" , ");
                sb.append(matrix[r][c]);
            }

            sb.append("}");
            System.out.println(sb.toString());
        }
    }

    private ArrayList<Integer> findSpiral(int[][] matrix) {

        ArrayList<Integer> results = new ArrayList<>();

        Direction dir = Direction.RIGHT;

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        int t=0, b = numRows-1, l=0, r = numCols -1;

        int[][] arr = matrix;

        while(t<= b && r >= l){

            switch(dir){

                case RIGHT: {
                    for(int col = l; col <= r; col++)
                        results.add(arr[t][col]);

                    t++;
                    dir = Direction.DOWN;
                    break;
                }
                case DOWN: {
                    for(int row = t; row <= b; row--)
                        results.add(arr[row][r]);
                    r--;
                    dir = Direction.LEFT;
                    break;
                }
                case LEFT: {
                    for(int col = r; col >= l; col--)
                        results.add(arr[b][col]);
                    b--;
                    dir = Direction.UP;
                    break;
                }
                case UP: {
                    for(int row = b; row >= t; row--){
                        System.out.println("row: " + row + ", l: " + l);
                        results.add(arr[row][l]);
                    }
                    l++;
                    break;
                }
            }
        }
        /*
        while(t <= b && r >= l){

            switch(dir){
                case RIGHT: {
                    for( int c=l; c <= r; c++)
                        results.add(matrix[t][c]);

                    dir = Direction.DOWN;
                    t++;
                    break;
                }
                case DOWN: {
                    for(int row=t; row <= b; row++)
                        results.add(matrix[row][r]);
                    r--;
                    dir = Direction.LEFT;
                    break;
                }
                case LEFT: {
                    for(int col = r; col >= l; col--)
                        results.add(matrix[b][col]);
                    b--;
                    dir = Direction.UP;
                    break;
                }
                case UP: {
                    for(int row = b; row >= t; row--)
                        results.add(matrix[row][l]);
                    l++;
                    dir = Direction.RIGHT;
                    break;
                }
            }


        } */


        return results;
    }
}
/*
nput 	Expected Result 	Your Result 	Outcome
{1, 2}
{3, 4}

[1, 2, 4, 3]

java.lang.ArrayIndexOutOfBoundsException: -1 : Check console for error details.
	      Error

0 1
1 0

[1, 0, 1, 0]

java.lang.ArrayIndexOutOfBoundsException: -1 : Check console for error details.
	      Error

{1, 2, 3},
{4, 5, 6},
{7, 8, 9}

[1, 2, 3, 6, 9, 8, 7, 4, 5]

java.lang.ArrayIndexOutOfBoundsException: -1 : Check console for error details.
	      Error

{1, 2, 3, 4},
{5, 6, 7, 8},
{9, 10, 11, 12}}

[1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

java.lang.ArrayIndexOutOfBoundsException: -1 : Check console for error details.
	      Error

{{1, 2, 3, 4, 5, 6},
{7, 8, 9, 10, 11, 12},
{13, 14, 15, 16, 17, 18}}

[1, 2, 3, 4, 5, 6, 12, 18, 17, 16, 15, 14, 13, 7, 8, 9, 10, 11]

java.lang.ArrayIndexOutOfBoundsException: -1 : Check console for error details.
 */
