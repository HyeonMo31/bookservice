package com.web.bookservice.exception;

import com.web.bookservice.dto.ResponseCodeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.web.bookservice.exception.ErrorMessage.BOOK_NOT_FOUND;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity bookNotFound(BookNotFoundException e) {
        ResponseCodeDto response = new ResponseCodeDto(HttpStatus.NOT_FOUND.value(), BOOK_NOT_FOUND.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberNotAuthenticatedException.class)
    public ResponseEntity memberNotAuth(MemberNotAuthenticatedException e) {
        ResponseCodeDto response = new ResponseCodeDto(HttpStatus.UNAUTHORIZED.value(),  e.getMessage());
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }



}
