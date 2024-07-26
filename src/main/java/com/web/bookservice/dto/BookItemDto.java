package com.web.bookservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookItemDto {

    private String title;
    private String image;
    private String author;
    private int price;
    private String publisher;
    private LocalDate pubdate;
    private String isbn;
    private String description;



}
