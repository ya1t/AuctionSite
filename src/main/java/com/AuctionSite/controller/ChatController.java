package com.AuctionSite.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.AuctionSite.model.ChatMessage;
import com.AuctionSite.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public ChatMessage newUser(ChatMessage chatMessage) {
        chatMessage.setContent(chatMessage.getSender() + " joined the chat!");
        return chatMessage;
    }
    
    @GetMapping("/rooms/{itemId}")
    public String chatRoom(@PathVariable Long itemId, HttpSession session, Model model) {
        // 세션에서 User 객체를 가져옵니다.
        User user = (User) session.getAttribute("loggedInUser");

        // 로그인이 안 된 경우 로그인 페이지로 리다이렉트합니다.
        if (user == null) {
            return "redirect:/auth/login";
        }

        // 모델에 필요한 정보를 추가합니다.
        model.addAttribute("itemId", itemId);
        model.addAttribute("username", user.getUsername());  // User 객체에서 이름을 가져옵니다.

        return "chat";  // chat.jsp로 이동합니다.
    }
}