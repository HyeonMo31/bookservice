package com.web.bookservice.dto;

import com.web.bookservice.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {

    public MemberResponseDto() {}

    public MemberResponseDto(Member member) {
        this.name = member.getName();
        this.loginId = member.getLoginId();
        this.city = member.getCity();
        this.createdDate = member.getCreatedDate();
    }

    private String name;
    private String loginId;
    private String city;
    private LocalDateTime createdDate;

}
