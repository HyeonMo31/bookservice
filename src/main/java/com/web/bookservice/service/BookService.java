package com.web.bookservice.service;

import com.web.bookservice.domain.Book;
import com.web.bookservice.dto.BookDto;
import com.web.bookservice.exception.BookNotFoundException;
import com.web.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookDto findBookDetail(String isbn) {

        Book findBook = bookRepository.findByIsbn(isbn);

        if(findBook == null) {
            throw new BookNotFoundException();
        }

        BookDto bookDto = new BookDto(findBook);

        return bookDto;
    }

    public Map<String, BookDto> findTopBook() {


        Map<String, BookDto> map = new HashMap<>();

        Book reviewBook = bookRepository.findTopBookByReviewCount(Limit.of(1));
        if(reviewBook == null)
            map.put("review", new BookDto());
        else
            map.put("review", new BookDto(reviewBook));

        Book postBook = bookRepository.findTopBookByPostCount(Limit.of(1));
        if(postBook == null)
            map.put("post", new BookDto());
        else
            map.put("post", new BookDto(postBook));

        Book bookmarkBook = bookRepository.findTopBookByBookmarkCount(Limit.of(1));
        if(bookmarkBook == null)
            map.put("bookmark", new BookDto());
        else
            map.put("bookmark", new BookDto(bookmarkBook));

        return map;
    }

}
