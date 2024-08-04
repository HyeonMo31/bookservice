package com.web.bookservice.controller;

import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookMarkController {

    private final BookMarkService bookMarkService;

    /**
     * 즐겨찾기 여부 조회
     */
    @GetMapping("/api/books/{isbn}/bookmarks")
    public boolean findBookMark(@PathVariable("isbn")String isbn,
                                       @AuthenticationPrincipal CustomMemberDetails member) {
        return bookMarkService.findBookMark(isbn, member);
    }

    /**
     * 즐겨찾기 등록
     */
    @PostMapping("/api/books/{isbn}/bookmarks")
    public ResponseCodeDto addBookMark(@PathVariable("isbn")String isbn,
                                       @AuthenticationPrincipal CustomMemberDetails member) {

        return bookMarkService.addBookMark(isbn, member);
    }



}
