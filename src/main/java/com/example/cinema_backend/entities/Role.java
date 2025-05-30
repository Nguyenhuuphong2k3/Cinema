package com.example.cinema_backend.entities;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
