package com.web.bookservice.dto;

import com.web.bookservice.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {


    private String name;
    private String memberImage;
    private String loginId;
    private String city;
    private LocalDateTime createdDate;

    public MemberResponseDto() {}

    public MemberResponseDto(Member member) {
        this.name = member.getName();
        this.memberImage = member.getMemberImage().getStoreFileName();
        this.loginId = member.getLoginId();
        this.city = member.getCity();
        this.createdDate = member.getCreatedDate();
    }



}
