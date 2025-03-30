package com.example.cinema_backend.security.controller;

import com.example.cinema_backend.dtos.Message1DTO;
import com.example.cinema_backend.services.Message1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private Message1Service message1Service;

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/room1/{roomId}")
    public Message1DTO sendMessage(@DestinationVariable Long roomId, @Payload Message1DTO messageDTO) {
        messageDTO.setRoom1Id(roomId);
        return message1Service.saveMessage(messageDTO);
    }
}
