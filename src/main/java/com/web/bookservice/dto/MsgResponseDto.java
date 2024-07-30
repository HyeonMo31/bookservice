package com.web.bookservice.dto;

import lombok.Getter;

@Getter
public class MsgResponseDto {
    public MsgResponseDto(String msg) {
        this.msg = msg;
    }

    private String msg;

}
