package com.web.bookservice.service;

import com.web.bookservice.domain.Post;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.PostDto;
import com.web.bookservice.dto.PostSearchCondition;
import com.web.bookservice.exception.MemberNotAuthenticatedException;
import com.web.bookservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public Page<PostDto> findAll(PostSearchCondition condition, Pageable pageable, CustomMemberDetails member)
    {
        if(condition.isMy() && member == null)
            throw new MemberNotAuthenticatedException("로그인 되어 있지 않습니다.");

        String loginId;

        if(member == null)
            loginId = null;
        else
            loginId = member.getUsername();

        return postRepository.findPostList(condition, pageable, loginId);
    }


}
