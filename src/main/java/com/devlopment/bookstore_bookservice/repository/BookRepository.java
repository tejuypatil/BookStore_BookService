package com.devlopment.bookstore_bookservice.repository;

import com.devlopment.bookstore_bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findBookByName(String bookName);
}
