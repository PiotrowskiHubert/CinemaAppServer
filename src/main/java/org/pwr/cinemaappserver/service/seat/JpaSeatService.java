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

    public JpaSeatService(ISeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Seat add(Seat seat) {
        return seatRepository.save(seat);
    }
}


