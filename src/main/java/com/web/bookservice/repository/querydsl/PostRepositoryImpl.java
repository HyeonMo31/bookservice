package com.web.bookservice.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.bookservice.dto.*;
import com.web.bookservice.repository.CommentRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static com.web.bookservice.domain.QBook.book;
import static com.web.bookservice.domain.QComment.comment;
import static com.web.bookservice.domain.QMember.member;
import static com.web.bookservice.domain.QPost.*;
import static org.springframework.util.StringUtils.isEmpty;

public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public PostDto findPostDetails(Long id) {

        //댓글 대댓글을 한번에 조회한다.
        List<ReviewCommentResponseDto> comments = findPostComments(id);

        PostDto postDto = findPostAndBook(id);

        //부모 댓글에 대해 자식 댓글을 삽입한다.
        TreeMap<Long, ReviewCommentResponseDto> m = new TreeMap<>();
        for (ReviewCommentResponseDto comment : comments) {
            //부모 요소이면 m에 삽입.
            if(comment.getParentId() == null) {
                m.put(comment.getId(), comment);
                //자식 요소이면 부모 아래 삽입.
            } else {
               m.get(comment.getParentId()).getChildren().add(comment);
            }
        }
        
        //map의 요소들을 list로 반환
        List<ReviewCommentResponseDto> keysList = new ArrayList<>(m.values());
        postDto.setCommentsDto(keysList);

        return postDto;
    }

    @Override
    public PostDto findPostAndBook(Long id) {
        PostDto postDto = (PostDto) queryFactory
                .select(Projections.constructor(
                        PostDto.class,
                        post.id,
                        member.memberImage.storeFileName,
                        member.name,
                        member.loginId,
                        post.createdDate,
                        post.title.as("postTitle"),
                        post.text,
                        Projections.constructor(
                                PostDto.BookInfo.class,
                                book.image,
                                book.title.as("bookTitle"),
                                book.author,
                                book.pubdate,
                                book.isbn
                        )
                ))
                .from(post)
                .leftJoin(post.book, book)
                .leftJoin(post.member, member)
                .where(post.id.eq(id))
                .fetchOne();
        return postDto;
    }

    @Override
    public List<ReviewCommentResponseDto> findPostComments(Long id) {
        QueryResults<ReviewCommentResponseDto> results = queryFactory
                .select(Projections.constructor(
                        ReviewCommentResponseDto.class,
                        comment.id,
                        comment.member.memberImage.storeFileName,
                        comment.member.loginId,
                        comment.member.name,
                        comment.text,
                        comment.createdDate,
                        comment.parent.id))
                .from(post)
                .leftJoin(post.comments, comment)
                .where(post.id.eq(id))
                .orderBy(comment.id.asc().nullsFirst())
                .fetchResults();
        return results.getResults();
    }

    @Override
    public Page<PostPagingDto> findPostList(SearchCondition condition, Pageable pageable,
                                            String loginId) {

        QueryResults<PostPagingDto> results = queryFactory
                .select(new QPostPagingDto(
                        post.id,
                        post.title,
                        book.title,
                        book.isbn,
                        member.name,
                        post.createdDate,
                        post.comments.size()))
                .from(post)
                .leftJoin(post.book, book)
                .leftJoin(post.member, member)
                .where(isMy(loginId),
                        selectEq(condition.getSelect(), condition.getQuery()))
                .orderBy(sortPost(condition.getOrderBy()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                //카운트 쿼리와 컨텐트 쿼리를 날린다. 총 2개를 날린다라는 것.
                .fetchResults();
        List<PostPagingDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    private OrderSpecifier<?> sortPost(String orderBy) {
        if(isEmpty(orderBy) || orderBy.equals("desc"))
            return post.createdDate.desc();
        else if (orderBy.equals("asc")) {
            return post.createdDate.asc();
        }
        else if (orderBy.equals("commentDesc")) {
            return post.comments.size().desc();
        }
        else if (orderBy.equals("commentAsc")) {
            return post.comments.size().asc();
        }


        return post.createdDate.desc();
    }
    private BooleanExpression isMy(String loginId) {
        return isEmpty(loginId) ? null : member.loginId.eq(loginId);
    }
    private BooleanExpression selectEq(String select, String query) {
        if(isEmpty(select) || isEmpty(query))
            return null;
        else if (select.equals("writer"))
            return member.name.eq(query);
        else if (select.equals("postTitle"))
            return post.title.contains(query);
        else if (select.equals("bookTitle"))
            return book.title.contains(query);

        return null;
    }

}
