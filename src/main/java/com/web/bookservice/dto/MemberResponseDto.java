package com.web.bookservice.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {

    public MemberResponseDto() {}

    public MemberResponseDto(String name, String loginId, String city, LocalDateTime joinDate) {
        this.name = name;
        this.loginId = loginId;
        this.city = city;
        this.joinDate = joinDate;
    }

    private String name;
    private String loginId;
    private String city;
    private LocalDateTime joinDate;

}
