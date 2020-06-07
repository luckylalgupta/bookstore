package com.online.bookstore.controller;

import com.online.bookstore.dto.Book;
import com.online.bookstore.exception.BookStoreException;
import com.online.bookstore.service.IBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class BookController {
    @Autowired
   private IBookServiceImpl bookServiceImpl;

    @PostMapping("/add/{count}")
    public boolean addBook(@RequestBody Book book,@PathVariable(value = "count") long count) throws Exception{
        return bookServiceImpl.addBook(book,count);
    }
    @GetMapping("/get/{id}")
    public Book findByBookById(@PathVariable(value = "id") long id){
        return bookServiceImpl.getBookById(id);
    }

    @GetMapping("/search/{params}")
    public Book searchBookByISBNTitleAuthor(@PathVariable(value = "params") String params) throws BookStoreException {
        return bookServiceImpl.searchBook(params);
    }

    @GetMapping("/getallbooks")
    public List<Book> getAllBooks(){
        return bookServiceImpl.getAllBooks();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteByID(@PathVariable(value = "id") long id){
        bookServiceImpl.deleteBook(id);
        return "Delete Successfully";
    }

    @GetMapping("/getBook/{title}/{count}")
    public Book getBookByTitle(@PathVariable(value = "title") String title,@PathVariable(value = "count") long count) throws BookStoreException {
        System.out.println("title "+title+"  count "+count);
       return bookServiceImpl.getBookByTitle(title,count);
    }
    @GetMapping("/buyABook/{title}/{count}")
    public Book buyBookByTitle(@PathVariable(value = "title") String title,@PathVariable(value = "count") long count) throws BookStoreException {
        return bookServiceImpl.buyABook(title,count);
    }
    @GetMapping("/searchMedia/{isbn}")
    public List<String> searchMediaByIsbn(@PathVariable(value = "isbn") String isbn) throws BookStoreException {
       return bookServiceImpl.searchMedia(isbn);
    }
}
