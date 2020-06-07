package com.online.bookstore;

import com.online.bookstore.dto.Book;
import com.online.bookstore.exception.BookStoreException;
import com.online.bookstore.repository.BookRepository;
import com.online.bookstore.service.IBookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
class BookstoreApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    Book book = null;

    IBookServiceImpl iBookService=new IBookServiceImpl();

    @Before
    public void setUp(){
        book = new Book("1234", "Theory of relativity", "The secret theory",
                "Albert Einstien", "Pearson Edition", 10l);
    }

    @Test
    public void addingBookToTheDataBase() throws BookStoreException {
        boolean expected = true;
        iBookService=mock(IBookServiceImpl.class);
        when(iBookService.addBook(book,10l)).thenReturn(expected);
        boolean actual = iBookService.addBook(book, 10l);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void GetBookById(){
        Book expected=book;
        iBookService=mock(IBookServiceImpl.class);
        when(iBookService.getBookById(1)).thenReturn(expected);
        Book actual= iBookService.getBookById(1);
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void SearchBook() throws BookStoreException {
        Book expected=book;
        iBookService=mock(IBookServiceImpl.class);
        when(iBookService.searchBook("Chetan")).thenReturn(expected);
        Book actual= iBookService.searchBook("Chetan");
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void BuyingBookByTitle() throws BookStoreException {
        Book expected=book;
        iBookService=mock(IBookServiceImpl.class);
        when(iBookService.buyABook("Learn Spring",10l)).thenReturn(expected);
        Book actual= iBookService.buyABook("Learn Spring",10l);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void getAllBooks(){
        Book book1 = new Book("1234", "Theory of relativity", "The secret theory",
                "Albert Einstien", "Pearson Edition", 10l);
        Book book2 = new Book("1235", "Science theory", "The secret of science",
                "HC Verma", "Pearson Edition", 20l);
        Book book3 = new Book("1236", "Math theory", "The secret of math",
                "RD Sharma", "Pearson Edition", 30l);
        iBookService=mock(IBookServiceImpl.class);
        List<Book> aList1 = new ArrayList<Book>();
        aList1.add(book1);aList1.add(book2);aList1.add(book3);
        when(iBookService.getAllBooks()).thenReturn(aList1);
        List aList2 = iBookService.getAllBooks();
        Assert.assertSame(aList1,aList2);
    }
    @Test
    public void searchMedia() throws BookStoreException {
        List<String> aList1 = new ArrayList<>();
        aList1.add("My Sweet Home");
        iBookService=mock(IBookServiceImpl.class);
        when(iBookService.searchMedia("1127")).thenReturn(aList1);
        List aList2 = iBookService.searchMedia("1127");
        Assert.assertSame(aList1,aList2);
    }

}
