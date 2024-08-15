package com.web.bookservice.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.bookservice.dto.QPostDto is a Querydsl Projection type for PostDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostDto extends ConstructorExpression<PostDto> {

    private static final long serialVersionUID = 563674407L;

    public QPostDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> postTitle, com.querydsl.core.types.Expression<String> bookTitle, com.querydsl.core.types.Expression<String> writer, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdDate) {
        super(PostDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, id, postTitle, bookTitle, writer, createdDate);
    }

}

