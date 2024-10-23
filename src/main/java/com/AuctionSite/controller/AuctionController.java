package com.AuctionSite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    @GetMapping("/start/{itemId}")
    public String startAuction(@PathVariable Long itemId) {
        // 경매 시작 로직 (예: WebSocket 알림 등)
        return "Auction started for item " + itemId;
    }
}