package com.web.bookservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReviewCommentDto {

    private String loginId;
    private String name;
    private String text;
    private LocalDateTime writeDate;
}
