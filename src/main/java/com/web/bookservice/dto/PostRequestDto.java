package com.web.bookservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequestDto {

    private String loginId;
    private String isbn;
    private String title;
    private String text;

}
