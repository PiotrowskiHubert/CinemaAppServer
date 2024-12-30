package org.pwr.cinemaappserver.repository;

import org.pwr.cinemaappserver.entity.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShowingRepository extends JpaRepository<Showing, Long> {
    void deleteByMovieTitleAndScreeningRoomNameAndStartTime(String movieTitle, String screeningRoomName, String startTime);
}
