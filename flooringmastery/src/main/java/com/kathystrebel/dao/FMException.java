package com.kathystrebel.dao;

public class FMException extends Exception
{
    public FMException(String msg)
    {
        super(msg);
    }

    public FMException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
