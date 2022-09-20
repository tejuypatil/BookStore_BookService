package com.devlopment.bookstore_bookservice.entity;

import com.devlopment.bookstore_bookservice.dto.BookRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@Table(name = "Book")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int bookId;
    private String name;
    private String author;
    private int price;
    private LocalDate arrivalDate;
    private String coverImage;
    private int quantity;
    public Book(BookRequestDTO bookRequestDTO) {
        this.name = bookRequestDTO.name;
        this.author = bookRequestDTO.author;
        this.price = bookRequestDTO.price;
        this.quantity = bookRequestDTO.quantity;
    }

}
