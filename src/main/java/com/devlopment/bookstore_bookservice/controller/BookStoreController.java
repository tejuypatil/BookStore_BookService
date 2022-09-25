package com.devlopment.bookstore_bookservice.controller;

import com.devlopment.bookstore_bookservice.dto.BookRequestDTO;
import com.devlopment.bookstore_bookservice.dto.BookResponseDTO;
import com.devlopment.bookstore_bookservice.entity.Book;
import com.devlopment.bookstore_bookservice.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class BookStoreController {
    @Autowired
    public IBookService bookService;

    @GetMapping("/bookservice")
    public List<Book> getAllBooks(String token) {
        List<Book> bookList= bookService.getAllBooks(token);
        return bookList;
    }
    @PostMapping("/bookservice")
    public ResponseEntity<BookResponseDTO> createBookData(@RequestBody BookRequestDTO bookRequestDTO,@RequestHeader (name = "Authorization")String token){
        Book book = bookService.createBook(bookRequestDTO,token);
        return new ResponseEntity<BookResponseDTO>(new BookResponseDTO("Inserted book data successfully",book), HttpStatus.OK);
    }

    @GetMapping("bookservice/{bookId}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable("bookId") int bookId,@RequestHeader(name = "Authorization")String token){
        Book book = bookService.getBook(bookId,token);
        return new ResponseEntity<BookResponseDTO>(new BookResponseDTO("Get call for Id successful",book),HttpStatus.OK);

    }
    @PutMapping("/bookservice/{bookId}")
    public ResponseEntity<BookResponseDTO> updateBookById(@PathVariable("bookId")int bookId,@RequestBody BookRequestDTO bookRequestDTO,@RequestHeader(name = "Authorization")String token){
        Book book = bookService.updateBook(bookId,bookRequestDTO,token);
        return new ResponseEntity<BookResponseDTO>(new BookResponseDTO("Updated book data successfully",book),HttpStatus.OK);
    }
    @DeleteMapping("/bookservice/{bookId}")
    public ResponseEntity<BookResponseDTO> deleteByBookId(@PathVariable("bookId") int bookId,@RequestHeader(name = "Authorization")String token){
        bookService.deleteBook(bookId,token);
        return new ResponseEntity<BookResponseDTO>(new BookResponseDTO("Deleted successfully",null),HttpStatus.OK);
    }

    @GetMapping("/bookservice/search/{bookName}")
    public List<Book>findBookByBookName(@PathVariable("bookName")String bookName,@RequestHeader(name = "Authorization")String token){
        List<Book> book= bookService.findBookByName(bookName,token);
        return book;
    }
}

