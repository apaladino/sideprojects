package com.andyp.algorithms.arrays;

/**
 * Created by andy on 4/26/17.
 */
public class FlipMatrixVertically {

    public static void main(String [] a){
        new FlipMatrixVertically().run();
    }

    private void run() {
        int[][] m = { {1,0,0},
                {0,0,1}};

        printMatrix(m);

        for(int row = 0; row < m.length; row++)
            for(int col = 0; col < m[0].length / 2; col++){

                int val = m[row][col];
                m[row][col] = m[row][((m[0].length - 1) - col)];
                m[row][((m[0].length - 1) - col)] = val;

            }

        printMatrix(m);
    }

    private void printMatrix(int [][] m){
        for(int r=0; r < m.length; r++){
            System.out.print("[ ");
            for(int c=0; c < m[0].length; c++){
                System.out.print(" " + m[r][c]);
            }

            System.out.println(" ]");
        }
    }

}
