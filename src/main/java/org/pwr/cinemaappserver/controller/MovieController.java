package org.pwr.cinemaappserver.controller;

import org.pwr.cinemaappserver.entity.Movies;
import org.pwr.cinemaappserver.service.DBService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DBController {
    private final DBService dbService;

    public DBController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movies>> getAllMovies() {
        final List<Movies> allMovies = dbService.getAllMovies();
        return allMovies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allMovies);
    }

    @PostMapping("/add-movie")
    public ResponseEntity<Movies> addMovie(@RequestParam String title, @RequestParam String time) {
        return ResponseEntity.ok(dbService.addMovie(title, time));
    }

    @GetMapping("/delete")
    public ResponseEntity<Void> deleteMovie(@RequestParam Long id) {
        dbService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-movie")
    public ResponseEntity<Optional<Movies>> findMovie(@RequestParam Long id) {
        return ResponseEntity.ok(dbService.findMovieById(id));
    }
}
