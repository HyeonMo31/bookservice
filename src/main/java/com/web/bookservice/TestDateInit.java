package com.web.bookservice;

import com.web.bookservice.domain.*;
import com.web.bookservice.dto.NaverBookResponseDto;
import com.web.bookservice.repository.*;
import com.web.bookservice.service.NaverBookService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TestDateInit {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PostRepository postRepository;
    private final BookRepository bookRepository;
    private final NaverBookService naverBookService;
    private final BookMarkRepository bookMarkRepository;
    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;


//    @PostConstruct
//    public void testDataAdd() {
//
//        Member member = new Member(bCryptPasswordEncoder.encode("31"),
//                "thesting31", Role.USER, "정현모", "전주", new UploadFile("tuna.jpg", "tuna.jpg"));
//
//        Member member2 = new Member(bCryptPasswordEncoder.encode("31"),
//                "thesting313", Role.USER, "정현모짜렐라", "전주", new UploadFile("tuna.jpg", "tuna.jpg"));
//
//        memberRepository.save(member);
//        memberRepository.save(member2);
//
//        for (int i = 1; i <= 14; i++) {
//            naverBookService.searchBooks("도서관", i, "sim");
//        }
//
//        // Save posts and bookmarks for the first range of books
//        for (int i = 1; i < 64; i++) {
//            Book book = findBook(i).orElseGet(() ->
//                    new Book("테스트 제목 입니다. ",
//                            "https://shopping-phinf.pstatic.net/main_3246655/32466555089.20221228071854.jpg",
//                            "테스트 ", 10000,
//                            "테스트 출판사 ", LocalDate.now(),
//                            "1231312", "테스트 텍스트"));
//
//            Post post = new Post(member, book, book.getTitle() + "이란 무엇일까", book.getDescription());
//            postRepository.save(post);
//            reviewRepository.save(new Review(member2, book, i + " 번째 리뷰입니다."));
//            commentRepository.save(new Comment(post, member, "재밌는 글입니다."));
//            commentRepository.save(new Comment(post, member2, "재미없는 글입니다. 후후"));
//        }
//
//
//        // Save posts and bookmarks for the second range of books
//        for (int i = 64; i < 128; i++) {
//            Book book = findBook(i).orElseGet(() ->
//                    new Book("테스트 제목 입니다. ",
//                            "https://shopping-phinf.pstatic.net/main_3246655/32466555089.20221228071854.jpg",
//                            "테스트 ", 10000,
//                            "테스트 출판사 ", LocalDate.now(),
//                            "1231312", "테스트 텍스트"));
//            bookMarkRepository.save(new Bookmark(book, member));
//            Post post = new Post(member2, book, book.getTitle() + "이란 무엇일까", book.getDescription());
//            postRepository.save(post);
//            bookMarkRepository.save(new Bookmark(book, member2));
//
//        }
//
//    }
//
//        public Optional<Book> findBook(int i) {
//                return bookRepository.findById(Long.valueOf(i));
//        //        return new Book("테스트 제목 입니다. " + i,
//        //                "https://shopping-phinf.pstatic.net/main_3246655/32466555089.20221228071854.jpg", "테스트 " + i , 10000,
//        //                "테스트 출판사 " + i, LocalDate.now(), "1231312" + i, "테스트 텍스트");
//        }
}


