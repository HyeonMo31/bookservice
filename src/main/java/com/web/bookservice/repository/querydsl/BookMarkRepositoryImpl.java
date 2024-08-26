package com.web.bookservice.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.bookservice.domain.QBookmark;
import com.web.bookservice.dto.*;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.web.bookservice.domain.QBook.book;
import static com.web.bookservice.domain.QBookmark.*;
import static com.web.bookservice.domain.QMember.member;
import static com.web.bookservice.domain.QPost.post;
import static org.springframework.util.StringUtils.isEmpty;

public class BookMarkRepositoryImpl implements BookMarkRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public BookMarkRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<BookmarkDto> findAll(String loginId) {
        QueryResults<BookmarkDto> results = queryFactory
                .select(new QBookmarkDto(
                        bookmark.id,
                        bookmark.book.title,
                        bookmark.book.author,
                        bookmark.book.publisher,
                        bookmark.book.pubdate,
                        bookmark.book.isbn
                ))
                .from(bookmark)
                .leftJoin(bookmark.book)
                .leftJoin(bookmark.member)
                .where(isMy(loginId))
                //카운트 쿼리와 컨텐트 쿼리를 날린다. 총 2개를 날린다라는 것.
                .fetchResults();
        return results.getResults();
    }

    @Override
    public Page<BookmarkDto> findByPage(SearchCondition condition, Pageable pageable,
                                    String loginId) {

        QueryResults<BookmarkDto> results = queryFactory
                .select(new QBookmarkDto(
                        bookmark.id,
                        bookmark.book.title,
                        bookmark.book.author,
                        bookmark.book.publisher,
                        bookmark.book.pubdate,
                        bookmark.book.isbn
                ))
                .from(bookmark)
                .leftJoin(bookmark.book)
                .leftJoin(bookmark.member)
                .where(isMy(loginId),
                        selectEq(condition.getSelect(), condition.getQuery()))
                .orderBy(sortBook(condition.getOrderBy()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                //카운트 쿼리와 컨텐트 쿼리를 날린다. 총 2개를 날린다라는 것.
                .fetchResults();
        List<BookmarkDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    private OrderSpecifier<?> sortBook(String orderBy) {
        if(isEmpty(orderBy) || orderBy.equals("createdDesc"))
            return bookmark.createdDate.desc();
        else if (orderBy.equals("createdAsc"))
            return bookmark.createdDate.asc();
        else if (orderBy.equals("pubDesc"))
            return bookmark.book.pubdate.desc();
        else if (orderBy.equals("pubAsc"))
            return bookmark.book.pubdate.asc();

        return bookmark.createdDate.desc();
    }
    private BooleanExpression isMy(String loginId) {
        return isEmpty(loginId) ? null : bookmark.member.loginId.eq(loginId);
    }
    private BooleanExpression selectEq(String select, String query) {
        if(isEmpty(select) || isEmpty(query))
            return null;
        else if (select.equals("author"))
            return bookmark.book.author.eq(query);
        else if (select.equals("isbn"))
            return bookmark.book.isbn.contains(query);
        else if (select.equals("bookTitle"))
            return bookmark.book.title.contains(query);
        else if (select.equals("publisher"))
            return bookmark.book.publisher.contains(query);

        return null;
    }
}
