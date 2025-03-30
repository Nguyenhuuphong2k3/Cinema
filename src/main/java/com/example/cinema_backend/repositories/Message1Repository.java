package com.example.cinema_backend.repositories;

import com.example.cinema_backend.entities.Message1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Message1Repository extends JpaRepository<Message1, Long> {
    List<Message1> findByRoom1Id(Long roomId);
}
