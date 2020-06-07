package com.online.bookstore.repository;

import com.online.bookstore.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByIsbn(String isbn);
    Book findByTitle(String bookTitle);
    Book findById(long id);
}
