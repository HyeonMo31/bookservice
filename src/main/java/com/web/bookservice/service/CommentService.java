package com.web.bookservice.service;

import com.web.bookservice.domain.Comment;
import com.web.bookservice.domain.Post;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.exception.MemberNotAuthenticatedException;
import com.web.bookservice.exception.PostNotFoundException;
import com.web.bookservice.repository.CommentRepository;
import com.web.bookservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public ResponseCodeDto addComment(Long postId, String text, CustomMemberDetails member) {

        Optional<Post> findPost = postRepository.findById(postId);

        findPost.orElseThrow(()->new PostNotFoundException());

        if(member == null)
            throw new MemberNotAuthenticatedException("로그인 되어 있지 않습니다.");

        commentRepository.save(new Comment(findPost.get(), member.getMember(), text));


        return new ResponseCodeDto(200, "댓글이 작성 되었습니다.");
    }

    public ResponseCodeDto deleteComment(Long postId, Long commentId, CustomMemberDetails member) {

        if(member == null)
            throw new MemberNotAuthenticatedException("로그인 되어 있지 않습니다.");

        Optional<Post> findPost = postRepository.findById(postId);
        findPost.orElseThrow(() -> new PostNotFoundException());

        Optional<Comment> comment = commentRepository.findById(commentId);
        comment.orElseThrow(() -> new RuntimeException("댓글이 존재하지 않음"));

        commentRepository.delete(comment.get());

        return new ResponseCodeDto(200, "댓글이 삭제 되었습니다.");
    }

}
