package org.pwr.cinemaappserver.service.screeningRoom;

import org.pwr.cinemaappserver.dto.MovieDTO;
import org.pwr.cinemaappserver.entity.Movies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IMovieService {
    Movies add(MovieDTO newMovie);
    Optional<Movies> getMovieById(Long id);
    Movies update(Movies movies);
    void deleteById(Long id);
    List<Movies> getAllMovies();
}
