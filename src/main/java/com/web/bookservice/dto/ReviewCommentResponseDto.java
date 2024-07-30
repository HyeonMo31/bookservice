package com.web.bookservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReviewCommentResponseDto {

    public ReviewCommentResponseDto(Long id, String loginId, String name, String text, LocalDateTime writeDate) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.text = text;
        this.writeDate = writeDate;
    }

    private Long id;
    private String loginId;
    private String name;
    private String text;
    private LocalDateTime writeDate;
}
