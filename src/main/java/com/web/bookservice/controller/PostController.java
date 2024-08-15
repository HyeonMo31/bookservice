package com.web.bookservice.controller;

import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.PostSearchCondition;
import com.web.bookservice.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    /**
     * 조건에 따른 게시글 Paging 조회 (PagedResourcesAssembler를 통해 직관적인 JSON 반환)
     * build.gradle에서 의존성 추가
     */
    @GetMapping("/api/post")
    public ResponseEntity<PagedModel> getPosts(@PageableDefault(size = 10) Pageable pageable,
                                               PostSearchCondition condition,
                                               @AuthenticationPrincipal CustomMemberDetails member,
                                               PagedResourcesAssembler assembler) {
        log.info("SearchCondition = {}", condition);
        return ResponseEntity.ok(assembler.toModel(postService.findAll(condition, pageable, member)));
    }
}
