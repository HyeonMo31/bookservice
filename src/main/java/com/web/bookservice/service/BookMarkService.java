package com.web.bookservice.service;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Bookmark;
import com.web.bookservice.domain.Member;
import com.web.bookservice.dto.*;
import com.web.bookservice.exception.BookNotFoundException;
import com.web.bookservice.exception.MemberNotAuthenticatedException;
import com.web.bookservice.repository.BookMarkRepository;
import com.web.bookservice.repository.BookRepository;
import com.web.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.web.bookservice.exception.ErrorMessage.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BookMarkService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookMarkRepository bookMarkRepository;

    public List<BookmarkDto> findAll(CustomMemberDetails member) {

        if(member == null)
            throw new MemberNotAuthenticatedException("로그인 되어 있지 않습니다.");

        Member findMember = memberRepository.findByLoginId(member.getUsername());

        return bookMarkRepository.findAll(findMember.getLoginId());

    }

    public ResponseCodeDto isBookMarked(String isbn, CustomMemberDetails member) {

        Book findBook = bookRepository.findByIsbn(isbn);

        if(findBook == null)
            throw new BookNotFoundException();
        if(member == null)
            return new ResponseCodeDto(401, "false");

        Member findMember = memberRepository.findByLoginId(member.getUsername());
        Bookmark bookmark = bookMarkRepository.findByBookAndMember(findBook, findMember);

        if(bookmark == null)
            return new ResponseCodeDto(404, "false");
        else
            return new ResponseCodeDto(200, "true");

    }

    public ResponseCodeDto addBookMark(String isbn, CustomMemberDetails member) {

        Book findBook = bookRepository.findByIsbn(isbn);
        if(findBook == null)
            throw new BookNotFoundException();
        if(member == null)
            throw new MemberNotAuthenticatedException(MEMBER_NOT_AUTHORIZED.getMessage());

        Member findMember = memberRepository.findByLoginId(member.getUsername());

        Bookmark bookmark = new Bookmark(findBook, findMember);

        bookMarkRepository.save(bookmark);

        return new ResponseCodeDto(200, "즐겨찾기가 추가 되었습니다.");

    }

    public ResponseCodeDto deleteBookMark(String isbn, CustomMemberDetails member) {

        Book findBook = bookRepository.findByIsbn(isbn);

        if(member == null)
            throw new MemberNotAuthenticatedException("로그인이 되어 있지 않습니다.");

        Member findMember = memberRepository.findByLoginId(member.getUsername());

        Bookmark findBookMark = bookMarkRepository.findByBookAndMember(findBook, findMember);

        bookMarkRepository.delete(findBookMark);

        return new ResponseCodeDto(200, "삭제 완료");
    }

    public Page<BookmarkDto> findByPage(SearchCondition searchCondition, Pageable pageable, CustomMemberDetails member) {
        return bookMarkRepository.findByPage(searchCondition, pageable, member.getUsername());
    }
}
