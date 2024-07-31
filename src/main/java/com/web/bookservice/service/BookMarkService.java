package com.web.bookservice.service;

import com.web.bookservice.domain.Book;
import com.web.bookservice.domain.Bookmark;
import com.web.bookservice.domain.Member;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.MsgResponseDto;
import com.web.bookservice.repository.BookMarkRepository;
import com.web.bookservice.repository.BookRepository;
import com.web.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMarkService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookMarkRepository bookMarkRepository;

    public MsgResponseDto findBookMark(String isbn, CustomMemberDetails member) {

        Book book = bookRepository.findByIsbn(isbn);

        if(book == null || member == null)
            return new MsgResponseDto("false");

        Member findMember = memberRepository.findByLoginId(member.getUsername());
        Bookmark bookmark = bookMarkRepository.findByBookAndMember(book, findMember);

        if(bookmark == null)
            return new MsgResponseDto("false");
        else
            return new MsgResponseDto("true");

    }

    public void addBookMark(String isbn, CustomMemberDetails member) {

        Book book = bookRepository.findByIsbn(isbn);
        Member findMember = memberRepository.findByLoginId(member.getUsername());

        Bookmark bookmark = new Bookmark();
        bookmark.setBook(book);
        bookmark.setMember(findMember);

        bookMarkRepository.save(bookmark);

    }


}
