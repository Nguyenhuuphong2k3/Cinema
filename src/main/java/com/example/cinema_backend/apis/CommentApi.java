package com.example.cinema_backend.apis;

import com.example.cinema_backend.dtos.CommentDTO;
import com.example.cinema_backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentApi {
    @Autowired
    private CommentService commentService;

    // API thêm bình luận
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO) {
        commentService.saveComment(commentDTO); // Lưu bình luận từ DTO
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // API lấy danh sách bình luận của phim
    @GetMapping("/{movieId}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Integer movieId) {
        List<CommentDTO> comments = commentService.getCommentsByMovieId(movieId); // Lấy bình luận dưới dạng DTO
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
