package org.pwr.cinemaappserver.repository;

import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IScreeningRoomRepository extends JpaRepository<ScreeningRoom, Long> {
    Optional<ScreeningRoom> findByName(String name);
}
