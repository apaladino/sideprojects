package com.andyp.algorithms.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 5/7/17.
 */
public class FibonacciExample {

    public static void main(String[] a){
        new FibonacciExample().run();
    }

    public void run(){

        //, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
        for(int i=0; i < 10; i++) {
            System.out.println(String.format("Fib(%d) = %d", i, fib(i)));
        }
    }


    public int fib(int n) {

        Map<Integer,Integer> cache = new HashMap<>();

        return cachedFib(n, cache);
    }

    public int cachedFib(int n, Map<Integer,Integer> cache){

        if(n < 2) return n;

        Integer val = cache.get(n);

        if(val == null)
            val = cachedFib(n-1, cache) + cachedFib(n-2, cache);
        else{
            cache.put(n, val);
        }

        return val.intValue();
    }
}
