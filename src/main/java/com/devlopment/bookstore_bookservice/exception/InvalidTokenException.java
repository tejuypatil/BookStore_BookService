package com.devlopment.bookstore_bookservice.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String token){
        super("Invalid Token:   "+token);
    }
}
