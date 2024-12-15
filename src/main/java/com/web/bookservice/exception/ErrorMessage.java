package com.web.bookservice.exception;

import lombok.Getter;

public enum ErrorMessage {

    MEMBER_NOT_AUTHORIZED("로그인 되어 있지 않습니다."),
    BOOK_NOT_FOUND("책을 찾을 수 없습니다."),
    POST_NOT_FOUND("글이 존재하지 않습니다."),
    PASSWORD_BAD_REQUEST("비밀번호가 일치하지 않습니다."),
    IMAGE_TYPE_MIS("사진은 jpg, gif, png 파일만 가능합니다."),
    COMMENT_NOT_FOUND("댓글이 존재하지 않습니다.");


    @Getter
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
