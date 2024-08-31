package com.web.bookservice.controller;

import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.JoinDto;
import com.web.bookservice.dto.MemberResponseDto;
import com.web.bookservice.repository.FileStore;
import com.web.bookservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final FileStore fileStore;

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
     * multipart 데이터를 받을떄에는 requestBody는 적합하지 않다.
     * ModelAttribute 는 요청의 URL 쿼리 파라미터나 폼
     * 데이터(application/x-www-form-urlencoded, multipart/form-data)를 객체로 변환합니다.
     */

    @PatchMapping("/api/members")
    public ResponseEntity<MemberResponseDto> updateMember(@ModelAttribute JoinDto joinDto,
                                                          BindingResult result,
                                                          @AuthenticationPrincipal CustomMemberDetails member) throws IOException {
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> {
                System.out.println("Field: " + error.getField() + ", Error: " + error.getDefaultMessage());
            });
        }
        return ResponseEntity.ok(memberService.updateMember(joinDto, member));

    }

    @GetMapping("/files/{fileName}")
    public Resource getImage(@PathVariable("fileName")String fileName) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullDefaultPath(fileName));
    }


}
