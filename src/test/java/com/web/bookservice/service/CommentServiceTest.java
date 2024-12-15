package com.web.bookservice.service;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Comment;
import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Post;
import com.web.bookservice.repository.BookRepository;
import com.web.bookservice.repository.CommentRepository;
import com.web.bookservice.repository.MemberRepository;
import com.web.bookservice.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("댓글 작성 테스트")
    public void addParentComment() {

        Member member = memberRepository.findByLoginId("thesting31");
        Optional<Post> post = postRepository.findById(1L);
        Comment comment = new Comment(post.get(), member, "string", null);

        commentRepository.save(comment);

        Optional<Comment> findComment = commentRepository.findById(comment.getId());

        //댓글 작성이 DB에 반영이 되는지
        Assertions.assertThat(comment).isEqualTo(findComment.get());
    }

    @Test
    @DisplayName("대댓글 작성")
    public void addChildComment() {

        Member member = memberRepository.findByLoginId("thesting31");
        Optional<Post> post = postRepository.findById(1L);
        Optional<Comment> ParentComment = commentRepository.findById(1L);

        Comment ChildComment = new Comment(post.get(), member, "string", ParentComment.get());
        commentRepository.save(ChildComment);

        Optional<Comment> findComment = commentRepository.findById(ChildComment.getId());

        //대댓글 작성이 DB에 반영이 되는지
        Assertions.assertThat(ChildComment).isEqualTo(findComment.get());
        //부모 요소가 제대로 반영이 됐는디
        Assertions.assertThat(ChildComment.getParent()).isEqualTo(ParentComment.get());
    }

    @Test
    @DisplayName("댓글 삭제")
    public void deleteParentComment() {

        Member member = memberRepository.findByLoginId("thesting31");
        Optional<Post> post = postRepository.findById(1L);
        Comment comment = new Comment(post.get(), member, "string", null);

        //댓글 저장 하고 삭제하기
        Comment savedComment = commentRepository.save(comment);
        commentRepository.delete(savedComment);

        Optional<Comment> findComment =commentRepository.findById(savedComment.getId());

        //댓글이 성공적으로 삭제 되었는지?
        Assertions.assertThat(findComment).isEmpty();

        //대댓글이 성공적으로 삭제 되었는지?
        List<Comment> children = commentRepository.findByParentId(savedComment.getId());
        Assertions.assertThat(children).hasSize(0);

    }

    @Test
    @DisplayName("대댓글 삭제")
    public void deleteChildComment() {

        Member member = memberRepository.findByLoginId("thesting31");
        Optional<Post> post = postRepository.findById(1L);
        Optional<Comment> parentComment = commentRepository.findById(1L);

        //자식 댓글 삭제
        Comment ChildComment = new Comment(post.get(), member, "string", parentComment.get());
        Comment savedComment = commentRepository.save(ChildComment);
        commentRepository.delete(savedComment);

        Optional<Comment> findParentComment = commentRepository.findById(parentComment.get().getId());
        //자식 댓글 삭제했을때 부모는 삭제 안되는지만 살피면 된다.
        Assertions.assertThat(findParentComment).isNotEmpty();

    }


}