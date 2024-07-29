package com.web.bookservice.controller;

import com.web.bookservice.dto.BookDto;
import com.web.bookservice.dto.NaverBookResponseDto;
import com.web.bookservice.service.BookService;
import com.web.bookservice.service.NaverBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final NaverBookService naverBookService;
    private final BookService bookService;

    /**
     * 네이버 Book API 결과 조회
     */
    @GetMapping("/api/books")
    public ResponseEntity<?> findBooks(@RequestParam("query")String query,
                                        @RequestParam(defaultValue = "1", name = "page")int pageNum,
                                      @RequestParam(defaultValue = "sim", name = "sort")String sort) {

        NaverBookResponseDto response = naverBookService.searchBooks(query, pageNum, sort);

        return ResponseEntity.ok(response);

    }

    /**
     * DB, ISBN을 통한 책 결과 조회
     */
    @GetMapping("/api/books/{isbn}")
    public BookDto findBookDetail(@PathVariable(name = "isbn")String isbn) {

        return bookService.findBookDetail(isbn);

    }


}
