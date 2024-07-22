package com.web.bookservice.controller;

import com.web.bookservice.domain.Member;
import com.web.bookservice.dto.JoinDTO;
import com.web.bookservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "user/join";
    }

    @PostMapping("/joinProc")
    public String join(@ModelAttribute("joinRequset")JoinDTO joinDTO) {

        boolean state = memberService.join(joinDTO);



        return "redirect:/";
    }

}
