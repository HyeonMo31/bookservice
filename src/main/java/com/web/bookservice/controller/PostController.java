package com.web.bookservice.controller;

import com.web.bookservice.domain.Post;
import com.web.bookservice.dto.*;
import com.web.bookservice.service.CommentService;
import com.web.bookservice.service.PostService;
import com.web.bookservice.utils.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final CookieUtil cookieUtil;

    /**
     * 조건에 따른 게시글 Paging 조회 (PagedResourcesAssembler를 통해 직관적인 JSON 반환)
     * build.gradle에서 의존성 추가
     */
    @GetMapping("/api/post")
    public ResponseEntity<PagedModel> findAllPost(@PageableDefault(size = 10) Pageable pageable,
                                                  SearchCondition condition,
                                                  @AuthenticationPrincipal CustomMemberDetails member,
                                                  PagedResourcesAssembler assembler) {
        return ResponseEntity.ok(assembler.toModel(postService.findAll(condition, pageable, member)));
    }

    /**
     * 게시글 Detail 조회
     */
    @GetMapping("/api/post/{postId}")
    public ResponseEntity<PostDto> findPost(@PathVariable("postId")Long postId,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        String cookieValue = cookieUtil.getCookieValue(request, response, "view_id");
        return ResponseEntity.ok(postService.findPostDetails(postId, cookieValue));
    }

    /**
     * 최신글 3개 조회
     */
    @GetMapping("/api/post/latest")
    public ResponseEntity<List<PostLatestDto>> findLatestPost() {
        return ResponseEntity.ok(postService.findLatestPost());
    }

    /**
     * 게시글 작성
     * @param request
     * @return ResponseCode{code = 200, message = savedPost.id()}
     */
    @PostMapping("/api/post")
    public ResponseEntity<ResponseCodeDto> createPost(@RequestBody PostRequestDto request,
                                                      @AuthenticationPrincipal CustomMemberDetails member) {

        return ResponseEntity.ok(postService.createPost(request, member));

    }

    /**
     * 게시글 수정
     */
    @PatchMapping("/api/post")
    public ResponseEntity<ResponseCodeDto> updatePost(@RequestBody PostRequestDto request,
                                                      @AuthenticationPrincipal CustomMemberDetails member) {

        return ResponseEntity.ok(postService.updatePost(request, member));

    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/api/post/{postId}")
    public ResponseEntity<ResponseCodeDto> deletePost(@PathVariable("postId") Long id,
                                                      @AuthenticationPrincipal CustomMemberDetails member) {

        return ResponseEntity.ok(postService.deletePost(id, member));

    }

    /**
     * 댓글 작성
     */
    @PostMapping("/api/post/{postId}/comments")
    public ResponseEntity<ResponseCodeDto> addComment(@PathVariable("postId") Long postId,
                                                      @RequestBody ReviewCommentRequestDto request,
                                                      @AuthenticationPrincipal CustomMemberDetails member) {

        return ResponseEntity.ok(commentService.addComment(postId, request, member));

    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/api/post/{postId}/comments/{commentId}")
    public ResponseEntity<ResponseCodeDto> deleteComment(@PathVariable("postId") Long postId,
                                                      @PathVariable("commentId") Long commentId,
                                                      @AuthenticationPrincipal CustomMemberDetails member) {

        return ResponseEntity.ok(commentService.deleteComment(postId, commentId, member));

    }
}
