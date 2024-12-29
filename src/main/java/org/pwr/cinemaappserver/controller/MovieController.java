package org.pwr.cinemaappserver.controller;

import org.pwr.cinemaappserver.entity.Movies;
import org.pwr.cinemaappserver.service.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movies>> getAllMovies() {
        final List<Movies> allMovies = movieService.getAllMovies();
        return allMovies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allMovies);
    }

    @PostMapping("/add-movie")
    public ResponseEntity<Movies> addMovie(@RequestParam String title, @RequestParam String time) {
        return ResponseEntity.ok(movieService.addMovie(title, time));
    }

    @GetMapping("/delete")
    public ResponseEntity<Void> deleteMovie(@RequestParam Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-movie")
    public ResponseEntity<Optional<Movies>> findMovie(@RequestParam Long id) {
        return ResponseEntity.ok(movieService.findMovieById(id));
    }
}
