package com.web.bookservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String image;
    private String author;
    private int price;
    private String publisher;
    private LocalDate pubdate;
    private String isbn;
    //      Column 애너테이션의 length를 설정하지 않으면, default로 CHARACTER VARYING(255)로 설정되는 것 같다.
    // 그래서 디스크립션에서 255 초과했다고 오류가 났다.
    //해결법은 도메인에서  columnDefinition = "TEXT 추가
    @Column(columnDefinition = "TEXT")
    private String description;

    public Book(String title, String image, String author, int price, String publisher, LocalDate pubdate, String isbn, String description) {
        this.title = title;
        this.image = image;
        this.author = author;
        this.price = price;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.isbn = isbn;
        this.description = description;
    }
}
