package com.AuctionSite.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class AuctionWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        // 메시지를 처리하고 입찰 정보를 다른 참가자에게 전달
        session.sendMessage(new TextMessage("Received: " + payload));
    }
}