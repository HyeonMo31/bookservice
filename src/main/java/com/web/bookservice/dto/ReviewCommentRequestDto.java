package com.web.bookservice.dto;

import lombok.Getter;

@Getter
public class ReviewCommentRequestDto {

    private Long parentId;
    private String text;

}
