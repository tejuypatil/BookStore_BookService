package com.devlopment.bookstore_bookservice.service;

import com.devlopment.bookstore_bookservice.dto.BookRequestDTO;
import com.devlopment.bookstore_bookservice.entity.Book;
import com.devlopment.bookstore_bookservice.entity.UserData;
import com.devlopment.bookstore_bookservice.exception.InvalidTokenException;
import com.devlopment.bookstore_bookservice.exception.UserIsNotAdmin;
import com.devlopment.bookstore_bookservice.repository.BookRepository;
import com.devlopment.bookstore_bookservice.repository.UserRepository;
import com.devlopment.bookstore_bookservice.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookService implements IBookService {
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    public BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Book> getAllBooks(String token)
    {
        int userId= tokenUtility.decodeToken(token);
        Optional<UserData> optionalUserData = userRepository.findById(userId);
        if(optionalUserData.isPresent())
        {
            List<Book> bookList = bookRepository.findAll();
            if (bookList.isEmpty()){
                return null;
            }
            return bookList;
        }
        else {
            throw new InvalidTokenException(token);
        }
    }

    @Override
    public Book createBook(String token, BookRequestDTO bookRequestDTO) {
        int userId= tokenUtility.decodeToken(token);
        Optional<UserData> optionalUserData = userRepository.findById(userId);
        if(optionalUserData.isPresent())
        {
            UserData loggedInUser = optionalUserData.get();
            if(loggedInUser.isAdmin())
            {
                Book book = new Book(bookRequestDTO);
                return bookRepository.save(book);
            }
            else
            {
                throw new UserIsNotAdmin();
            }
        }
        else {
            throw new InvalidTokenException(token);
        }
    }
    @Override
    public Book getBook(int bookId,String token) {
        int userId= tokenUtility.decodeToken(token);
        Optional<UserData> optionalUserData = userRepository.findById(userId);
        if(optionalUserData.isPresent())
        {
            return bookRepository.findById(bookId).orElse(null);
        }
        else {
            throw new InvalidTokenException(token);
        }
    }
    @Override
    public Book updateBook(int bookId, BookRequestDTO bookRequestDTO,String token) {
        int userId= tokenUtility.decodeToken(token);
        Optional<UserData> optionalUserData = userRepository.findById(userId);
        if(optionalUserData.isPresent())
        {
            Book book = this.getBook(bookId,token);
            book.setName(bookRequestDTO.name);
            book.setAuthor(bookRequestDTO.author);
            return bookRepository.save(book);
        }
        else {
            throw new InvalidTokenException(token);
        }
    }
    @Override
    public void deleteBook(int bookId,String token) {
        int userId= tokenUtility.decodeToken(token);
        Optional<UserData> optionalUserData = userRepository.findById(userId);
        if(optionalUserData.isPresent())
        {
            bookRepository.deleteById(bookId);
        }
        else {
            throw new InvalidTokenException(token);
        }
    }

    public List <Book> findBookByName(String bookName,String token){
        int userId= tokenUtility.decodeToken(token);
        Optional<UserData> optionalUserData = userRepository.findById(userId);
        if(optionalUserData.isPresent()) {
            List<Book> book = bookRepository.findBookByName(bookName);
            return book;
        }
        else {
            throw new InvalidTokenException(token);
        }
    }
}