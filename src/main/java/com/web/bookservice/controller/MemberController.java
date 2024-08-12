package com.web.bookservice.controller;


import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.MemberResponseDto;
import com.web.bookservice.exception.MemberNotAuthenticatedException;
import com.web.bookservice.repository.MemberRepository;
import com.web.bookservice.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
    /* 현재 로그인한 사용자 정보
    */
    @GetMapping("/api/members")
    public MemberResponseDto findLoginMember(@AuthenticationPrincipal CustomMemberDetails member) {

         return memberService.findLoginMember(member);

     }

}
