package com.apaladino.error;

/**
 * utility class to mark records which are not found
 *
 * @author Andy Paladino
 * @version June 28, 2020
 */
public class NotFoundException extends Exception{
    public NotFoundException(String msg){
        super(msg);
    }
}
