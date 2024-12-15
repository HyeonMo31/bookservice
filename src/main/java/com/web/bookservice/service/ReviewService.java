package com.web.bookservice.service;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Review;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.dto.ReviewCommentResponseDto;
import com.web.bookservice.dto.ReviewCommentRequestDto;
import com.web.bookservice.exception.BookNotFoundException;
import com.web.bookservice.exception.MemberNotAuthenticatedException;
import com.web.bookservice.repository.BookRepository;
import com.web.bookservice.repository.FileStore;
import com.web.bookservice.repository.MemberRepository;
import com.web.bookservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.web.bookservice.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public ReviewCommentResponseDto save(String isbn, ReviewCommentRequestDto request, CustomMemberDetails member) {

        Book findBook = bookRepository.findByIsbn(isbn);

        if(findBook == null)
            throw new BookNotFoundException();
        if(member == null)
            throw new MemberNotAuthenticatedException(MEMBER_NOT_AUTHORIZED.getMessage());

        Member findMember = memberRepository.findByLoginId(member.getUsername());
        Review review = new Review(findMember, findBook, request.getText());

        Review savedReview = reviewRepository.save(review);

        return new ReviewCommentResponseDto(savedReview.getId(), findMember.getMemberImage().getStoreFileName(),
                findMember.getLoginId(), findMember.getName(), request.getText(), savedReview.getCreatedDate(), null);

    }

    public ResponseCodeDto deleteReview(String isbn, Long reviewId, CustomMemberDetails member) {

        Book findBook = bookRepository.findByIsbn(isbn);

        if(findBook == null)
            throw new BookNotFoundException();
        if(member == null)
            throw new MemberNotAuthenticatedException(MEMBER_NOT_AUTHORIZED.getMessage());

        Member findMember = memberRepository.findByLoginId(member.getUsername());

        Optional<Review> review = reviewRepository.findById(reviewId);

        reviewRepository.delete(review.get());

        return new ResponseCodeDto(200, "리뷰가 삭제 되었습니다.");
    }

    public List<ReviewCommentResponseDto> findReviewsByIsbn(String isbn) throws MalformedURLException {

        Book book = bookRepository.findByIsbn(isbn);

        List<ReviewCommentResponseDto> reviewCommentResponseDtoList = new ArrayList<>();
        List<Review> findReview = reviewRepository.findByBook(book);

        for(Review review : findReview) {

            ReviewCommentResponseDto reviewCommentResponseDto =
                    new ReviewCommentResponseDto(review.getId(),
                            review.getMember().getMemberImage().getStoreFileName(),
                            review.getMember().getLoginId(),
                            review.getMember().getName(),
                            review.getText(),
                            review.getCreatedDate(), null);
            reviewCommentResponseDtoList.add(reviewCommentResponseDto);
        }
        return reviewCommentResponseDtoList;

    }

}
