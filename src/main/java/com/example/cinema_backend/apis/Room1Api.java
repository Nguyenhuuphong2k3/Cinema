package com.example.cinema_backend.apis;

import com.example.cinema_backend.dtos.Room1DTO;
import com.example.cinema_backend.services.Room1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room1s")
public class Room1Api {
    @Autowired
    private Room1Service room1Service;

    @PostMapping
    public ResponseEntity<Room1DTO> createRoom1(@RequestBody Room1DTO room1DTO) {
        return ResponseEntity.ok(room1Service.createRoom1(room1DTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room1DTO> getRoom1(@PathVariable Integer id) {
        return ResponseEntity.ok(room1Service.getRoom1ById(id));
    }

    @GetMapping
    public ResponseEntity<List<Room1DTO>> getAllRoom1s() {
        return ResponseEntity.ok(room1Service.getAllRoom1s());
    }
}
