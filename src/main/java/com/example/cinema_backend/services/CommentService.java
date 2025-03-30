package com.example.cinema_backend.services;

import com.example.cinema_backend.dtos.CommentDTO;
import com.example.cinema_backend.entities.Comment;
import com.example.cinema_backend.entities.Movie;
import com.example.cinema_backend.entities.User;
import com.example.cinema_backend.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    // Lấy danh sách bình luận của phim và ánh xạ sang DTO
    public List<CommentDTO> getCommentsByMovieId(Integer movieId) {
        return commentRepository.findByMovieIdOrderByCreatedAtDesc(movieId)
                .stream()
                .map(comment -> {
                    CommentDTO dto = new CommentDTO();
                    dto.setId(comment.getId());
                    dto.setMovieId(comment.getMovie().getId());
                    dto.setUserFullName(comment.getUser().getFullName());
                    dto.setContent(comment.getContent());
                    dto.setCreatedAt(comment.getCreatedAt().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Lưu bình luận từ DTO
    public void saveComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        // Gán Movie từ movieId
        Movie movie = new Movie();
        movie.setId(commentDTO.getMovieId());
        comment.setMovie(movie);

        // Gán User từ ID giả lập (hoặc lấy từ session/token)
        User user = new User();
        user.setId(1); // Thay ID user thực tế
        comment.setUser(user);

        commentRepository.save(comment);
    }
}
