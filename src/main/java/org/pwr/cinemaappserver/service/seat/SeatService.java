package org.pwr.cinemaappserver.service.seat;

import org.pwr.cinemaappserver.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {
    private final ISeatService seatService;

    public SeatService(ISeatService seatService) {
        this.seatService = seatService;
    }

    public Seat add(Seat seat) {
        return seatService.add(seat);
    }
}
