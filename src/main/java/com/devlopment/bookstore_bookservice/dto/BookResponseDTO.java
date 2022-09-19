package com.devlopment.bookstore_bookservice.dto;

import java.awt.print.Book;

public class BookResponseDTO {
    public String message;
    public Book book;
    public BookResponseDTO(String message, Book book) {
        this.message = message;
        this.book= book;
    }

    public BookResponseDTO(String message, com.devlopment.bookstore_bookservice.entity.Book book) {

        this.message = message;
    }


}
