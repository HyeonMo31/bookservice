package com.web.bookservice.repository;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByBook(Book book);

}
