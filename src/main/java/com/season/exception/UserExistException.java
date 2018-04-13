package com.season.exception;

public class UserExistException extends Exception
{
    public UserExistException(String errorMsg)
    {
        super(errorMsg);
    }
}
