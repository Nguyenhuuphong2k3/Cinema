package com.example.cinema_backend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class Room1DTO {
    private Integer id;
    private String name;
    private Movie1DTO movie1; // Liên kết với Movie1DTO
    private List<String> users; // Danh sách user tham gia phòng
}
