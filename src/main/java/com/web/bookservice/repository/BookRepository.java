package com.web.bookservice.repository;

import com.web.bookservice.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

}
