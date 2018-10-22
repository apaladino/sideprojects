
import java.math.BigDecimal;

/**
 * @author Andy Paladino
 * @version 12/24/2017
 *
 * Bouncy numbers
    Problem 112

    Working from left-to-right if no digit is exceeded by the digit to its left it is called an increasing number; for example, 134468.

    Similarly if no digit is exceeded by the digit to its right it is called a decreasing number; for example, 66420.

    We shall call a positive integer that is neither increasing nor decreasing a "bouncy" number; for example, 155349.

    Clearly there cannot be any bouncy numbers below one-hundred, but just over half of the numbers below one-thousand (525) are bouncy.
    In fact, the least number for which the proportion of bouncy numbers first reaches 50% is 538.

    Surprisingly, bouncy numbers become more and more common and by the time we reach 21780 the proportion of bouncy numbers is equal to 90%.

    Find the least number for which the proportion of bouncy numbers is exactly 99%.

You should include with your solution:
    • A sample of the output
        SAMPLE OUTPUT
        Testing isIncreasingStr helper method
        Testing isDescreasingStr helper method
        Testing findLeastBouncyNumberForPercentage method
        Disconnected from the target VM, address: '127.0.0.1:49994', transport: 'socket'
        For the target percentage: 99%, the least number found is 1587000
        All tests passed.
    • Why you chose the problems you did
        Seemed pretty straight-forward to solve if i could calculate the percentages correctly.

    • A description of the process you followed in solving the problem
        I started writing the helper methods to check to see if an integer was increasing or decreasing,
        then when i had those working, I created the main method to check to see if the integer was a bouncy number.
        Then, I wrote some tests to make sure the counts were correct for different percentages.
    • What reference sources you used, if any
        Looked up BigDecimal API spec.
    • How much time you spent on the exercise
        30 mins or so to write the tests and clean things up.
 */
public class Problem112 extends BaseProblem{

    public static void main(String[] args){
        new Problem112().runTests();
    }

    private void runTests() {

        System.out.println("Testing isIncreasingStr helper method");
        assertTrue(isIncreasingStr("134468"));
        assertTrue(isIncreasingStr("987468") == false);

        System.out.println("Testing isDescreasingStr helper method");
        assertTrue(isDecreasingStr("66420"));
        assertTrue(isDecreasingStr("66489") == false);
        assertTrue(isBouncyNumber(155349));
        assertTrue(isBouncyNumber(134468) == false);
        assertTrue(isBouncyNumber(66420) == false);

        System.out.println("Testing findLeastBouncyNumberForPercentage method");
        int leastBouncyNumber = findLeastBouncyNumberForPercentage(50);
        assertTrue(leastBouncyNumber == 538, "Incorrect least number for 50% target");

        leastBouncyNumber = findLeastBouncyNumberForPercentage(90);
        assertTrue(leastBouncyNumber == 21780, "Incorrect least number for 90% target");

        long start = System.currentTimeMillis();
        leastBouncyNumber = findLeastBouncyNumberForPercentage(99);
        long end = System.currentTimeMillis();
        long diff = end - start;
        assertTrue(leastBouncyNumber == 1587000, "Incorrect least number for 99% target");

        System.out.println(String.format("For the target percentage: %s, the least number found is %d",
                "99%", leastBouncyNumber));

        assertTrue(diff < 2500, "Should have ran in less than a 2.5 seconds. " + diff);
        System.out.println("All tests passed.");

    }

    private int findLeastBouncyNumberForPercentage(int percent) {

        int numBouncy = 0;
        int currentNum = 0;
        BigDecimal percentage = BigDecimal.ZERO;
        BigDecimal targetPercent = BigDecimal.valueOf((double)percent / 100);

        while(percentage.compareTo(targetPercent)  < 0){

            currentNum++;

            if(currentNum > 100 && isBouncyNumber(currentNum)){
                numBouncy++;

                percentage = BigDecimal.valueOf((double) numBouncy / currentNum);

                if(percentage.compareTo(targetPercent) == 0){
                    return currentNum;
                }
            }

        }

        throw new IllegalStateException("target percentage not found.");
    }

    private boolean isBouncyNumber(int n) {
        if(n < 100)
            return false;

        boolean isIncreasing = true;
        boolean isDecreasing = true;

        String strVal = Integer.toString(n);
        isIncreasing = isIncreasingStr(strVal);
        isDecreasing = isDecreasingStr(strVal);
        return !isIncreasing && !isDecreasing;
    }

    private boolean isDecreasingStr(String strVal) {
        boolean isDecreasing = true;

        int lastDigit = Integer.MAX_VALUE;
        for(int i=0; i < strVal.length(); i++){
            int digit = Integer.parseInt(Character.toString(strVal.charAt(i)));

            if(digit > lastDigit){
                isDecreasing = false;
                break;
            }

            lastDigit = digit;
        }

        return isDecreasing;
    }

    private boolean isIncreasingStr(String strVal) {

        boolean isIncreasing = true;

        int lastDigit = Integer.MIN_VALUE;
        for(int i=0; i < strVal.length(); i++){
            int digit = Integer.parseInt(Character.toString(strVal.charAt(i)));
            if(digit < lastDigit){
                isIncreasing = false;
                break;
            }

            lastDigit = digit;
        }

        return isIncreasing;
    }
}
