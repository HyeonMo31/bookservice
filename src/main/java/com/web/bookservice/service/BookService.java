package com.web.bookservice.service;

import com.web.bookservice.domain.Book;
import com.web.bookservice.dto.BookDto;
import com.web.bookservice.exception.BookNotFoundException;
import com.web.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookDto findBookDetail(String isbn) {

        Book findBook = bookRepository.findByIsbn(isbn);

        if(findBook == null) {
            throw new BookNotFoundException();
        }

        BookDto bookDto = new BookDto();

        bookDto.setIsbn(findBook.getIsbn());
        bookDto.setPubdate(findBook.getPubdate());
        bookDto.setPublisher(findBook.getPublisher());
        bookDto.setImage(findBook.getImage());
        bookDto.setDescription(findBook.getDescription());
        bookDto.setPrice(findBook.getPrice());
        bookDto.setTitle(findBook.getTitle());
        bookDto.setAuthor(findBook.getAuthor());

        return bookDto;
    }


}
