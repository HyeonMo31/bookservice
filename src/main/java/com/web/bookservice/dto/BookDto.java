package com.web.bookservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDto {

    private String title;
    private String image;
    private String author;
    private int price;
    private String publisher;
    private LocalDate pubdate;
    private String isbn;
    private String description;

    public BookDto() {}

    @QueryProjection
    public BookDto(String title, String image, String author, LocalDate pubdate, String isbn) {
        this.title = title;
        this.image = image;
        this.author = author;
        this.pubdate = pubdate;
        this.isbn = isbn;
    }
}
