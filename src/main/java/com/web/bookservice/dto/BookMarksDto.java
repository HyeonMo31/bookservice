package com.web.bookservice.dto;

import com.web.bookservice.domain.Bookmark;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class BookMarksDto {

    private String loginId;
    private String name;
    private List<BookMarkInfo> bookMarksList = new ArrayList<>();

    public BookMarksDto(String loginId, String name, List<Bookmark> bookmarks) {

        this.loginId = loginId;
        this.name = name;

        for(Bookmark bookMark : bookmarks) {
            this.bookMarksList.add(new BookMarkInfo(bookMark));
        }

    }

    @Getter @Setter
    class BookMarkInfo {
        public BookMarkInfo(Bookmark bookMark) {
            this.id = bookMark.getId();
            this.isbn = bookMark.getBook().getIsbn();
            this.title = bookMark.getBook().getTitle();
            this.author = bookMark.getBook().getAuthor();
            this.publisher = bookMark.getBook().getPublisher();
        }
        private Long id;
        private String isbn;
        private String title;
        private String author;
        private String publisher;
    }


}
