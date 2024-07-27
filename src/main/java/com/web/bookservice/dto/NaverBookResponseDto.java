package com.web.bookservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NaverBookResponseDto {


    private int total;
    private List<BookDto> items = new ArrayList<>();
}
