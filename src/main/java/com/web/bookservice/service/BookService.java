package com.web.bookservice.service;

import com.web.bookservice.domain.Book;
import com.web.bookservice.dto.BookDetailResponseDto;
import com.web.bookservice.dto.BookDto;
import com.web.bookservice.dto.ReviewCommentDto;
import com.web.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ReviewService reviewService;

    public Book findByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    public BookDetailResponseDto getBookDetailResponseDto(String isbn) {

        BookDetailResponseDto bookDetailResponseDto = new BookDetailResponseDto();

        Book findBook = bookRepository.findByIsbn(isbn);
        BookDto bookDto = new BookDto();

        bookDto.setIsbn(findBook.getIsbn());
        bookDto.setPubdate(findBook.getPubdate());
        bookDto.setPublisher(findBook.getPublisher());
        bookDto.setImage(findBook.getImage());
        bookDto.setDescription(findBook.getDescription());
        bookDto.setPrice(findBook.getPrice());
        bookDto.setTitle(findBook.getTitle());
        bookDto.setAuthor(findBook.getAuthor());

        List<ReviewCommentDto> reviewCommentDtoList = reviewService.findByBook(findBook);

        bookDetailResponseDto.setBookDto(bookDto);
        bookDetailResponseDto.setReviewCommnetDtoList(reviewCommentDtoList);

        return bookDetailResponseDto;
    }


}
