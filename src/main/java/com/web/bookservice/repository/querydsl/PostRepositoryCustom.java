package com.web.bookservice.repository.querydsl;

import com.web.bookservice.dto.PostDto;
import com.web.bookservice.dto.PostSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

   Page<PostDto> findPostList(PostSearchCondition condition, Pageable pageable, String loginId);

}
