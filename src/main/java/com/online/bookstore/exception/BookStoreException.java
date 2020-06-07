package com.online.bookstore.exception;

public class BookStoreException extends Exception {

   public enum ExceptionType{
        BOOK_NOT_FOUND,
        BOOK_NOT_UPDATED,
        NOT_ABLE_BOOK,
        Quantity_not_sufficient;
   }
    ExceptionType type;

    public BookStoreException(String message,ExceptionType type) {
        super(message);
        this.type=type;
    }
}
