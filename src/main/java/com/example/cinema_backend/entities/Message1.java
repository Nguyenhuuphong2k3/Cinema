package com.example.cinema_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Message1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String sender;

    @ManyToOne
    @JoinColumn(name = "room1_id", nullable = false)
    private Room1 room1;
}
