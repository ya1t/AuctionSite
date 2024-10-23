package com.AuctionSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AuctionSite.model.User;
import com.AuctionSite.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String loginPage(@ModelAttribute("errorMessage") String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return "login";  // login.jsp로 이동
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,  // 세션을 통해 사용자 정보 저장
            RedirectAttributes redirectAttributes) {

    	System.out.println("Username: " + username + ", Password: " + password);
    	
        User user = authService.authenticate(username, password);
        if (user != null) {  // 인증 성공 시
            session.setAttribute("loggedInUser", user);  // 세션에 사용자 정보 저장
            return "redirect:/welcome";  // 성공 시 welcome.jsp로 이동
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "NO USER~~");
            return "redirect:/auth/login";  // 실패 시 로그인 페이지로 리다이렉트
        }
    }

    // 회원가입 페이지로 이동
    @GetMapping("/register")
    public String registerPage() {
        return "register";  // register.jsp로 이동
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email, 
            RedirectAttributes redirectAttributes) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        authService.registerUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "GOOOOOOOOOOOD1");
        return "redirect:/auth/login";
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 무효화
        return "redirect:/";  // 홈 페이지로 리다이렉트
    }
}
