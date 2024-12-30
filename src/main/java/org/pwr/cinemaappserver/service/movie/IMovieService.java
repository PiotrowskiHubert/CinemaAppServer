package org.pwr.cinemaappserver.service.movie;

import org.pwr.cinemaappserver.dto.MovieDTO;
import org.pwr.cinemaappserver.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface IMovieService {
    Movie add(MovieDTO newMovie);
    Optional<Movie> getMovieById(Long id);
    Movie update(Movie movie);
    void deleteById(Long id);
    List<Movie> getAllMovies();
    Optional<Movie> findMovieByTitle(String title);
    Movie patchUpdateById(Long id, Map<String, Object> updates);
    Movie patchUpdateByTitle(String oldTitle, Map<String, Object> updates);
}
