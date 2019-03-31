package com.andyp.algorithms.recursion;

/**
 * Created by andy on 4/9/18.
 */
public class PowerOfN {

    public static void main(String[] args){

        new PowerOfN().run();
    }

    public void run(){
        int x = 100;
        int n = 2;

        int total = powerSum(x,n);
        System.out.println("total = " + total);
    }

    private int powerSum(int x, int n) {
        return calc(x, n, 1);
    }

    private int calc(int x, int n, int num) {

        int current = (int) Math.pow(num, n);
        System.out.println("calc: x: " + x + ", n: " + n + ", num: " + num + ", current: " + current);

        // if current > x return 0
        if (current > x){
            System.out.println("<-- 0");
        return 0;
        }else if(current == x) {
            System.out.println("<-- 1");
            return 1;
        }else {
            int diff = x - current;
            int result =  calc(x, n, num+1) + calc(diff, n, num+1);
            System.out.println("<-- " + result);
            return result;
        }

    }
}
