package com.web.bookservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Comment;
import com.web.bookservice.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PostDto {

    private Long id;
    private String memberImage;
    private String name;
    private String loginId;
    private String createdDate;
    private String postTitle;
    private String text;

    private BookInfo bookInfo;

    private List<ReviewCommentResponseDto> commentsDto = new ArrayList<>();

//    @QueryProjection
    public PostDto(Long id, String memberImage, String name, String loginId,
                   LocalDateTime createdDate, String postTitle, String text,
                   BookInfo bookInfo) {
        this.id = id;
        this.memberImage = memberImage;
        this.name = name;
        this.loginId = loginId;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.createdDate = createdDate.format(formatter);
        this.postTitle = postTitle;
        this.text = text;
        this.bookInfo = bookInfo;
    }

    //QueryDsl을 사용할때 inner클래스는 static 클래스어야 한다.
//    내부 클래스는 외부 클래스의 인스턴스가 필요하고, 이는 QueryDSL이 리플렉션을 통해 생성자를 호출할 때 문제가 됩니다.
//    static 클래스로 선언하면, 내부 클래스는 외부 클래스와 독립적으로 동작하므로 QueryDSL이 문제 없이 생성자를 호출할 수 있습니다.
    @Getter @Setter
    public static class BookInfo {

        private String image;
        private String bookTitle;
        private String author;
        private LocalDate pubdate;
        private String isbn;

//        @QueryProjection
        public BookInfo(String image, String bookTitle, String author, LocalDate pubdate, String isbn) {
            this.image = image;
            this.bookTitle = bookTitle;
            this.author = author;
            this.pubdate = pubdate;
            this.isbn = isbn;
        }
    }


}
