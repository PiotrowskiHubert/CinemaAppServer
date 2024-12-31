package org.pwr.cinemaappserver.service.seat;

import org.pwr.cinemaappserver.dto.SeatDTO;
import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.pwr.cinemaappserver.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ISeatService {
    Seat add(Seat Seat);
}
