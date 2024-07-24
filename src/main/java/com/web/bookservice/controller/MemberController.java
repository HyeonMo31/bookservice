package com.web.bookservice.controller;

import com.web.bookservice.domain.Member;
import com.web.bookservice.dto.JoinDTO;
import com.web.bookservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "아이디 또는 비밀번호가 맞지 않습니다.");
        }
        return "user/login";
    }

    @GetMapping("/join")
    public String joinPage(@ModelAttribute JoinDTO joinDTO) {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@Validated  @ModelAttribute JoinDTO joinDTO, BindingResult result
    , RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            return "user/join";
        }

        boolean state = memberService.join(joinDTO);

        if(state) {
            redirectAttributes.addFlashAttribute("successMessage", "회원가입이 완료 되었습니다.");
        } else {
            result.rejectValue("loginId", "" ,"아이디가 이미 존재합니다.");
            return "user/join";
        }

        return "redirect:/login";
    }

}
