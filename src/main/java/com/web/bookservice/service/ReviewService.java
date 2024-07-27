package com.web.bookservice.service;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Review;
import com.web.bookservice.dto.ReviewCommentDto;
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

    public List<ReviewCommentDto> findByBook(Book book) {

        List<ReviewCommentDto> reviewCommentDtoList = new ArrayList<>();
        List<Review> findReview = reviewRepository.findByBook(book);


        for(Review review : findReview) {
            ReviewCommentDto reviewCommentDto = new ReviewCommentDto();
            reviewCommentDto.setText(review.getText());
            reviewCommentDto.setName(review.getMember().getName());
            reviewCommentDto.setWriteDate(review.getWriteDate());
            reviewCommentDto.setLoginId(review.getMember().getLoginId());
            reviewCommentDtoList.add(reviewCommentDto);
        }
        return reviewCommentDtoList;
    }

}
