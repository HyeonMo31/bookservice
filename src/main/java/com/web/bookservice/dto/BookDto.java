package com.web.bookservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.web.bookservice.domain.Book;
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

    public BookDto(Book book) {
        this.title = book.getTitle();
        this.image = book.getImage();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.publisher = book.getPublisher();
        this.pubdate = book.getPubdate();
        this.isbn = book.getIsbn();
        this.description = book.getDescription();
    }
}
