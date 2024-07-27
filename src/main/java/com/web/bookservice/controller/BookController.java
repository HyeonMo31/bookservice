package com.web.bookservice.controller;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Review;
import com.web.bookservice.dto.BookDetailResponseDto;
import com.web.bookservice.dto.BookDto;
import com.web.bookservice.dto.NaverBookResponseDto;
import com.web.bookservice.service.BookService;
import com.web.bookservice.service.NaverBookService;
import com.web.bookservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final NaverBookService naverBookService;
    private final BookService bookService;
    private final ReviewService reviewService;

    @GetMapping("/api/books")
    public ResponseEntity<?> findBooks(@RequestParam("query")String query,
                                        @RequestParam(defaultValue = "1", name = "page")int pageNum,
                                      @RequestParam(defaultValue = "sim", name = "sort")String sort) {

        NaverBookResponseDto response = naverBookService.searchBooks(query, pageNum, sort);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/api/books/{isbn}")
    public ResponseEntity<?> findBookDetail(@PathVariable(name = "isbn")String isbn) {

        BookDetailResponseDto response = bookService.getBookDetailResponseDto(isbn);

        return ResponseEntity.ok(response);

    }


}
