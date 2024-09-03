package com.web.bookservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class PostLatestDto {

    private Long id;
    private String postTitle;
    private String name;
    private String createdDate;
    private String bookImage;

    public PostLatestDto(Long id, String postTitle, String name, LocalDateTime createdDate, String bookImage) {
        this.id = id;
        this.postTitle = postTitle;
        this.name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.createdDate = createdDate.format(formatter);
        this.bookImage = bookImage;
    }
}
