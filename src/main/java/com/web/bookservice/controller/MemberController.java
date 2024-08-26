package com.web.bookservice.controller;


import com.web.bookservice.domain.Member;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.JoinDto;
import com.web.bookservice.dto.MemberResponseDto;
import com.web.bookservice.exception.MemberNotAuthenticatedException;
import com.web.bookservice.repository.MemberRepository;
import com.web.bookservice.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
    /* 현재 로그인한 사용자 정보
    */
    @GetMapping("/api/members")
    public MemberResponseDto findLoginMember(
            @AuthenticationPrincipal CustomMemberDetails member) {

         return memberService.findLoginMember(member);

     }

    /**
     * 사용자 수정
     */
    @PatchMapping("/api/members")
    public ResponseEntity<MemberResponseDto> updateMember(@RequestBody JoinDto joinDto,
                                                          @AuthenticationPrincipal CustomMemberDetails member) {

        return ResponseEntity.ok(memberService.updateMember(joinDto, member));

    }


}
