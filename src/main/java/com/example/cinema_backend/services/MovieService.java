package com.example.cinema_backend.services;

import com.example.cinema_backend.dtos.MovieDTO;
import com.example.cinema_backend.entities.Movie;
import com.example.cinema_backend.repositories.IMovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Tìm tất cả phim đang chiếu, có phân trang

    public Page<MovieDTO> findAllShowingMoviesPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findMoviesByIsShowingOrderByIdDesc(1, pageable)
                .map(movie -> modelMapper.map(movie, MovieDTO.class));
    }
    public Page<Movie> getMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable);
    }

    // Tìm tất cả phim đang chiếu
    @Override
    public List<MovieDTO> findAllShowingMovies() {
        return movieRepository.findMoviesByIsShowingOrderByIdDesc(1)
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    // Lấy chi tiết phim theo ID
    @Override
    public MovieDTO getById(Integer movieId) {
        return movieRepository.findById(movieId)
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .orElseThrow(() -> new RuntimeException("Movie with ID " + movieId + " not found"));
    }

    // Tìm phim theo tên, có phân trang

    public Page<MovieDTO> findAllShowingMoviesByNamePaged(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findMoviesByIsShowingAndNameContaining(1, keyword, pageable)
                .map(movie -> modelMapper.map(movie, MovieDTO.class));
    }

    // Tìm phim theo tên
    @Override
    public List<MovieDTO> findAllShowingMoviesByName(String keyword) {
        return movieRepository.findMoviesByIsShowingAndNameContaining(1, keyword)
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    // Thêm phim mới
    @Override
    public void addMovie(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        movieRepository.save(movie);
    }

    // Cập nhật thông tin phim
    @Override
    public void updateMovie(MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(movieDTO.getId())
                .orElseThrow(() -> new RuntimeException("Movie with ID " + movieDTO.getId() + " not found"));

        // Cập nhật thông tin
        modelMapper.map(movieDTO, movie);

        // Lưu thay đổi
        movieRepository.save(movie);
    }

    // Xóa phim
    @Override
    public void deleteMovie(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie with ID " + id + " not found");
        }
        movieRepository.deleteById(id);
    }
}
