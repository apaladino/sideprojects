package com.andyp.algorithms.matrix;

/**
 * Rotate Matrix
 *
 *
 * 1,0
 0,0

 {{0,0},{1,0}}

 --
 1,0,1
 1,0,1
 1,0,1

 {{1,1,1},{0,0,0},{1,1,1}}

 ---
 1,2,3
 4,5,6
 7,8,9

 {{3,6,9},{2,5,8},{1,4,7}}
 */
public class RotateMatrix {

    public static void main(String a[]){
        new RotateMatrix().run();
    }

    public void run(){
        int [][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};

        rotateMatrixCounterClockwise(matrix);
    }

    private void rotateMatrixCounterClockwise(int[][] m) {
        int h = m.length;
        int w = m[0].length;

        // transpose matrix
        for(int r = 0; r < h; r++){
            for(int c = r+1; c < w; c++){
                int temp = m[r][c];
                m[r][c] = m[c][r];
                m[c][r] = temp;
            }
        }

        printMatrix("transpose: " , m);

        // flip matrix horrizontally
        for(int row = 0; row < h/2; row++)
            for(int col = 0; col < w; col++){
                int temp = m[row][col];
                m[row][col] = m[h-1-row][col];
                m[h-1-row][col] = temp;
            }

        printMatrix("flipped horizontally: ", m);


    }

    private void printMatrix(String str, int[][] m) {
        System.out.println(str);

        for(int row = 0; row < m.length; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < m[0].length; col++) {
                if(col > 0)
                    sb.append(" , ");
                sb.append(m[row][col]);
            }
            System.out.println(sb.toString());
        }
        System.out.println("---\n");
    }
}

