package com.example.cinema_backend.services;


import com.example.cinema_backend.dtos.MovieDTO;

import java.util.List;

public interface IMovieService {
    List<MovieDTO> findAllShowingMovies();
    MovieDTO getById(Integer movieId);
    List<MovieDTO> findAllShowingMoviesByName(String name);
    void addMovie(MovieDTO movieDTO);      // Thêm phim mới
    void updateMovie(MovieDTO movieDTO);   // Cập nhật phim
    void deleteMovie(Integer id);
}
