package org.pwr.cinemaappserver.service.screeningRoom;

import org.pwr.cinemaappserver.dto.ScreeningRoomDTO;
import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface IScreeningRoomService {
    ScreeningRoom add(ScreeningRoomDTO newScreeningRoom);
    Optional<ScreeningRoom> findByName(String name);
    void deleteScreeningRoomByName(String name);
    List<ScreeningRoom> getAllScreeningRooms();
    ScreeningRoom patchUpdate(Long id, Map<String, Object> updates);
}
