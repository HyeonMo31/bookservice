package com.web.bookservice.exception;

import org.springframework.security.core.AuthenticationException;

public class MemberNotAuthenticatedException extends AuthenticationException {

    public MemberNotAuthenticatedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public MemberNotAuthenticatedException(String msg) {
        super(msg);
    }
}
