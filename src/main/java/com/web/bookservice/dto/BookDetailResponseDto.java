package com.web.bookservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class BookDetailResponseDto {

    private BookDto bookDto;
    List<ReviewCommentDto> reviewCommnetDtoList = new ArrayList<>();

}
