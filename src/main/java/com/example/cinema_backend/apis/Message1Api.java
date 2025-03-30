package com.example.cinema_backend.apis;

import com.example.cinema_backend.dtos.Message1DTO;
import com.example.cinema_backend.services.Message1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class Message1Api {

    @Autowired
    private Message1Service message1Service;

    @GetMapping("/{roomId}")
    public List<Message1DTO> getMessages(@PathVariable Long roomId) {
        return message1Service.getMessagesByRoomId(roomId);
    }
}
