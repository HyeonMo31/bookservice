package com.web.bookservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
}
