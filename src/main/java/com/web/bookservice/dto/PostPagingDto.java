package com.web.bookservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class PostPagingDto {

    private Long id;
    private String postTitle;
    private String bookTitle;
    private String isbn;
    private String writer;
    private String createdDate;

    @QueryProjection
    public PostPagingDto(Long id, String postTitle, String bookTitle, String isbn,
                         String writer, LocalDateTime createdDate) {
        this.id = id;
        this.postTitle = postTitle;
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.writer = writer;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.createdDate = createdDate.format(formatter);
    }
}
