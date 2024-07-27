package com.web.bookservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String MainPage() {
        return "home";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search/bookSearch";
    }

    @GetMapping("/search/books")
    public String searchBooks() {
        return "search/books";
    }

    @GetMapping("/search/book/{isbn}")
    public String searchBook() {
        log.info("여기 들어오나용." );
        return "search/book";
    }
}
