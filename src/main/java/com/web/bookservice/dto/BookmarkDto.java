package com.web.bookservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class BookmarkDto {

    Long id;
    String bookTitle;
    String author;
    String publisher;
    LocalDate pubdate;
    String isbn;

    @QueryProjection
    public BookmarkDto(Long id, String bookTitle, String author, String publisher, LocalDate pubdate, String isbn) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.isbn = isbn;
    }
}
