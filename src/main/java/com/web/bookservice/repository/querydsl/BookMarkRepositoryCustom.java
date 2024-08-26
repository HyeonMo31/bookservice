package com.web.bookservice.repository.querydsl;

import com.web.bookservice.dto.BookDto;
import com.web.bookservice.dto.BookmarkDto;
import com.web.bookservice.dto.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookMarkRepositoryCustom {

    List<BookmarkDto> findAll(String loginId);

    Page<BookmarkDto> findByPage(SearchCondition searchCondition,
                             Pageable pageable, String loginId);

}
