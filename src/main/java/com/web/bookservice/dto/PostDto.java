package com.web.bookservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class PostDto {

    private Long id;
    private String postTitle;
    private String bookTitle;
    private String writer;
    private LocalDateTime createdBy;

    @QueryProjection
    public PostDto(Long id, String postTitle, String bookTitle, String writer, LocalDateTime createdBy) {
        this.id = id;
        this.postTitle = postTitle;
        this.bookTitle = bookTitle;
        this.writer = writer;
        this.createdBy = createdBy;
    }
}
