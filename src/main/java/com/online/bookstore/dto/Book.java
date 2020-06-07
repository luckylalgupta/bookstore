package com.online.bookstore.dto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="book")
@AllArgsConstructor
@SequenceGenerator(name="seq",initialValue = 1)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String isbn;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String author;

    @NotBlank
    private String publisher;

    private Long bookCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getBookCount() {
        return bookCount;
    }
    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }

    public Book() {
    }

    public Book(String isbn, String title, String description,String author, String publisher,Long bookCount) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.bookCount = bookCount;
    }


}
