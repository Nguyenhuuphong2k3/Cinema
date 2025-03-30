package com.example.cinema_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Movie1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // Tên phim
    private String url;  // Đường dẫn video
    private String description; // Mô tả phim
}
