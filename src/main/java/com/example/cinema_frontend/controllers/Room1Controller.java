package com.example.cinema_frontend.controllers;

import com.example.cinema_frontend.constants.Api;
import com.example.cinema_frontend.models.Message1DTO;
import com.example.cinema_frontend.models.Room1DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/room1")
public class Room1Controller {
    @Autowired
    private RestTemplate restTemplate;

    private static final String API_GET_ROOM1 = Api.baseURL + "/api/room1s/";
    private static final String API_GET_MESSAGES = Api.baseURL + "/api/messages/";

    @GetMapping("/{id}")
    public String getRoom1(@PathVariable Integer id, Model model) {
        // Lấy thông tin phòng
        ResponseEntity<Room1DTO> roomResponse = restTemplate.getForEntity(API_GET_ROOM1 + id, Room1DTO.class);
        Room1DTO room1 = roomResponse.getBody();

        // Lấy lịch sử tin nhắn
        ResponseEntity<Message1DTO[]> messagesResponse = restTemplate.getForEntity(API_GET_MESSAGES + id, Message1DTO[].class);
        List<Message1DTO> messages = Arrays.asList(messagesResponse.getBody());

        // Đưa dữ liệu vào model
        model.addAttribute("room1", room1);
        model.addAttribute("messages", messages);
        return "room1-details";
    }
}
