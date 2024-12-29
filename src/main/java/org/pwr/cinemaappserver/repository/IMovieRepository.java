package org.pwr.cinemaappserver.repository;

import org.pwr.cinemaappserver.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMovieRepository extends JpaRepository<Movies, Long> {
    Optional<Movies> findById(Long id);
}
