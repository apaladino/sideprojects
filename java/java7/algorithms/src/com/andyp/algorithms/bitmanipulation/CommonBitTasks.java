package com.andyp.algorithms.bitmanipulation;

/**
 * Created by andy on 10/30/16.
 */
public class CommonBitTasks {

    public static final int ADDRESS_LENGTH = 32;

    private void run() {

        int num = 36;
        printNumberAndBits("\nOriginal Number", num);

        /*
            Arithmetic Shift:  << divides by 2, >> multiplies by 2

               ** In an arithmetic right shift, we shift values to the right but we fill the
               *  new bits with the value of the sign bit.
          */
        int s1 = num << 2;
        printNumberAndBits("Arithmetic Shift >> 2", s1);  //36 << 2 == (36 * 2) * 2 == 144

        int s2 = s1 << 1;
        printNumberAndBits("Arithmetic Shift << 1", s2); // 144 << 2 == (144 % 2) == 72

        /*
            Logical Shift:

              ** In a logical shift right, we shift values to the right but we fill the
              * new bits with 0.
          */
        num = -75;
        printNumberAndBits("\nOriginal Number", num);

        // Logic Shift >> 1
        int s3 = num >>> 1;
        printNumberAndBits("Logical Shift >>> 1", s3); // -75 >>> 1 == 90

        /*
            Get Bit at position i
         */
        int i = 6;
        int bitAtI = getBit(num, i);
        System.out.println("getBit:\t Num: " + num + ", Num Bits: " +
                Integer.toBinaryString(num) + ", i: " + i + ", bitValue: " + bitAtI);


        /*
            Set Bit at position i
         */
        int setBitsInt = setBit(num, i, true);
        System.out.println("setBit:\t Num: " + num + ", Num Bits: " + Integer.toBinaryString(num)
            + ", i: " + i + ", setValue: " + 1 + ", result: " + setBitsInt
                + ", result Bits: " + Integer.toBinaryString(setBitsInt));


        /*
            Clear Bit
         */
        i = 4;
        printNumberAndBits("\nOriginal Number", num);
        int clearedBits = clearBit(num, i);
        System.out.println("clearBit:\t Num: " + num + ", Num Bits: " + Integer.toBinaryString(num) +
            ", i: " + i + ", cleared int: " + clearedBits + ", cleared bits: " + Integer.toBinaryString(clearedBits));

        /*
            Update Bit
         */
        i = 3;
        num = 64;
        printNumberAndBits("\nOriginal Number", num);

        int updatedBitInt = updateBit(num, i, true);
        System.out.println("updateBit:\tNum: " + num + ", Num Bits: " + Integer.toBinaryString(num) +
            ", i: " + i + ", val: 1, updatedBitInt: " + updatedBitInt + ", updated bits: " +
                Integer.toBinaryString(updatedBitInt));

        /*
            Clear most significant bits through i-th bit
         */
        num = -75;
        printNumberAndBits("\nOriginal Number", num);
        clearedBits = clearBitsMSBthroughI(num, i);
        System.out.println("clearBitsMSBthroughI\t Num: " + num + ", Num Bits: " + Integer.toBinaryString(num) +
            ", i: " + i + ", clearedBits: " + clearedBits + ", clearedBits bits: " +
                Integer.toBinaryString(clearedBits));

        /*
            Clear all bits from i through 0
         */
        printNumberAndBits("\nOriginal Number", num);
        i = 4;
        clearedBits = clearAllBitsFromIto0(num, i);
        System.out.println("clearAllBitsFromIto0\t Num:" + num + ", Num Bits: " + Integer.toBinaryString(num) +
            "i: " + i + ", clearedBits: " + clearedBits + ", clearedBits bits: " + Integer.toBinaryString(clearedBits));

        System.out.println("\n\n###");
        System.out.println("### Interview Questions");
        System.out.println("###");

        /*
            1. You are given two 32-bit numbers, N and M, and two bit positions, i and j.  Write a method to insert M into N
            such that M starts at bit j  and ands a bit i. You can assume that bits j through i have enough space to fit in
            all of M. That is, if M = 10011, you can assume that there are at least 5 bits between j and i. You would not,
            for example, have j=3 and i=2.
         */
        int M = Integer.parseInt("10011", 2);
        int N = Integer.parseInt("10000000000", 2);
        i = 2;
        int j = 6;
        int result = insertMintoN(M, N, i, j);
        printNumberAndBits("\ninsertMintoN", result);

        /*
            2. Binary to String: Given a real number between 0 and 1 (e.g. 0.72) that is passed in a double, print
               the binary representation. If the number cannot be represented accurately in binary with at most
               32 characters, print "ERROR"
         */
        String bin = printBinary(0.893D);
        System.out.println("PrintBinary\t" + bin);


        /*
            3. Flip Bit To Win: You have an integer and you can flip exactly one bit from a 0 to a 1. Write
               code to find the length of the longest sequence of 1s you could create.
         */
        num = 1775;
        int maxSeq = flipBitLongestSequence(num);
        System.out.println("\nflipBitLongestSequence\t Num: " + num + ", Num Bits: " + Integer.toBinaryString(num) +
            ", maxSeq: " + maxSeq);


        /*
            4. Conversion -  Write a function to determine the number of bits you would need to flip to
                convert integer A to integer B.

         */

        int count = bitConversionCount(29, 15);

        /*
            5. Pairwise Swap: Wriet a program to swap odd and even bits in an integer with as few instructions
                as possible. (e.g., bit 0 and 1 are swapped, bit 2 and 3 are swapped, and so on).
         */

        num = 1249;
        int swapped = swapOddAndEvenBits(num);
        System.out.println("\nswapOddAndEvenBits\t Num: " + num + ", Bits: " + Integer.toBinaryString(num) +
            ", swapped: " + swapped + ", Swapped Bits: " + Integer.toBinaryString(swapped));

        int odds = 0xaaaaaaaa;
        int evens = 0xeeeeeeee;
        printNumberAndBits("odds" , odds);
        printNumberAndBits("evens", evens);
    }

    private int swapOddAndEvenBits(int num) {

        // 1. create mask for just even 1 bits
        int evenMask = 0xaaaaaaaa;
        int oddMask = 0x55555555;

        // 2. create number with just event 1's from num
        int evenBits = num & evenMask;

        // 3. create a number with just odd 1's from num
        int oddBits = num & oddMask;

        // 4. shift even bits to the left by 1
        evenBits = evenBits << 1;

        // 5. shift odd bits to the right by 1. * logical shift to add 0 to MSB
        oddBits = oddBits >>> 1;

        // 6. combine both even and odd bits
        int combined = oddBits | evenBits;
        return combined;
    }

    private int bitConversionCount(int n, int m) {

        int count = 0;

        int i = n ^ m;  // XOR two numbers to find bits that differ

        // loop through each bit in i and count # of 1s
        while(i != 0){

            /*
                Ex:    0101
                                         0101
                                     AND 0001    ==> 1

                  >> 1  010              010
                                     AND 001     ==> 0

                  >> 1  01                01
                                     AND  01     ==> 1

                  >> 1  00                00
                                     AND  01     ==> 0

                 count = 1 + 0 + 1 + 0 == 2
             */
            count += i & 1;
            i = i >> 1; // Arithmetic shift right - divides by 2
        }

        return count;
    }


    private int flipBitLongestSequence(int num) {

        int maxSeq = 0;

        for(int i = 0; i < ADDRESS_LENGTH; i++){

            maxSeq = Math.max(maxSeq, findLongestSequenceOf1s(num, i));
        }

        return maxSeq;
    }

    private int findLongestSequenceOf1s(int num, int currentIndex) {

        int max = 0;
        int num1s = 0;

        for(int j=0; j < ADDRESS_LENGTH; j++){

            if(j== currentIndex || getBit(num, j) == 1){
                num1s ++;
                max = Math.max(num1s, max);
            }else
                num1s = 0; // reset count
        }

        return max;
    }

    private String printBinary(double num){
        if(num >= 1 || num <= 0)
            return "ERROR";

        StringBuilder binVal = new StringBuilder();
        binVal.append(".");

        while(num > 0){
            // if we go over 32 characters, return ERROR
            if(binVal.length() >= 32)
                return "ERROR";

            double rem = num * 2;  // shift decimal point to the left
            if(rem >= 1){
                binVal.append(1);
                num = rem - 1;
            }else{
                binVal.append("0");
                num = rem;
            }
        }


        return binVal.toString();
    }

    private int insertMintoN(int M, int N, int i, int j) {

        int result = N;

        // loop through each bit in M and update N's bits i-j
        int current = i;
        String mBits = Integer.toBinaryString(M);
        for(int k=0; k < mBits.length(); k++){
            boolean bitIs1 = mBits.charAt(k) == '1';

            result = updateBit(result, current, bitIs1);
            current++;
        }

        return result;
    }

    private int clearAllBitsFromIto0(int num, int i){

        // start with a sequence of 1's (-1)
        int mask = -1;
        printNumberAndBits("-1", mask);

        // then use a logical shift to move sign bit and replace with zeros
        mask = (mask >>> (31 -i));
        printNumberAndBits("mask >>> i", mask);

        // then negate mask
        mask = ~mask;
        printNumberAndBits("~ mask", mask);

        // then AND mask with num
        int andVal = num & mask;
        printNumberAndBits("num & mask", andVal);
        return andVal;
    }

    private int clearBitsMSBthroughI(int num, int i){

        // Create a mask with a 1 at the i-th bit
        int mask = (1 << i);
        printNumberAndBits("(1 << i)", mask);

        // Subtract 1 from mask to give a sequence of 0s followed by i 1s
        mask = mask - 1;
        printNumberAndBits("mask = mask - 1", mask);

        // AND num with mask to leave just last i bits
        int andVal = num & mask;
        printNumberAndBits("num & mask", andVal);
        return andVal;
    }

    private int updateBit(int num, int i, boolean bitIs1){

        // shift intended value left by i bits. This will create a value where bit i = value and all others are 0.
        int newVal = (bitIs1) ? 1 : 0;
        newVal = (newVal << i);

        // Create a mask to clear the i-th bit.
        int mask = ~(1 << i);

        // clear ith bit
        int clearedBitVal = num & mask;

        // Do a logical OR on the new value to set i-th bit.
        int updatedVal = clearedBitVal | newVal;
        return updatedVal;
    }

    private int clearBit(int num, int i){

        // Create a mask by shifting 1 left by i bits. ex: 0001 << 1 == 0010
        int mask = (1 << i);

        // negate the mask.  ex 0010 -> 1101
        int negatedMask = ~(mask);

        // perform a logical AND with num, this will clear the ith bit.
        return num & negatedMask;
    }

    private int setBit(int num, int i, boolean bitIs1){

        // Create a mask by shifting 1 left by i bits.  0001 << 1 == 0010
        int mask = (1 << i);

        // By performing an OR with num, only the value of bit i will change
        return num | mask;
    }

    private int getBit(int num, int i) {
        int val = 0;

        // Create a mask by shifting 1 left by i bits.  0001 << 1 == 0010
        int mask = (1 << i);

        // AND number by mask to zero out all other bits but bit i
        int andResult = num & mask;

        // if andResult != 0 then set val = 1, 0 otherwise
        val = (andResult != 0) ? 1 : 0;

        return val;
    }

    private void printNumberAndBits(String desc, int num) {
        System.out.println(String.format("%s:\t Num: %s, Bits: %s", desc, num,
                Integer.toBinaryString(num)));
    }

    public static void main(String args[]){
        new CommonBitTasks().run();
    }
}
