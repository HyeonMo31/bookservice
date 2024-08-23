package com.web.bookservice.repository.querydsl;

import com.web.bookservice.domain.Post;
import com.web.bookservice.dto.PostDto;
import com.web.bookservice.dto.PostPagingDto;
import com.web.bookservice.dto.PostSearchCondition;
import com.web.bookservice.dto.ReviewCommentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {

   Page<PostPagingDto> findPostList(PostSearchCondition condition, Pageable pageable, String loginId);

   PostDto findPostDetails(Long id);

   PostDto findPostAndBook(Long id);
   List<ReviewCommentResponseDto> findPostComments(Long id);

}
