package org.pwr.cinemaappserver.service.seat;

import org.pwr.cinemaappserver.dto.SeatDTO;
import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.pwr.cinemaappserver.entity.Seat;
import org.pwr.cinemaappserver.repository.IScreeningRoomRepository;
import org.pwr.cinemaappserver.repository.ISeatRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class JpaSeatService implements ISeatService {
    private final ISeatRepository seatRepository;
    private final IScreeningRoomRepository screeningRoomRepository;

    public JpaSeatService(ISeatRepository seatRepository, IScreeningRoomRepository screeningRoomRepository) {
        this.seatRepository = seatRepository;
        this.screeningRoomRepository = screeningRoomRepository;
    }

    @Override
    public Seat add(SeatDTO newSeat) {
        final Seat seat = Seat.builder()
                .screeningRoom(newSeat.getScreeningRoom())
                .seatNumber(newSeat.getSeatNumber())
                .isAvailable(newSeat.isAvailable())
                .build();
        return seatRepository.save(seat);
    }

    @Override
    public Optional<List<Seat>> getSeatsByScreeningRoomName(String screeningRoomName) {
        final Optional<ScreeningRoom> screeningRoom = screeningRoomRepository.findByName(screeningRoomName);
        if (screeningRoom.isPresent()){
            return seatRepository.findAllByScreeningRoom(screeningRoom.get());
        }else {
            return Optional.empty();
        }
    }
}


