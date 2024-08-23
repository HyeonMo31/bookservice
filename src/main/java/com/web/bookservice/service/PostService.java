package com.web.bookservice.service;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Post;
import com.web.bookservice.dto.*;
import com.web.bookservice.exception.BookNotFoundException;
import com.web.bookservice.exception.MemberNotAuthenticatedException;
import com.web.bookservice.exception.PostNotFoundException;
import com.web.bookservice.repository.BookRepository;
import com.web.bookservice.repository.MemberRepository;
import com.web.bookservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    
    public PostDto findPostDetails(Long id) {
        Optional<Post> findPost = postRepository.findById(id);

        findPost.orElseThrow(() -> new PostNotFoundException());
//
//        if(!findPost.isPresent())
//            throw new PostNotFoundException();

        return postRepository.findPostDetails(id);

    }


    public Page<PostPagingDto> findAll(PostSearchCondition condition, Pageable pageable, CustomMemberDetails member)
    {
        String loginId = null;

        if(condition.isMy() && member == null)
            throw new MemberNotAuthenticatedException("로그인 되어 있지 않습니다.");

        if(condition.isMy() && member != null)
            loginId = member.getUsername();

        return postRepository.findPostList(condition, pageable, loginId);
    }

    public ResponseCodeDto createPost(PostRequestDto request, CustomMemberDetails member) {

        if(member == null)
            throw new MemberNotAuthenticatedException("로그인이 해제 되었습니다.");

        Member findMember = memberRepository.findByLoginId(request.getLoginId());
        Book findBook = bookRepository.findByIsbn(request.getIsbn());

        Post post = new Post(findMember, findBook, request.getTitle(), request.getText());

        Post savedPost = postRepository.save(post);

        return new ResponseCodeDto(200, savedPost.getId().toString());

    }


}
