package org.pwr.cinemaappserver.service.movie;

import org.pwr.cinemaappserver.dto.MovieDTO;
import org.pwr.cinemaappserver.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {
    private final IMovieService movieService;

    public MovieService(IMovieService movieService) {
        this.movieService = movieService;
    }

    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    public Movie addMovie(String title) {
        MovieDTO newMovie = MovieDTO.builder()
                .title(title)
                .build();

        return movieService.add(newMovie);
    }

    public void deleteMovie(Long id) {
        movieService.deleteById(id);
    }
    public Optional<Movie> findMovieById(Long id) {
        return movieService.getMovieById(id);
    }
    public Optional<Movie> findMovieByTitle(String title) {
        return movieService.findMovieByTitle(title);
    }

    public Movie updateMovie(Long id, String title) {
        Movie movie = findMovieById(id).orElseThrow();
        movie.setTitle(title);

        return movieService.update(movie);
    }

    public Movie updateMovieByTitle(String oldTitle, String newTitle) {
        Movie movie = findMovieByTitle(oldTitle).orElseThrow();
        movie.setTitle(newTitle);

        return movieService.update(movie);
    }

    public Movie patchUpdateById(Long id, Map<String, Object> updates) {
        return movieService.patchUpdateById(id, updates);
    }

    public Movie patchUpdateByTitle(String oldTitle, Map<String, Object> updates) {
        return movieService.patchUpdateByTitle(oldTitle, updates);
    }
}
