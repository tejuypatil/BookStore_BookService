package com.devlopment.bookstore_bookservice.exception;

public class UserIsNotAdmin extends RuntimeException {
    public UserIsNotAdmin()
    {
        super("User is not Admin");
    }
}

