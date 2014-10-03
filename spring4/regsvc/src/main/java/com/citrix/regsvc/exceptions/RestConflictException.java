package com.citrix.regsvc.exceptions;

/**
 * Exception to denote conflicting rest calls
 * @author: Andy Paladino
 * @version: 10/3/14
 */
public class RestConflictException extends Exception {


    public RestConflictException(String msg){
        super(msg);
    }

}
