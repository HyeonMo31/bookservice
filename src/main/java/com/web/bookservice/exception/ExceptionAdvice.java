package com.web.bookservice.exception;

import com.web.bookservice.dto.ResponseCodeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jndi.TypeMismatchNamingException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.web.bookservice.exception.ErrorMessage.*;
import static com.web.bookservice.exception.ErrorMessage.BOOK_NOT_FOUND;
import static com.web.bookservice.exception.ErrorMessage.POST_NOT_FOUND;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity bookNotFound(BookNotFoundException e) {
        ResponseCodeDto response = new ResponseCodeDto(HttpStatus.NOT_FOUND.value(), BOOK_NOT_FOUND.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity postNotFound(PostNotFoundException e) {
        ResponseCodeDto response = new ResponseCodeDto(HttpStatus.NOT_FOUND.value(), POST_NOT_FOUND.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberNotAuthenticatedException.class)
    public ResponseEntity memberNotAuth(MemberNotAuthenticatedException e) {
        ResponseCodeDto response = new ResponseCodeDto(HttpStatus.UNAUTHORIZED.value(),  e.getMessage());
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordBadRequestException.class)
    public ResponseEntity memberBadPassword(PasswordBadRequestException e) {
        ResponseCodeDto response = new ResponseCodeDto(HttpStatus.BAD_REQUEST.value(),  PASSWORD_BAD_REQUEST.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageTypeMisException.class)
    public ResponseEntity memberBadImage(ImageTypeMisException e) {
        ResponseCodeDto response = new ResponseCodeDto(HttpStatus.BAD_REQUEST.value(), IMAGE_TYPE_MIS.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }




}
