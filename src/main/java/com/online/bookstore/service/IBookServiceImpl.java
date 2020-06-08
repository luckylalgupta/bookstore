package com.online.bookstore.service;

import com.online.bookstore.dto.Book;
import com.online.bookstore.exception.BookStoreException;
import com.online.bookstore.repository.BookRepository;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
@Service
public class IBookServiceImpl implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public boolean addBook(Book book,long currCount) throws BookStoreException {

        String isbn = book.getIsbn();
        String title = book.getTitle();
        String  description = book.getDescription();
        String author = book.getAuthor();
        String  publisher = book.getPublisher();

        List<Book> allBooks = bookRepository.findAll();
        for (Book bookWise: allBooks){
            if(bookWise.getIsbn().equals(isbn)){
                long prevCount = bookWise.getBookCount();
                long totalCount = prevCount+currCount;
                updateBook(bookWise,totalCount);
                return true;
            }
        }
         bookRepository.save(new Book(isbn,title,description,author,publisher,currCount));
         return true;
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    private void updateBook( Book book,long count) {
        book.setBookCount(count);
        bookRepository.save(book);
    }

    @Override
    public Book searchBook(String params) throws BookStoreException {
        List<Book> allBooks = bookRepository.findAll();
        for (Book book: allBooks){
            if(book.getIsbn().equals(params)){
                return book;
            }
            if(book.getTitle().matches("(.*)"+params+"(.*)")){
                return book;
            }
            if(book.getAuthor().matches("(.*)"+params+"(.*)")){
                return book;
            }
        }
        throw new BookStoreException("Book not found", BookStoreException.ExceptionType.BOOK_NOT_FOUND);
    }



    @Override
    public Book buyABook(String bookTitle,Long count) throws BookStoreException {
        Book book = bookRepository.findByTitle(bookTitle);
        long prevCount = book.getBookCount();
        if(prevCount<count){
            throw new BookStoreException("Book quantity is less than demand", BookStoreException.ExceptionType.Quantity_not_sufficient);
        }
        long totalCount = prevCount-count;
        updateBook(book,totalCount);
        if(totalCount == 0){
            updateBook(book,1);
        }
        return book;
    }

    @Override
    public List<String> searchMedia(String isbn) throws BookStoreException {
        System.out.println("Enter in first the method");
        List<String> aList = new ArrayList<>();
        Book book = bookRepository.findByIsbn(isbn);
        if(book==null){
            throw new BookStoreException("Book not available", BookStoreException.ExceptionType.BOOK_NOT_FOUND);
        }
        String title = book.getTitle();

        final String uri = "https://jsonplaceholder.typicode.com/posts";
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri,String.class);
        result = result.replace("\\n"," ");

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray)parser.parse(result);
            System.out.println(" array  "+array);
            for(int i=0;i<array.size();i++){
                Object titleMatch =  ((JSONObject)array.get(i)).get("title");
                Object bodyMatch = ((JSONObject)array.get(i)).get("body");
                if(title.equalsIgnoreCase(titleMatch.toString())){
                    aList.add(titleMatch.toString());
                }
                if(title.matches("(.*)"+bodyMatch.toString()+"(.*)")){
                    aList.add(titleMatch.toString());
                }
            }
        } catch (Exception e) {
        e.printStackTrace();
        }

        return aList;
    }
}
