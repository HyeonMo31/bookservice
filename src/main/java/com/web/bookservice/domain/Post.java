package com.web.bookservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
//    @NotNull(message = "책을 필수로 선택하십시오.")
    private Book book;

//    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

//    @NotBlank(message = "텍스트를 입력해주세요.")
    @Column(columnDefinition = "TEXT")
    private String text;

    @CreatedDate
    private LocalDateTime createdDate;

    public Post(Member member, Book book, String title, String text) {
        this.member = member;
        this.book = book;
        this.title = title;
        this.text = text;
    }

    public void updatePost(String title, String text) {
        this.title = title;
        this.text = text;
    }


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

}
