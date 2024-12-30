package org.pwr.cinemaappserver.repository;

import org.pwr.cinemaappserver.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findById(Long id);
    Optional<Movie> findByTitle(String title);
}
