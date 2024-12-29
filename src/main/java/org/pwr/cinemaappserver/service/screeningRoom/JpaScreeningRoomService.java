package org.pwr.cinemaappserver.service.screeningRoom;

import org.pwr.cinemaappserver.dto.ScreeningRoomDTO;
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
public class JpaScreeningRoomService implements IScreeningRoomService {
    private final IScreeningRoomRepository screeningRoomRepository;
    private final ISeatRepository seatRepository;

    public JpaScreeningRoomService(IScreeningRoomRepository screeningRoomRepository, ISeatRepository seatRepository) {
        this.screeningRoomRepository = screeningRoomRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public ScreeningRoom add(ScreeningRoomDTO newScreeningRoom) {
        ScreeningRoom screeningRoom = ScreeningRoom.builder()
                .name(newScreeningRoom.getName())
                .build();

        return screeningRoomRepository.save(screeningRoom);
    }

    @Override
    public Optional<ScreeningRoom> findByName(String name) {
        return screeningRoomRepository.findByName(name);
    }

    public Optional<ScreeningRoom> getScreeningRoomWithSeats(ScreeningRoom screeningRoom) {
        Optional<List<Seat>> seats = seatRepository.findAllByScreeningRoom(screeningRoom);
        if (seats.isPresent()) {
            screeningRoom.setSeats(seats.get());
        }
        return Optional.of(screeningRoom);
    }
}
