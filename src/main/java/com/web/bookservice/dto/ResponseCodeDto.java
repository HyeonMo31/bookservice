package com.web.bookservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCodeDto {

    private int code;
    private String message;

    public ResponseCodeDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
