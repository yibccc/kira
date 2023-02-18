package com.kira.common;

/**
 * @author Kira
 * @create 2023-02-18 19:19
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
