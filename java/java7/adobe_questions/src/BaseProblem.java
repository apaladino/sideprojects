/**
 * @author Andy Paladino
 * @version 12/24/2017
 * Parent class with shared method
 */
public class BaseProblem {

    /**
     * Utility method to assert that some boolean condition is true
     *
     * @param condition
     */
    public void assertTrue(boolean condition){
        if(!condition){
            throw new AssertionError();
        }
    }

    /**
     * Utility method to assert that some boolean condition is true
     *
     * @param condition
     * @param message
     */
    public void assertTrue(boolean condition, String message){
        if(!condition){
            throw new AssertionError(message);
        }
    }
}
