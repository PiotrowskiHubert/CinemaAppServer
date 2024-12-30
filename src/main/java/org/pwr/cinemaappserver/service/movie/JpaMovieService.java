package org.pwr.cinemaappserver.service.movie;

import jakarta.transaction.Transactional;
import org.pwr.cinemaappserver.dto.MovieDTO;
import org.pwr.cinemaappserver.entity.Movie;
import org.pwr.cinemaappserver.repository.IMovieRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
    public Movie add(MovieDTO newMovie) {
        final Movie movie = Movie.builder()
                .title(newMovie.getTitle())
                .build();

        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public Movie patchUpdateById(Long id, Map<String, Object> updates) {
        return movieRepository.findById(id)
                .map(existingMovie -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "title":
                                existingMovie.setTitle((String) value);
                                break;
                            default:
                                throw new IllegalArgumentException("Unknown key: " + key);
                        }
                    });

                    return movieRepository.save(existingMovie);
                })
                .orElseThrow(() -> new IllegalArgumentException("Movie with id: " + id + " not found"));
    }

    @Override
    public Movie patchUpdateByTitle(String oldTitle, Map<String, Object> updates) {
        return movieRepository.findByTitle(oldTitle)
                .map(existingMovie -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "title":
                                existingMovie.setTitle((String) value);
                                break;
                            default:
                                throw new IllegalArgumentException("Unknown key: " + key);
                        }
                    });

                    return movieRepository.save(existingMovie);
                })
                .orElseThrow(() -> new IllegalArgumentException("Movie with title: " + oldTitle + " not found"));
    }
}
