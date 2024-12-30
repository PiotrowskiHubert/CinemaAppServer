package org.pwr.cinemaappserver.service.screeningRoom;

import org.pwr.cinemaappserver.dto.ScreeningRoomDTO;
import org.pwr.cinemaappserver.dto.SeatDTO;
import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.pwr.cinemaappserver.entity.Seat;
import org.pwr.cinemaappserver.repository.IScreeningRoomRepository;
import org.pwr.cinemaappserver.repository.ISeatRepository;
import org.pwr.cinemaappserver.service.seat.ISeatService;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ScreeningRoomService {
    private final IScreeningRoomService screeningRoomService;
    private final ISeatService seatService;

    public ScreeningRoomService(IScreeningRoomService screeningRoomService, ISeatService seatService) {
        this.screeningRoomService = screeningRoomService;
        this.seatService = seatService;
    }

    public ScreeningRoom addScreeningRoom(String name, int numOfSeats) {
        ScreeningRoomDTO newScreeningRoom = ScreeningRoomDTO.builder()
                .name(name)
                .build();
        screeningRoomService.add(newScreeningRoom);

        final ScreeningRoom screeningRoom = screeningRoomService.findByName(newScreeningRoom.getName()).get();

        for (int i = 0; i < numOfSeats; i++) {
            SeatDTO seat = SeatDTO.builder()
                    .screeningRoom(screeningRoom)
                    .seatNumber(i + 1)
                    .isAvailable(true)
                    .build();
            seatService.add(seat);
        }

        screeningRoom.setSeats(seatService.getSeatsByScreeningRoomName(screeningRoom.getName()).get());

        return screeningRoomService.findByName(newScreeningRoom.getName()).get();
    }

    public Optional<ScreeningRoom> getScreeningRoomByName(String name) {
        return screeningRoomService.findByName(name);
    }

    public void deleteScreeningRoomByName(String name) {
        screeningRoomService.deleteScreeningRoomByName(name);
    }

    public List<ScreeningRoom> getAllScreeningRooms() {
        return screeningRoomService.getAllScreeningRooms();
    }

    public ResponseEntity<ScreeningRoom> patchUpdate(Long id, Map<String, Object> updates) {
        return ResponseEntity.ok(screeningRoomService.patchUpdate(id, updates));
    }
}
