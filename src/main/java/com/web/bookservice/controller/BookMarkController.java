package com.web.bookservice.controller;

import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.dto.SearchCondition;
import com.web.bookservice.repository.MemberRepository;
import com.web.bookservice.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookMarkController {

    private final BookMarkService bookMarkService;
    private final MemberRepository memberRepository;

    /**
     * 즐겨찾기 멤버 리스트 조회
     */
    @GetMapping("/api/bookmarks")
    public ResponseEntity<?> findMemberBookMark(@AuthenticationPrincipal CustomMemberDetails member,
                                                @PageableDefault(size = 10) Pageable pageable,
                                                SearchCondition condition,
                                                PagedResourcesAssembler assembler,
                                                @RequestParam(value = "paged", defaultValue = "false", required = false)boolean paged) {

        if(paged)
            return ResponseEntity.ok(assembler.toModel(bookMarkService.findByPage(condition, pageable, member)));
        else
            return ResponseEntity.ok(bookMarkService.findAll(member));
    }

    /**
     * 즐겨찾기 여부 조회
     */
    @GetMapping("/api/books/{isbn}/bookmarks")
    public ResponseEntity<ResponseCodeDto> findBookMark(@PathVariable("isbn")String isbn,
                                       @AuthenticationPrincipal CustomMemberDetails member) {
        return ResponseEntity.ok(bookMarkService.isBookMarked(isbn, member));
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
