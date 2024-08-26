package com.web.bookservice.repository;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Bookmark;
import com.web.bookservice.domain.Member;
import com.web.bookservice.dto.BookDto;
import com.web.bookservice.repository.querydsl.BookMarkRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<Bookmark, Long>, BookMarkRepositoryCustom {

    Bookmark findByBookAndMember(Book book, Member member);

}
