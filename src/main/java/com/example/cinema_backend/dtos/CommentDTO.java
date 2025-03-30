package com.example.cinema_backend.dtos;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer id;           // ID của bình luận
    private Integer movieId;      // ID phim được bình luận
    private String userFullName;  // Tên người gửi bình luận
    private String content;       // Nội dung bình luận
    private String createdAt;     // Thời gian tạo bình luận
}
