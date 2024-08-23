package com.web.bookservice.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.bookservice.dto.QPostDto_BookInfo is a Querydsl Projection type for BookInfo
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostDto_BookInfo extends ConstructorExpression<PostDto.BookInfo> {

    private static final long serialVersionUID = 352431166L;

    public QPostDto_BookInfo(com.querydsl.core.types.Expression<String> image, com.querydsl.core.types.Expression<String> bookTitle, com.querydsl.core.types.Expression<String> author, com.querydsl.core.types.Expression<java.time.LocalDate> pubdate, com.querydsl.core.types.Expression<String> isbn) {
        super(PostDto.BookInfo.class, new Class<?>[]{String.class, String.class, String.class, java.time.LocalDate.class, String.class}, image, bookTitle, author, pubdate, isbn);
    }

}

