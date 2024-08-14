package com.web.bookservice.controller;

import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookMarkController {

    private final BookMarkService bookMarkService;

    /**
     * 즐겨찾기 여부 조회
     */
    @GetMapping("/api/books/{isbn}/bookmarks")
    public ResponseEntity<ResponseCodeDto> findBookMark(@PathVariable("isbn")String isbn,
                                       @AuthenticationPrincipal CustomMemberDetails member) {
        return ResponseEntity.ok(bookMarkService.findBookMark(isbn, member));
    }

    /**
     * 즐겨찾기 등록
     */
    @PostMapping("/api/books/{isbn}/bookmarks")
    public ResponseEntity<ResponseCodeDto> addBookMark(@PathVariable("isbn")String isbn,
                                                       @AuthenticationPrincipal CustomMemberDetails member) {
        return ResponseEntity.ok(bookMarkService.addBookMark(isbn, member));
    }

    /**
     * 즐겨찾기 삭제
     */

    @DeleteMapping("/api/books/{isbn}/bookmarks")
    public ResponseEntity<ResponseCodeDto> deleteBookMark(@PathVariable("isbn")String isbn,
                                                          @AuthenticationPrincipal CustomMemberDetails member) {
        return ResponseEntity.ok(bookMarkService.deleteBookMark(isbn, member));
    }




}
