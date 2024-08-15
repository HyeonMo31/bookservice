package com.web.bookservice.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.bookservice.dto.PostDto;
import com.web.bookservice.dto.PostSearchCondition;
import com.web.bookservice.dto.QPostDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.web.bookservice.domain.QBook.book;
import static com.web.bookservice.domain.QMember.member;
import static com.web.bookservice.domain.QPost.*;
import static org.springframework.util.StringUtils.isEmpty;

public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<PostDto> findPostList(PostSearchCondition condition, Pageable pageable,
                                      String loginId) {

        QueryResults<PostDto> results = queryFactory
                .select(new QPostDto(
                        post.id,
                        post.title,
                        book.title,
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
        List<PostDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    private OrderSpecifier<?> sortPost(String orderBy) {
        if(isEmpty(orderBy) || orderBy.equals("asc"))
            return post.createdDate.asc();
        else if (orderBy.equals("desc")) {
            return post.createdDate.desc();
        }

        return post.createdDate.asc();
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
            return post.title.eq(query);
        else if (select.equals("bookTitle"))
            return book.title.eq(query);

        return null;
    }

}
