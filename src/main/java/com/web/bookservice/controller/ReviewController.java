package com.web.bookservice.controller;


import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.dto.ReviewCommentResponseDto;
import com.web.bookservice.dto.ReviewRequestDto;
import com.web.bookservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 조회
     */
    @GetMapping("/api/books/{isbn}/reviews")
    public List<ReviewCommentResponseDto> findAllReviews(@PathVariable("isbn")String isbn) {
        return reviewService.findReviewsByIsbn(isbn);
    }

    /**
     * 리뷰 등록
     */
    @PostMapping("/api/books/{isbn}/reviews")
    public ResponseEntity<ReviewCommentResponseDto> addReview(@PathVariable("isbn")String isbn,
                                                              @RequestBody ReviewRequestDto request,
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
