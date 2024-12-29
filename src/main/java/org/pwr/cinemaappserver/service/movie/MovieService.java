package org.pwr.cinemaappserver.service.screeningRoom;

import org.pwr.cinemaappserver.dto.MovieDTO;
import org.pwr.cinemaappserver.entity.Movies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final IMovieService movieService;

    public MovieService(IMovieService movieService) {
        this.movieService = movieService;
    }

    public List<Movies> getAllMovies() {
        return movieService.getAllMovies();
    }

    public Movies addMovie(String title, String time) {
        MovieDTO newMovie = MovieDTO.builder()
                .title(title)
                .time(time)
                .build();

        return movieService.add(newMovie);
    }

    public void deleteMovie(Long id) {
        movieService.deleteById(id);
    }

    public Optional<Movies> findMovieById(Long id) {
        return movieService.getMovieById(id);
    }
}
