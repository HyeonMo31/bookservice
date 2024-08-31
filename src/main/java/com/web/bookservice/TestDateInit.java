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


    @PostConstruct
    public void testDataAdd() {





    }


}


