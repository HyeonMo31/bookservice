package com.web.bookservice.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.bookservice.dto.QReviewCommentResponseDto is a Querydsl Projection type for ReviewCommentResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewCommentResponseDto extends ConstructorExpression<ReviewCommentResponseDto> {

    private static final long serialVersionUID = 620277871L;

    public QReviewCommentResponseDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> memberImage, com.querydsl.core.types.Expression<String> loginId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> text, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdDate) {
        super(ReviewCommentResponseDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, id, memberImage, loginId, name, text, createdDate);
    }

}

