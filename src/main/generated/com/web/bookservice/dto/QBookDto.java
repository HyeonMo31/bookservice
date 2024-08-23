package com.web.bookservice.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.bookservice.dto.QBookDto is a Querydsl Projection type for BookDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBookDto extends ConstructorExpression<BookDto> {

    private static final long serialVersionUID = 1019562558L;

    public QBookDto(com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> image, com.querydsl.core.types.Expression<String> author, com.querydsl.core.types.Expression<java.time.LocalDate> pubdate, com.querydsl.core.types.Expression<String> isbn) {
        super(BookDto.class, new Class<?>[]{String.class, String.class, String.class, java.time.LocalDate.class, String.class}, title, image, author, pubdate, isbn);
    }

}

