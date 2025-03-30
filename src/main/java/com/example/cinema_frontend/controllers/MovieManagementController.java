package com.example.cinema_frontend.controllers;

import com.example.cinema_frontend.constants.Api;
import com.example.cinema_frontend.models.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/movie-management")
public class MovieManagementController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_BASE_URL = Api.baseURL + "/api/movies";

    @GetMapping
    public String listMovies(Model model) {
        MovieDTO[] movies = restTemplate.getForObject(API_BASE_URL + "/showing", MovieDTO[].class);
        model.addAttribute("movies", movies);
        return "movie-management"; // Tên view hiển thị danh sách phim
    }

    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("movie", new MovieDTO());
        return "movie-add"; // Tên view cho trang thêm phim
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute MovieDTO movie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MovieDTO> request = new HttpEntity<>(movie, headers);
        restTemplate.postForEntity(API_BASE_URL + "/add", request, Void.class);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String displayEditForm(@PathVariable int id, Model model) {
        MovieDTO movie = restTemplate.getForObject(API_BASE_URL + "/details?movieId=" + id, MovieDTO.class);
        model.addAttribute("movie", movie);
        return "movie-edit"; // Tên view cho trang sửa phim
    }

    @PostMapping("/edit")
    public String editMovie(@ModelAttribute MovieDTO movie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MovieDTO> request = new HttpEntity<>(movie, headers);
        restTemplate.put(API_BASE_URL + "/edit", request);

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable int id) {
        restTemplate.delete(API_BASE_URL + "/delete/" + id);
        return "redirect:/";
    }
}
