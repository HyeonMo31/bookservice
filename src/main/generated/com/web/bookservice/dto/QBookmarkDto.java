package com.web.bookservice.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.bookservice.dto.QBookmarkDto is a Querydsl Projection type for BookmarkDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBookmarkDto extends ConstructorExpression<BookmarkDto> {

    private static final long serialVersionUID = -1052156719L;

    public QBookmarkDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> author, com.querydsl.core.types.Expression<String> publisher, com.querydsl.core.types.Expression<java.time.LocalDate> pubdate, com.querydsl.core.types.Expression<String> isbn) {
        super(BookmarkDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDate.class, String.class}, id, title, author, publisher, pubdate, isbn);
    }

}

