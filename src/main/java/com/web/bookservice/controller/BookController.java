package com.web.bookservice.controller;

import com.web.bookservice.dto.*;
import com.web.bookservice.service.BookService;
import com.web.bookservice.service.NaverBookService;
import com.web.bookservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final NaverBookService naverBookService;
    private final ReviewService reviewService;
    private final BookService bookService;

    /**
     * 네이버 Book API 결과 조회
     */
    @GetMapping("/api/books")
    public ResponseEntity<?> findBooks(@RequestParam("query")String query,
                                        @RequestParam(defaultValue = "1", name = "page")int pageNum,
                                      @RequestParam(defaultValue = "sim", name = "orderBy")String orderBy) {

        NaverBookResponseDto response = naverBookService.searchBooks(query, pageNum, orderBy);
        return ResponseEntity.ok(response);

    }

    /**
     * DB, ISBN을 통한 책 결과 조회
     */
    @GetMapping("/api/books/{isbn}")
    public BookDto findBookDetail(@PathVariable(name = "isbn")String isbn) {

        return bookService.findBookDetail(isbn);

    }

    /**
     * DB, 리뷰 많은 순, 언급 1등, 즐겨찾기 1등 책 DB 조회
     */
    @GetMapping("/api/books/top")
    public ResponseEntity<Map<String, BookDto>> findTopBook() {
        return ResponseEntity.ok(bookService.findTopBook());
    }

    /**
     * 리뷰 조회
     */
    @GetMapping("/api/books/{isbn}/reviews")
    public List<ReviewCommentResponseDto> findAllReviews(@PathVariable("isbn")String isbn) throws MalformedURLException {
        return reviewService.findReviewsByIsbn(isbn);
    }

    /**
     * 리뷰 등록
     */
    @PostMapping("/api/books/{isbn}/reviews")
    public ResponseEntity<ReviewCommentResponseDto> addReview(@PathVariable("isbn")String isbn,
                                                              @RequestBody ReviewCommentRequestDto request,
                                                              @AuthenticationPrincipal CustomMemberDetails member) {
        return ResponseEntity.ok(reviewService.save(isbn, request, member));
    }

    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/api/books/{isbn}/reviews/{reviewId}")
    public ResponseEntity<ResponseCodeDto> deleteReview(@PathVariable("isbn")String isbn,
                                                        @PathVariable("reviewId")Long reviewId,
                                                        @AuthenticationPrincipal CustomMemberDetails member) {
        return ResponseEntity.ok(reviewService.deleteReview(isbn, reviewId, member));
    }


}
