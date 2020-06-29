package com.apaladino.error;

/**
 * utility class to mark records which already exist
 *
 * @author Andy Paladino
 * @version June 28, 2020
 */
public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(String msg){
        super(msg);
    }
}
