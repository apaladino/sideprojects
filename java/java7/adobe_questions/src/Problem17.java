

import java.util.*;

/**
 * @author Andy Paladino
 * @version 12/23/2007
 *
 * Number letter counts
    Problem 17

    If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

    If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?

    NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
        The use of "and" when writing out numbers is in compliance with British usage.

    Link: https://projecteuler.net/problem=17

You should include with your solution:
• A sample of the output

    SAMPLE OUTPUT:
        -- Testing Written Number Constructor --
        Number: 342, Text: three hundred and forty-two, Count: 23
        Number: 1000, Text: one thousand, Count: 11
        Number: 100, Text: one hundred, Count: 10
        Number: 30, Text: thirty, Count: 6
        Number: 999, Text: nine hundred and ninety-nine, Count: 24
        Number: 5, Text: five, Count: 4
        Number: 1, Text: one, Count: 3
        Number: 111, Text: one hundred and eleven, Count: 19
        Number: 913, Text: nine hundred and thirteen, Count: 22
        Number: 1012, Text: one thousand and twelve, Count: 20
        Number: 507, Text: five hundred and seven, Count: 19
        Expected AssertionError thrown for an invalid number format

        -- Testing count of charactors for number range [1-1000]--
        Total characters in range is: 21094. Time: 241

• Why you chose the problems you did
    I liked this problem because it seemed simple at the start but I figured that there'd be some hidden complexity
    in the word parsing, which would making things interesting.

• A description of the process you followed in solving the problem
    I focused on starting out with a top down simple design, where you just loop through a range of numbers and
    count the valid characters. I wasn't caring initially how i counted the valid characters, with the simple top down
    design, I was able to isolate the complexity into a separate testable object.

• What reference sources you used, if any
    I looked up the Assert object since this is something that I don't typically use. I typically will use
    Spring Core's Assert object and leverage unit tests as much as possible.

• How much time you spent on the exercise
    About an hour. I originally wanted to represent the number as an integer array and had to backtrack a little to
   clean things up, then spent some time making sure the english text logic worked as it should.

 */
public class Problem17 extends BaseProblem{

    public static void main(String args[]){
        new Problem17().runTests();
    }

    private static Map<Integer, String> numbersMap = new HashMap<>();
    private static Map<String, String> tensMap = new HashMap<>();
    private static final String THOUSANDS = "thousand";
    private static final String HUNDREDS = "hundred";
    private static final String AND = "and";

    static{
        numbersMap.put(0, "");
        numbersMap.put(1, "one");
        numbersMap.put(2, "two");
        numbersMap.put(3, "three");
        numbersMap.put(4, "four");
        numbersMap.put(5, "five");
        numbersMap.put(6, "six");
        numbersMap.put(7, "seven");
        numbersMap.put(8, "eight");
        numbersMap.put(9, "nine");
        tensMap.put("", "");
        tensMap.put("two", "twenty");
        tensMap.put("three", "thirty");
        tensMap.put("four", "forty");
        tensMap.put("five", "fifty");
        tensMap.put("six", "sixty");
        tensMap.put("seven", "seventy");
        tensMap.put("eight", "eighty");
        tensMap.put("nine", "ninety");

    }


    public void runTests(){

        ArrayList<Interval> ints = new ArrayList<>();
        ints.add(new Interval(0,1));
        ints.add(new Interval(0,1));
        ints.add(new Interval(0,1));
        ints.add(new Interval(0,0));


        ArrayList<Interval> result = mergeIntervals(ints);
        System.out.println(result);

        /*
        Input 	Expected Result
[[-1,3], [1,2], [2,4]]

[[-1,4]]
[[0,1], [0,1], [0,1], [0,0]]

[[0,1]]
[[1,3], [2,9]]

[[1,9]]
[[1,3], [2,6], [8,10], [15,18]]

[[1,6], [8,10], [15,18]]
[[1,2], [2,3], [3,4], [4,5]]

[[1,5]]
[Empty]

[Empty]
[[-5,-3], [-4,-2], [0,10]]

[[-5,-2], [0,10]]
[[0,1]]

[[0,1]]
         */
    }

    public ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervalsList){
        ArrayList<Interval> ints = new ArrayList<>();

        if(intervalsList == null || intervalsList.isEmpty())
            return ints;

        if(intervalsList.size() == 1)
            return intervalsList;

        // sort array on start
        Collections.sort(intervalsList, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });

        for(int i=0; i < intervalsList.size(); i++){
            Interval current = intervalsList.get(i);

            for(int j=i+1; j < intervalsList.size(); j++){
                Interval next = intervalsList.get(j);
                if(next.start <= current.end && next.end >= current.end){
                    current.end = next.end;
                    i++;
                }else{
                    if(next.start <= current.end && next.end < current.end){
                        i++;
                    }
                    break;
                }
            }

            ints.add(current);
        }

        return ints;
    }

    class Interval {
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString(){
            return "[" + start + " , " + end + "]";
        }
    }
    /**
     * Given a number range start and end values, it will convert each number to its English text representation
     * and count each valid character, returning the sum of the number of valid characters for the entire range.
     *
     * @param start
     * @param end
     * @return sum of all valid characters in english representation for each number in range.
     */
    private long countCharactersInNumberRange(int start, int end) {

        long totalChars = 0L;

        for(int i = start; i <= end; i++){
            WrittenNumber wn = new WrittenNumber(Integer.toString(i));
            //System.out.println(wn);
            totalChars += wn.getNumChars();
        }
        return totalChars;
    }


    private void testCountChars1to1000() {


        System.out.println("\n-- Testing count of charactors for number range [1-1000]--");

        long start = System.currentTimeMillis();

        long count = countCharactersInNumberRange(1,1000);

        long end = System.currentTimeMillis();
        long diff = end - start;

        System.out.println(String.format("Total characters in range is: %s. Time: %s",
                count, diff));

        assertTrue(count == 21094, "Incorrect count for range [1-1000]");
        assertTrue(diff < 1000, "Should take less than a second to run.");
    }



    /*
        Test the WrittenNumber constructor, since this is where the bulk of the parsing/counting logic exists.

        ** This belongs in a separate unit test for the WrittenNumber object
     */
    private void testWrittenNumberConstructor() {
        System.out.println("\n-- Testing Written Number Constructor --");
        String[] numbers = { "342", "1000", "100", "30", "999", "5", "1", "111", "913", "1012", "507"};
        int[] expectedCounts = { 23, 11, 10, 6, 24, 4, 3, 19, 22, 20, 19};

        for(int i=0; i < numbers.length; i++) {
            String num = numbers[i];
            int expectedCount = expectedCounts[i];

            WrittenNumber number = new WrittenNumber(num);
            assertTrue((expectedCount == number.getNumChars()), "Incorrect letter count for number: " + num);
            System.out.println(number.toString());

        }

        // test with invalid number format
        try{
            new WrittenNumber("400.00");
            throw new IllegalStateException("Should have thrown an AssertionError");
        }catch (AssertionError e){
            System.out.println("Expected AssertionError thrown for an invalid number format");
        }
    }


    /*
        Meta class to hold written number info.

        ** Typically, this would live in a separate file,
           and would require a unit test due to its complexity,
           but I'm keeping it here for simplicity.
     */
    class WrittenNumber{
        String num;
        String onesVal;
        String tensVal;
        String hundredsVal;
        String thousandsVal;
        String textRepresentation;
        int numChars = 0;

        /**
         * Constructor for WrittenNumber. It will parse out the text representation for the number and count
         * the number of valid characters.
         *
         * @param n - Number
         */
        public WrittenNumber(String n){

            // redimentary assertion to check for bad input
            assertTrue(n.matches(".*[a-zA-Z.,].*") == false, "Invalid number parameter given.");

            this.num = n;

            // parse out the word values for each digit
            for(int i=0; i < num.length(); i++){
                char ch = num.charAt(i);
                int digit = Integer.parseInt(Character.toString(ch));

                setDigitStringValue(i, digit);
            }

            // set the english text representation of number
            setTextRepresentation();

            // set count of letters
            countLettersInTextRepresentation();
        }

        /**
         * Go through the text representation for the number and count valid characters
         */
        private void countLettersInTextRepresentation() {
            if(this.textRepresentation == null || this.textRepresentation.isEmpty())
                return;

            Set<Character> invalidChars = new HashSet<>();
            invalidChars.add(' ');
            invalidChars.add('-');

            int letterCount = 0;
            for(int i=0; i < this.textRepresentation.length(); i++){
                char ch = this.textRepresentation.charAt(i);

                if(invalidChars.contains(ch))
                    continue;

                letterCount++;
            }

            this.numChars = letterCount;
        }

        public void setTextRepresentation(){
            StringBuilder sb = new StringBuilder();
            boolean hasThousandsVal = thousandsVal != null && thousandsVal.length() > 0;
            boolean hasHundredsVal = hundredsVal != null && hundredsVal.length() > 0;
            boolean hasTensVal = tensVal != null && tensVal.length() > 0;
            boolean hasOnesVal = onesVal != null && onesVal.length() > 0;

            if(hasThousandsVal){
                sb.append(thousandsVal)
                    .append(" ")
                    .append(THOUSANDS);
            }

            if(hasHundredsVal){
                if(sb.length() > 0)
                    sb.append(" ");

                sb.append(hundredsVal)
                    .append(" ")
                    .append(HUNDREDS);

            }

            if(hasTensVal){
                if(sb.length() > 0)
                    sb.append(" ")
                      .append(AND)
                      .append(" ");

                String tensStr = parseTensVal();
                sb.append(tensStr);
            }

            if(hasOnesVal){
                if(hasTensVal){

                    if(tensVal.equals("one") == false){
                        sb.append("-")
                          .append(onesVal);
                    }

                }else{
                    if(hasThousandsVal || hasHundredsVal)
                        sb.append(" and ");

                    sb.append(onesVal);
                }
            }

            this.textRepresentation = sb.toString();
        }

        private String parseTensVal() {
            StringBuilder sb = new StringBuilder();

            switch(tensVal){
                case "one":
                    switch(onesVal){
                        case "one":
                            sb.append("eleven");
                            break;
                        case "two":
                            sb.append("twelve");
                            break;
                        case "three":
                            sb.append("thirteen");
                            break;
                        case "four":
                            sb.append("fourteen");
                            break;
                        case "five":
                            sb.append("fifteen");
                            break;
                        case "six":
                            sb.append("sixteen");
                            break;
                        case "seven":
                            sb.append("seventeen");
                            break;
                        case "eight":
                            sb.append("eighteen");
                            break;
                        case "nine":
                            sb.append("nineteen");
                            break;
                    };
                    break;
                default:
                    sb.append(tensMap.get(tensVal));

            }
            return sb.toString();
        }

        /*
            Given the digit and the exponant position, set the appropriate digit string value
         */
        public void setDigitStringValue(int pos, int digit) {

            int maxExp = num.length();

            switch(maxExp){
                case 4: // thousands
                    switch(pos){
                        case 0:
                            this.thousandsVal = numbersMap.get(digit);
                            break;
                        case 1:
                            this.hundredsVal = numbersMap.get(digit);
                            break;
                        case 2:
                            this.tensVal = numbersMap.get(digit);
                            break;
                        case 3:
                            this.onesVal = numbersMap.get(digit);
                    }
                    break;
                case 3: // hundreds
                    switch(pos) {
                        case 0:
                            this.hundredsVal = numbersMap.get(digit);
                            break;
                        case 1:
                            this.tensVal = numbersMap.get(digit);
                            break;
                        case 2:
                            this.onesVal = numbersMap.get(digit);
                    }
                    break;
                case 2: // tens
                    switch(pos) {
                        case 0:
                            this.tensVal = numbersMap.get(digit);
                            break;
                        case 1:
                            this.onesVal = numbersMap.get(digit);
                    }
                    break;
                case 1: // ones
                    this.onesVal = numbersMap.get(digit);

            }
        }

        public String toString() {
            return String.format("Number: %s, Text: %s, Count: %s",
                    this.num, getTextRepresentation(), this.numChars);
        }

        public String getTextRepresentation(){
            return this.textRepresentation;
        }

        public int getNumChars(){
            return this.numChars;
        }
    }
}
