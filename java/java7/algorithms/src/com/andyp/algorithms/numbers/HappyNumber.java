package com.andyp.algorithms.numbers;

import java.util.HashSet;
import java.util.Set;

/**
 * A number is Happy (Yes, it is a thing!) if it follows a sequence that ends in 1 after following the steps given below :
 Beginning with the number itself, replace it by the sum of the squares of its digits until either the number becomes 1 or loops endlessly in a cycle that does not include 1.

 For instance, 19 is a happy number. Sequence:
 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1
 */
public class HappyNumber {

    public static void main(String args[]){
        new HappyNumber().run();
    }

    public void run(){


        int [] numbers = { 19, 119, 20, 0, -1};

        for(int num : numbers) {
            boolean isHappy = isHappyNumber(num);
            System.out.println(String.format("Number: %d is happy? %s", num, isHappy));
        }
    }

    private boolean isHappyNumber(int num) {

        if(num == 0) return false;

        Long squaredNum = Long.valueOf(num);
        Set<Long> prevNumbers = new HashSet<>();

        while(squaredNum > 1){
            squaredNum = squareDigits(squaredNum);

            if(prevNumbers.contains(squaredNum)) // loop
                return false;

            prevNumbers.add(squaredNum);
        }

        return squaredNum.intValue() == 1;
    }


    private Long squareDigits(long n) {

        long sum = 0L;

        long num = n;
        long d = 0L;

        while(num >= 1){
            d = num % 10;
            num/= 10;
            sum += d * d;
        }

        return sum;
    }
}
