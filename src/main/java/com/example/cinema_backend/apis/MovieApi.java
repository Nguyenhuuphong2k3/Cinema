package com.example.cinema_backend.apis;

import com.example.cinema_backend.dtos.MovieDTO;
import com.example.cinema_backend.entities.Movie;
import com.example.cinema_backend.repositories.IMovieRepository;
import com.example.cinema_backend.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/movies", produces = "application/json")
public class MovieApi {
    @Autowired
    private IMovieService movieService;

    @Autowired
    private IMovieRepository movieRepository;

    @GetMapping("/showing")
    public ResponseEntity<List<MovieDTO>> findAllShowingMovies(){
        return new ResponseEntity<>(movieService.findAllShowingMovies(), HttpStatus.OK);
    }

    @GetMapping("/details")
    public MovieDTO getMovieById(@RequestParam Integer movieId){
        return movieService.getById(movieId);
    }

    @GetMapping("/showing/search")
    public List<MovieDTO> findAllShowingMoviesByName(@RequestParam String name){
        return movieService.findAllShowingMoviesByName(name);
    }

    @PostMapping
    public void updateMovie(@RequestBody Movie movie){
        movieRepository.save(movie);
    }

    // Endpoint thêm phim
    @PostMapping("/add")
    public ResponseEntity<Void> addMovie(@RequestBody MovieDTO movieDTO) {
        movieService.addMovie(movieDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);  // Trả về mã trạng thái CREATED
    }

    // Endpoint chỉnh sửa phim
    @PutMapping("/edit")
    public ResponseEntity<Void> editMovie(@RequestBody MovieDTO movieDTO) {
        movieService.updateMovie(movieDTO);
        return new ResponseEntity<>(HttpStatus.OK);  // Trả về mã trạng thái OK
    }

    // Endpoint xóa phim
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Trả về mã trạng thái NO_CONTENT khi xóa thành công
    }
}
