package com.andyp.algorithms.mathlogic;

import java.util.Arrays;

/**
 * Created by andy on 11/1/16.
 */
public class MathAndLogicExamples {

    public void run(){

        // Sieve of Eratoshenes
        String primes = genListOfPrimes(24);
        System.out.println("flags:" + primes);
    }

    private String genListOfPrimes(int max) {

        boolean[] flags = new boolean[max + 1];
        int count = 0;

        init(flags);

        int prime = 2;

        while(prime <= Math.sqrt(max)){

            // cross off remaining multiples of prime
            crossOff(flags, prime);

            prime = getNextPrime(flags, prime);
        }

        // loop through list of flags and add ones not crossed off already
        StringBuffer primes = new StringBuffer();

        for(int i=0; i < flags.length; i++){
            if(flags[i])
                primes.append(i);

            if(primes.length() > 0)
                primes.append(",");


        }
        return primes.toString();
    }

    private int getNextPrime(boolean[] flags, int prime) {
        int next = prime + 1;

        while( next < flags.length && !flags[next]){
            next++;
        }

        return next;
    }


    private void crossOff(boolean[] flags, int prime) {
        for(int i = prime * prime; i < flags.length; i+= prime){
            flags[i] = false;
        }
    }

    private void init(boolean[] flags) {
        for(int i=2; i < flags.length; i++){
            flags[i] = true;
        }
    }


    public static void main(String args[]){
        new MathAndLogicExamples().run();
    }
}
