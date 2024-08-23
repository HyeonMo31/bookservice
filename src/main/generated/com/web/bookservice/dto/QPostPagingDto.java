package com.web.bookservice.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.bookservice.dto.QPostPagingDto is a Querydsl Projection type for PostPagingDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostPagingDto extends ConstructorExpression<PostPagingDto> {

    private static final long serialVersionUID = 1995578363L;

    public QPostPagingDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> postTitle, com.querydsl.core.types.Expression<String> bookTitle, com.querydsl.core.types.Expression<String> isbn, com.querydsl.core.types.Expression<String> writer, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdDate) {
        super(PostPagingDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, id, postTitle, bookTitle, isbn, writer, createdDate);
    }

}

