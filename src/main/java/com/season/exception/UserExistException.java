package com.season.exception;

public class UserExistException extends MyException
{
    public UserExistException(String errorMsg)
    {
        super(errorMsg);
    }
}
