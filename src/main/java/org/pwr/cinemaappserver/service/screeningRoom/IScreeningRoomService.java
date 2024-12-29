package org.pwr.cinemaappserver.service.screeningRoom;

import org.pwr.cinemaappserver.dto.ScreeningRoomDTO;
import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IScreeningRoomService {
    ScreeningRoom add(ScreeningRoomDTO newScreeningRoom);
    Optional<ScreeningRoom> findByName(String name);
}
