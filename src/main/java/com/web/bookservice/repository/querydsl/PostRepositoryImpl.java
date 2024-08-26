package com.web.bookservice.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.bookservice.dto.*;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

        PostDto postDto = findPostAndBook(id);
        postDto.setCommentsDto(findPostComments(id));

        return postDto;
    }

    @Override
    public PostDto findPostAndBook(Long id) {
        PostDto postDto = (PostDto) queryFactory
                .select(Projections.constructor(
                        PostDto.class,
                        post.id,
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
                        comment.member.loginId,
                        comment.member.name,
                        comment.text,
                        comment.createdDate))
                .from(post)
                .leftJoin(post.comments, comment)
                .where(post.id.eq(id))
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
                        post.createdDate))
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
