package com.company.readnovel.exceptions;

public class NoSuchEntityException extends RuntimeException{
    public NoSuchEntityException(String message){
        super(message);
    }
}
