package com.web.bookservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class ReviewCommentResponseDto {

    @QueryProjection
    public ReviewCommentResponseDto(Long id, String loginId, String name, String text, LocalDateTime createdDate) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.text = text;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.createdDate = createdDate.format(formatter);
    }

    private Long id;
    private String loginId;
    private String name;
    private String text;
    private String createdDate;
}
