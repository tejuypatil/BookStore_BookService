package com.devlopment.bookstore_bookservice.service;

import com.devlopment.bookstore_bookservice.dto.BookRequestDTO;
import com.devlopment.bookstore_bookservice.dto.UserResponseDTO;
import com.devlopment.bookstore_bookservice.entity.Book;
import com.devlopment.bookstore_bookservice.entity.UserData;
import com.devlopment.bookstore_bookservice.exception.InvalidTokenException;
import com.devlopment.bookstore_bookservice.exception.UserIsNotAdmin;
import com.devlopment.bookstore_bookservice.repository.BookRepository;
import com.devlopment.bookstore_bookservice.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    public BookRepository bookRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Book> getAllBooks(String token)
    {
        int userId= tokenUtility.decodeToken(token);
        UserResponseDTO userResponseDTO= restTemplate.getForObject("http://localhost:8086/userservice/" + userId,UserResponseDTO.class);
        UserData userData = userResponseDTO.getUserData();
        if(userData!= null){
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
    public Book createBook( BookRequestDTO bookRequestDTO,String token) {
        int userId= tokenUtility.decodeToken(token);
        UserResponseDTO userResponseDTO= restTemplate.getForObject("http://localhost:8086/userservice/" + userId,UserResponseDTO.class);
        UserData userData = userResponseDTO.getUserData();
        if(userData!= null)
        {
            UserData loggedInUser = userResponseDTO.getUserData();
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
        UserResponseDTO userResponseDTO= restTemplate.getForObject("http://localhost:8086/userservice/" + userId,UserResponseDTO.class);
        UserData userData = userResponseDTO.getUserData();
        if(userData!= null)
        {
            return bookRepository.findById(bookId).orElse(null);
        }
        else
        {
            throw new InvalidTokenException(token);
        }
    }
    @Override
    public Book updateBook(int bookId, BookRequestDTO bookRequestDTO,String token) {
        int userId= tokenUtility.decodeToken(token);
        UserResponseDTO userResponseDTO= restTemplate.getForObject("http://localhost:8086/userservice/" + userId,UserResponseDTO.class);
        UserData userData = userResponseDTO.getUserData();
        if(userData!= null)
        {
            Book book =  bookRepository.findById(bookId).orElse(null);
            book.setName(bookRequestDTO.name);
            book.setAuthor(bookRequestDTO.author);
            book.setPrice(bookRequestDTO.price);
            book.setQuantity(bookRequestDTO.quantity);
            return bookRepository.save(book);
        }
        else {
            throw new InvalidTokenException(token);
        }
    }
    @Override
    public void deleteBook(int bookId,String token) {
        int userId= tokenUtility.decodeToken(token);
        UserResponseDTO userResponseDTO= restTemplate.getForObject("http://localhost:8086/userservice/" + userId,UserResponseDTO.class);
        UserData userData = userResponseDTO.getUserData();
        if(userData!= null)
        {
            bookRepository.deleteById(bookId);
        }
        else {
            throw new InvalidTokenException(token);
        }
    }

    public List <Book> findBookByName(String bookName,String token){
            int userId= tokenUtility.decodeToken(token);
            UserResponseDTO userResponseDTO= restTemplate.getForObject("http://localhost:8086/userservice/" + userId,UserResponseDTO.class);
            UserData userData = userResponseDTO.getUserData();
            if(userData!= null)
            {
            List<Book> book = bookRepository.findBookByName(bookName);
            return book;
     }
        else {
            throw new InvalidTokenException(token);
        }
    }
    public Book createBooks(String name, String bookId) {

        Book book = new Book();
        book.setName(name);
        book.setBookId(Integer.parseInt(bookId));
        return book;
    }
}