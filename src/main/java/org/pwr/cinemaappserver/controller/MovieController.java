package org.pwr.cinemaappserver.controller;

import org.pwr.cinemaappserver.dto.MovieDTO;
import org.pwr.cinemaappserver.entity.Movie;
import org.pwr.cinemaappserver.service.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController()
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        final List<Movie> allMovies = movieService.getAllMovies();
        return allMovies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allMovies);
    }

    @PostMapping("/add-movie")
    public ResponseEntity<Movie> addMovie(@RequestParam String title) {
        return ResponseEntity.ok(movieService.addMovie(title));
    }

    @GetMapping("/delete-movie")
    public ResponseEntity<Void> deleteMovie(@RequestParam Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-movie-by-title")
    public ResponseEntity<Optional<Movie>> findMovie(@RequestParam String title) {
        return ResponseEntity.ok(movieService.findMovieByTitle(title));
    }

    @GetMapping("/find-movie-by-id")
    public ResponseEntity<Optional<Movie>> findMovieById(@RequestParam Long id) {
        return ResponseEntity.ok(movieService.findMovieById(id));
    }

    @PatchMapping("/update-movie-by-title")
    public ResponseEntity<Movie> updateMovieByTitle(@RequestParam String title, @RequestBody Map<String, Object> updates){
        return ResponseEntity.ok(movieService.patchUpdateByTitle(title, updates));
    }

    @PatchMapping("/update-movie-by-id")
    public ResponseEntity<Movie> updateMovieById(@RequestParam Long id, @RequestBody Map<String, Object> updates){
        return ResponseEntity.ok(movieService.patchUpdateById(id, updates));
    }
}
