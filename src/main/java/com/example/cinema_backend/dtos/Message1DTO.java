package com.example.cinema_backend.dtos;

import lombok.Data;

@Data
public class Message1DTO {
    private Long id;
    private String content;
    private String sender;
    private Long room1Id;
}
