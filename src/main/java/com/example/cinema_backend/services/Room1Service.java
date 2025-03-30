package com.example.cinema_backend.services;

import com.example.cinema_backend.dtos.Room1DTO;
import com.example.cinema_backend.entities.Movie1;
import com.example.cinema_backend.entities.Room1;
import com.example.cinema_backend.repositories.Movie1Repository;
import com.example.cinema_backend.repositories.Room1Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Room1Service {
    @Autowired
    private Room1Repository room1Repository;

    @Autowired
    private Movie1Repository movie1Repository;

    @Autowired
    private ModelMapper modelMapper;

    public Room1DTO createRoom1(Room1DTO room1DTO) {
        Room1 room1 = modelMapper.map(room1DTO, Room1.class);

        // Gắn Movie1 vào Room1
        Movie1 movie1 = movie1Repository.findById(room1DTO.getMovie1().getId())
                .orElseThrow(() -> new RuntimeException("Movie1 not found"));
        room1.setMovie1(movie1);

        room1 = room1Repository.save(room1);

        return modelMapper.map(room1, Room1DTO.class);
    }

    public Room1DTO getRoom1ById(Integer id) {
        Room1 room1 = room1Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room1 not found"));

        return modelMapper.map(room1, Room1DTO.class);
    }

    public List<Room1DTO> getAllRoom1s() {
        return room1Repository.findAll().stream()
                .map(room -> modelMapper.map(room, Room1DTO.class))
                .collect(Collectors.toList());
    }
}
