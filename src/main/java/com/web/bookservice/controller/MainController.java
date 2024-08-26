package com.web.bookservice.controller;

import com.web.bookservice.dto.CustomMemberDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        System.out.println("/search/book/${isbn}");
        return "search/book";
    }
    @GetMapping("/post")
    public String postListPage() {
        return "post/postList";
    }

    @GetMapping("/post/add")
    public String postAddPage(){
        return "post/addForm";
    }

    @GetMapping("/post/{id}")
    public String postDetailPage() {return "post/post";}

    @GetMapping("/post/update/{id}")
    public String postUpdatePage() {return "post/editForm";}

    @GetMapping("/my-page")
    public String myPage() {

        return "user/my-page";
    }

    @GetMapping("/my-page/bookmarks")
    public String my_bookmarksPage() {
        return "user/myBookmarks";
    }

}
