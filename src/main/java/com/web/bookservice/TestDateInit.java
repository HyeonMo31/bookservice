package com.web.bookservice;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Post;
import com.web.bookservice.domain.Role;
import com.web.bookservice.repository.BookRepository;
import com.web.bookservice.repository.MemberRepository;
import com.web.bookservice.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDateInit {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PostRepository postRepository;
    private final BookRepository bookRepository;


    @PostConstruct
    public void testDataAdd() {

        Member member = new Member(bCryptPasswordEncoder.encode("31"),
                "thesting31", Role.USER, "정현모", "전주");

        Member member2 = new Member(bCryptPasswordEncoder.encode("31"),
                "thesting313", Role.USER, "정현모짜렐라", "전주");

        memberRepository.save(member);
        memberRepository.save(member2);


        for(int i = 0; i < 6; i++) {
            Book book = createBook(i);
            bookRepository.save(book);
            Post post = new Post(member, book, "테스트 게시판 제목입니다." + i,
                    "테스트 게시판 작성 내용 텍스트 입니다.입니다. 입니다.");
            postRepository.save(post);
        }

        for(int i = 6; i < 12; i++) {
            Book book = createBook(i);
            bookRepository.save(book);
            Post post = new Post(member2, book, "테스트 게시판 제목입니다." + i,
                    "테스트 게시판 작성 내용 텍스트 입니다.입니다. 입니다.");
            postRepository.save(post);
        }
    }

    public Book createBook(int i) {
        return new Book("테스트 제목 입니다. " + i, "i", "테스트 " + i , 10000,
                "테스트 출판사 " + i, LocalDate.now(), "1231312" + i, "테스트 텍스트");
    }
}
