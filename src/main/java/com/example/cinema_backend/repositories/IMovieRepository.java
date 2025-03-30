package com.example.cinema_backend.repositories;

import com.example.cinema_backend.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findMoviesByIsShowingOrderByIdDesc(Integer isShowing);

    Page<Movie> findMoviesByIsShowingOrderByIdDesc(Integer isShowing, Pageable pageable);

    List<Movie> findMoviesByIsShowingAndNameContaining(Integer isShowing, String keyword);

    Page<Movie> findMoviesByIsShowingAndNameContaining(Integer isShowing, String keyword, Pageable pageable);
}
