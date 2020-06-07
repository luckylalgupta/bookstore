package com.online.bookstore.service;

import com.online.bookstore.dto.Book;
import com.online.bookstore.exception.BookStoreException;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Book getBookById(long id);

    List<Book> getAllBooks();

    boolean addBook(Book book,long count) throws BookStoreException;

    void deleteBook(long id);

    Book searchBook(String params) throws BookStoreException;

    Book getBookByTitle(String bookTitle,long count) throws BookStoreException;

    Book buyABook(String bookTitle,Long count) throws BookStoreException;

    List<String> searchMedia(String isbn) throws BookStoreException;



}
