package com.web.bookservice.controller;

import com.web.bookservice.domain.UploadFile;
import com.web.bookservice.dto.JoinDto;
import com.web.bookservice.repository.FileStore;
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

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginJoinController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "아이디 또는 비밀번호가 맞지 않습니다.");
        }
        return "user/login";
    }

    @GetMapping("/join")
    public String joinPage(@ModelAttribute JoinDto joinDto) {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@Validated  @ModelAttribute JoinDto joinDTO, BindingResult result
    , RedirectAttributes redirectAttributes) throws IOException {

        if(result.hasErrors()) {
            return "user/join";
        }

        int state = memberService.join(joinDTO);

        //created
        if(state == 201) {
            redirectAttributes.addFlashAttribute("successMessage", "회원가입이 완료 되었습니다.");
        }
        //conflict
        else if(state == 409) {
            result.rejectValue("loginId" , "", "아이디가 이미 존재합니다.");
            return "user/join";
        }
        //bad request Image type error
        else if (state == 400) {
            result.rejectValue("memberImage", "", "gif, jpg, png 형식만 지원합니다.");
            return "user/join";
        }


        return "redirect:/login";
    }

}
