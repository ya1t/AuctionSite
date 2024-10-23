package com.AuctionSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.AuctionSite.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";  // home.jsp로 이동
    }
    
    @GetMapping("/welcome")
    public String welcomePage(HttpSession session, Model model) {
        Object loggedInUser = session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("username", ((User) loggedInUser).getUsername());
            return "welcome";  // welcome.jsp로 이동
        } else {
            return "redirect:/auth/login";  // 로그인이 안 된 경우 로그인 페이지로 이동
        }
    }
}
