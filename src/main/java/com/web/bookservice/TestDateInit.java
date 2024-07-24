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

        Member member = new Member();
        member.setLoginId("thesting31");
        member.setName("정현모");
        member.setPassword(bCryptPasswordEncoder.encode("31"));
        member.setCity("전주");
        member.setJoinDate(LocalDateTime.now());
        member.setRole(Role.USER);

        memberRepository.save(member);
    }
}
