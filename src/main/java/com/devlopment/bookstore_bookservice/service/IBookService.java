package com.devlopment.bookstore_bookservice.service;

import com.devlopment.bookstore_bookservice.dto.BookRequestDTO;
import com.devlopment.bookstore_bookservice.entity.Book;

import java.util.List;

public interface IBookService {
    public List<Book> getAllBooks(String token);

    public Book createBook(BookRequestDTO bookRequestDTO,String token);

    public Book getBook(int bookId, String token);

    public Book updateBook(int bookId, BookRequestDTO bookRequestDTO,String token);

    public void deleteBook(int bookId,String token);

    List<Book> findBookByName(String bookName,String token);
}
