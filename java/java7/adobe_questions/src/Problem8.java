
import java.math.BigInteger;

/**
 * @author Andy Paladino
 * @version 12/23/2007
 *
 *  Largest product in a series
    Problem 8

    The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.

    73167176531330624919225119674426574742355349194934
    96983520312774506326239578318016984801869478851843
    85861560789112949495459501737958331952853208805511
    12540698747158523863050715693290963295227443043557
    66896648950445244523161731856403098711121722383113
    62229893423380308135336276614282806444486645238749
    30358907296290491560440772390713810515859307960866
    70172427121883998797908792274921901699720888093776
    65727333001053367881220235421809751254540594752243
    52584907711670556013604839586446706324415722155397
    53697817977846174064955149290862569321978468622482
    83972241375657056057490261407972968652414535100474
    82166370484403199890008895243450658541227588666881
    16427171479924442928230863465674813919123162824586
    17866458359124566529476545682848912883142607690042
    24219022671055626321111109370544217506941658960408
    07198403850962455444362981230987879927244284909188
    84580156166097919133875499200524063689912560717606
    05886116467109405077541002256983155200055935729725
    71636269561882670428252483600823257530420752963450

    Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?

    Link: https://projecteuler.net/problem=8

You should include with your solution:
• A sample of the output
    SAMPLE OUTPUT
        Max product for 4 adjacent indexes in series is 5832. Test time: 62 millis.
        Max product for 13 adjacent indexes in series is 23514624000. Test time: 101 millis.
        Expected AssertionError thrown with numAdjacent has a negative value
        Expected AssertionError thrown with numAdjacent is too large
        Expected AssertionError thrown with null series
        Expected AssertionError thrown with empty series
• Why you chose the problems you did
    This seemed like a quick easy problem to solve, and a good primer to get me going.

• A description of the process you followed in solving the problem
    This is something that I just solved manually for a smaller series on a blank piece of paper to understand
    the algorithm and then just coded accordingly.

• What reference sources you used, if any

• How much time you spent on the exercise
    About 25 minutes, making things pretty and writing the different test cases (asserts).

 */
public class Problem8 extends BaseProblem{

    public static void main(String args[]){

        /*
            Note: the tests would typically be moved out into a unit test for this object.
         */
        new Problem8().runTests();
    }

    private void runTests() {

        String series = genSeriesArr();

        // test with 4 adjacent indexes
        int numAdjacent = 4;
        SeriesProduct maxProduct = findMaxProductIndexes(series, numAdjacent);
        System.out.println(String.format("Max product for %s adjacent indexes in series is %s. Test time: %s millis.",
                numAdjacent, maxProduct.getProduct(), maxProduct.getDurationMillis()));
        assertTrue(maxProduct.getProduct().intValue() == 5832, "Incorrect max product for 4 adjacent indexes");
        assertTrue(maxProduct.getDurationMillis() < 100,
                "Should take less than 100 millis to run for 4 adjacent indexes");

        // test with 13 adjacent indexes
        numAdjacent = 13;
        maxProduct = findMaxProductIndexes(series, numAdjacent);
        System.out.println(String.format("Max product for %s adjacent indexes in series is %s. Test time: %s millis.",
                numAdjacent, maxProduct.getProduct(), maxProduct.getDurationMillis()));
        assertTrue(maxProduct.getProduct().compareTo(new BigInteger("23514624000"))==0,
                "Incorrect max product for 13 adjacent indexes");
        assertTrue(maxProduct.getDurationMillis() < 200,
                "Should take less than 200 millis to run for 13 adjacent indexes");

        // test with negative input
        numAdjacent = -1;
        try{
            maxProduct = findMaxProductIndexes(series, numAdjacent);
            throw new IllegalStateException("Should have thrown an AssertionError if numAdjacent is < 2!");
        }catch(AssertionError e){
            System.out.println("Expected AssertionError thrown with numAdjacent has a negative value");
        }

        // test with numAdjacent larger than series size
        numAdjacent = series.length() + 1;
        try{
            maxProduct = findMaxProductIndexes(series, numAdjacent);
            throw new IllegalStateException("Should have thrown an AssertionError if numAdjacent is > series length");
        }catch(AssertionError e){
            System.out.println("Expected AssertionError thrown with numAdjacent is too large");
        }

        // test with null series
        numAdjacent = 4;
        try{
            maxProduct = findMaxProductIndexes(null, numAdjacent);
            throw new IllegalStateException("Should have thrown an AssertionError if series is null");
        }catch(AssertionError e){
            System.out.println("Expected AssertionError thrown with null series");
        }

        // test with empty series
        try{
            maxProduct = findMaxProductIndexes("", numAdjacent);
            throw new IllegalStateException("Should have thrown an AssertionError if series is empty");
        }catch(AssertionError e){
            System.out.println("Expected AssertionError thrown with empty series");
        }

    }

    /**
     * Given a series and a number of target adjacent indexes, find the max product for the number of
     * adjacent indexes.
     *
     * @param series
     * @param numAdjacent
     * @return
     */
    private SeriesProduct findMaxProductIndexes(String series, int numAdjacent) {

        long start = System.currentTimeMillis();

        assertTrue((series != null && series.length() > 0) , "Series must not be empty!");
        assertTrue((numAdjacent > 1) , "Target numAdjacent must be greater than one!");
        assertTrue((numAdjacent < series.length()) , "Target numAdjacent is larger than the series size!");

        SeriesProduct maxProduct = null;
        int maxLength = (series.length() - numAdjacent);

        // loop through the series array and look for max index array
        for(int i=0; i < maxLength; i++){

            SeriesProduct currentProduct = calculateProductForAdjacentIndexes(series, numAdjacent, i);

            if(maxProduct == null){
                maxProduct = currentProduct;
            }else{
                if(currentProduct.getProduct().compareTo(maxProduct.getProduct()) > 0){
                    maxProduct = currentProduct;
                }
            }

        }

        long end = System.currentTimeMillis();
        maxProduct.setDurationMillis((end - start));

        return maxProduct;
    }

    /**
     * Given a series array, the number of adjacent indexes, and a starting index, calculate the
     * product for the adjacent indexes in the series array and return the result.
     *
     * @param series
     * @param numAdjacent
     * @param startIndex
     * @return
     */
    private SeriesProduct calculateProductForAdjacentIndexes(String series, int numAdjacent, int startIndex) {

        int [] currentIndexes = new int[numAdjacent];
        BigInteger currentProductAmt = BigInteger.ZERO;

        // calculate the product for the current adjacent indexes
        for(int j=0; j < numAdjacent; j++){
            int val = Integer.parseInt(Character.toString(series.charAt(startIndex + j)));
            currentIndexes[j] = val;

            if(j == 0)
                currentProductAmt = BigInteger.valueOf(currentIndexes[j]);
            else{
                currentProductAmt = currentProductAmt.multiply(BigInteger.valueOf(currentIndexes[j]));
            }
        }

        return  new SeriesProduct(currentProductAmt, currentIndexes);

    }

    /*
        Helper method to generate an integer array representing a series of numbers
     */
    private String genSeriesArr() {
        String seriesStr = "73167176531330624919225119674426574742355349194934" +
                "96983520312774506326239578318016984801869478851843" +
                "85861560789112949495459501737958331952853208805511" +
                "12540698747158523863050715693290963295227443043557" +
                "66896648950445244523161731856403098711121722383113" +
                "62229893423380308135336276614282806444486645238749" +
                "30358907296290491560440772390713810515859307960866" +
                "70172427121883998797908792274921901699720888093776" +
                "65727333001053367881220235421809751254540594752243" +
                "52584907711670556013604839586446706324415722155397" +
                "53697817977846174064955149290862569321978468622482" +
                "83972241375657056057490261407972968652414535100474" +
                "82166370484403199890008895243450658541227588666881" +
                "16427171479924442928230863465674813919123162824586" +
                "17866458359124566529476545682848912883142607690042" +
                "24219022671055626321111109370544217506941658960408" +
                "07198403850962455444362981230987879927244284909188" +
                "84580156166097919133875499200524063689912560717606" +
                "05886116467109405077541002256983155200055935729725" +
                "71636269561882670428252483600823257530420752963450";

        return seriesStr;
    }

    /*
        Meta class to hold product information
     */
    class SeriesProduct{
        private BigInteger product;
        private int[] indexes;
        private long durationMillis;

        public SeriesProduct(BigInteger p, int[] idx){
            this.product = p;
            this.indexes = idx;
        }

        public BigInteger getProduct() {
            return product;
        }

        public int[] getIndexes() {
            return indexes;
        }

        public void setDurationMillis(long durationMillis) {
            this.durationMillis = durationMillis;
        }

        public long getDurationMillis(){
            return this.durationMillis;
        }
    }
}
