package com.example.cinema_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Room1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // Tên phòng

    @ManyToOne
    @JoinColumn(name = "movie1_id", nullable = false)
    private Movie1 movie1; // Liên kết tới Movie1

    @ElementCollection
    private List<String> users; // Danh sách user tham gia phòng

}
