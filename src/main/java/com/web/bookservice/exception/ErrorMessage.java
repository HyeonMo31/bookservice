package com.web.bookservice.exception;

import lombok.Getter;

public enum ErrorMessage {

    MEMBER_NOT_AUTHORIZED("로그인 되어 있지 않습니다."),
    BOOK_NOT_FOUND("책을 찾을 수 없습니다."),
    POST_NOT_FOUND("글이 존재하지 않습니다.");


    @Getter
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
