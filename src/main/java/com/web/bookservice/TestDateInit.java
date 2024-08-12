package com.web.bookservice;

import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Role;
import com.web.bookservice.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestDateInit {

    private MemberRepository memberRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public TestDateInit(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void testDataAdd() {

        Member member = new Member(bCryptPasswordEncoder.encode("31"),
                "thesting31", Role.USER, "정현모", "전주");
        memberRepository.save(member);
    }
}
