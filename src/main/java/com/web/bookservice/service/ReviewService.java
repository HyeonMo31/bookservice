package com.web.bookservice.service;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Review;
import com.web.bookservice.dto.ReviewCommentResponseDto;
import com.web.bookservice.dto.ReviewRequestDto;
import com.web.bookservice.repository.BookRepository;
import com.web.bookservice.repository.MemberRepository;
import com.web.bookservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public ReviewCommentResponseDto save(String isbn, ReviewRequestDto request, String loginId) {

        Member member = memberRepository.findByLoginId(loginId);
        Book book = bookRepository.findByIsbn(isbn);
        Review review = new Review();

        review.setBook(book);
        review.setText(request.getText());
        review.setWriteDate(LocalDateTime.now());
        review.setMember(member);
        reviewRepository.save(review);

        return new ReviewCommentResponseDto(loginId, member.getName(), request.getText(), LocalDateTime.now());

    }

    public List<ReviewCommentResponseDto> findReviewsByIsbn(String isbn) {

        Book book = bookRepository.findByIsbn(isbn);

        List<ReviewCommentResponseDto> reviewCommentResponseDtoList = new ArrayList<>();
        List<Review> findReview = reviewRepository.findByBook(book);

        for(Review review : findReview) {
            ReviewCommentResponseDto reviewCommentResponseDto =
                    new ReviewCommentResponseDto(review.getMember().getLoginId(),
                            review.getMember().getName(),
                            review.getText(),
                            review.getWriteDate());
            reviewCommentResponseDtoList.add(reviewCommentResponseDto);
        }
        return reviewCommentResponseDtoList;

    }

}
