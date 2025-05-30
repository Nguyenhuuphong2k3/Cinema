package com.example.cinema_backend.repositories;

import com.example.cinema_backend.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByMovieIdOrderByCreatedAtDesc(Integer movieId);
}
