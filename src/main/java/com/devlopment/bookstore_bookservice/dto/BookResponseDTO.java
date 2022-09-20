package com.devlopment.bookstore_bookservice.dto;


import com.devlopment.bookstore_bookservice.entity.Book;

public class BookResponseDTO {
    public String message;
    public Book book;
    public BookResponseDTO(String message, Book book) {
        this.message = message;
        this.book= book;
    }


}
