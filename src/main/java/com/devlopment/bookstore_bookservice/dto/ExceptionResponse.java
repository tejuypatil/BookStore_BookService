package com.devlopment.bookstore_bookservice.dto;

public class ExceptionResponse {
    public String errorMessage;

    public ExceptionResponse(String message) {
        this.errorMessage = message;
    }
}
