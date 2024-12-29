package org.pwr.cinemaappserver.service.screeningRoom;

import jakarta.transaction.Transactional;
import org.pwr.cinemaappserver.dto.MovieDTO;
import org.pwr.cinemaappserver.entity.Movies;
import org.pwr.cinemaappserver.repository.IMovieRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class JpaMovieService implements IMovieService {
    private final IMovieRepository movieRepository;

    public JpaMovieService(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public Movies add(MovieDTO newMovie) {
        final Movies movie = Movies.builder()
                .title(newMovie.getTitle())
                .time(newMovie.getTime())
                .build();

        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movies> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movies update(Movies movies) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movies> getAllMovies() {
        return movieRepository.findAll();
    }
}
