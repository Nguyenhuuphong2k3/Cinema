package com.example.cinema_frontend.controllers;

import com.example.cinema_frontend.constants.Api;
import com.example.cinema_frontend.models.CommentDTO;
import com.example.cinema_frontend.models.MovieDTO;
import com.example.cinema_frontend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("movie-details")
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @ModelAttribute("user")
    public User getUser() {
        return new User(); // Hoặc lấy user từ session nếu đã đăng nhập
    }

    // API endpoints
    public static final String API_GET_MOVIE_DETAILS = Api.baseURL + "/api/movies/details";
    public static final String API_GET_COMMENTS = Api.baseURL + "/api/comments/{movieId}";
    public static final String API_ADD_COMMENT = Api.baseURL + "/api/comments";

    // Hiển thị trang chi tiết phim
    @GetMapping
    public String displayMovieDetailPage(@RequestParam Integer movieId, Model model) {
        // Lấy thông tin chi tiết phim
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(API_GET_MOVIE_DETAILS)
                .queryParam("movieId", "{movieId}")
                .encode()
                .toUriString();
        ResponseEntity<MovieDTO> response = restTemplate.getForEntity(urlTemplate, MovieDTO.class, movieId);
        MovieDTO movie = response.getBody();

        // Lấy danh sách bình luận
        ResponseEntity<CommentDTO[]> commentResponse = restTemplate.getForEntity(API_GET_COMMENTS, CommentDTO[].class, movieId);
        List<CommentDTO> comments = Arrays.asList(commentResponse.getBody());

        // Thêm dữ liệu vào model
        model.addAttribute("movie", movie);
        model.addAttribute("comments", comments);
        return "movie-details";
    }

    // Xử lý gửi bình luận mới
    @PostMapping("/add-comment")
    public String addComment(@RequestParam Integer movieId, @RequestParam String content) {
        // Tạo đối tượng CommentDTO để gửi đến API backend
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setMovieId(movieId);
        commentDTO.setContent(content);

        HttpEntity<CommentDTO> request = new HttpEntity<>(commentDTO);
        restTemplate.postForEntity(API_ADD_COMMENT, request, Void.class);

        // Redirect lại trang chi tiết phim
        return "redirect:/movie-details?movieId=" + movieId;
    }
}
