package com.roki.fashionfeed.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final int USER_ID_MIN_NUMBER = 1;
    private final int USER_ID_MAX_NUMBER = 254;

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int sessionUser = (int) (USER_ID_MIN_NUMBER + Math.random() * USER_ID_MAX_NUMBER);
        session.setAttribute("sessionUser", sessionUser);
        System.out.println(sessionUser + "회원 로그인 완료");
        return "redirect:/";
    }

}
