package org.pwr.cinemaappserver.service.screeningRoom;

import org.pwr.cinemaappserver.dto.ScreeningRoomDTO;
import org.pwr.cinemaappserver.dto.SeatDTO;
import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.pwr.cinemaappserver.entity.Seat;
import org.pwr.cinemaappserver.repository.IScreeningRoomRepository;
import org.pwr.cinemaappserver.repository.ISeatRepository;
import org.pwr.cinemaappserver.service.seat.ISeatService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.LinkedList;


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

}
