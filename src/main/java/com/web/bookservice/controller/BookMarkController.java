package com.web.bookservice.controller;

import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.MsgResponseDto;
import com.web.bookservice.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookMarkController {

    private final BookMarkService bookMarkService;

    /**
     * 즐겨찾기 조회
     */
    @GetMapping("/api/books/{isbn}/bookmarks")
    public MsgResponseDto findBookMark(@PathVariable("isbn")String isbn,
                                       @AuthenticationPrincipal CustomMemberDetails member) {
        return bookMarkService.findBookMark(isbn, member);
    }

    /**
     * 즐겨찾기 등록
     */
    @PostMapping("/api/books/{isbn}/bookmarks")
    public ResponseEntity addBookMark(@PathVariable("isbn")String isbn,
                                      @AuthenticationPrincipal CustomMemberDetails member) {
        try {
            bookMarkService.addBookMark(isbn, member);
        }
        catch (UsernameNotFoundException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
        }
        return ResponseEntity.ok("즐겨찾기 완료");
    }



}
