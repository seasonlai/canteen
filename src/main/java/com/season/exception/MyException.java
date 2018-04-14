package com.season.exception;

/**
 * Created by Wellhope on 2018/4/14.
 */
public class MyException extends RuntimeException{

    public MyException(){

    }

    public MyException(String msg){
        super(msg);
    }
}
