package org.pwr.cinemaappserver.service.movie;

import org.pwr.cinemaappserver.service.seat.ISeatService;
import org.springframework.stereotype.Service;

@Service
public class ScreeningRoomService {
    private final IScreeningRoomService screeningRoomService;
    private final ISeatService seatService;

    public ScreeningRoomService(IScreeningRoomService screeningRoomService, ISeatService seatService) {
        this.screeningRoomService = screeningRoomService;
        this.seatService = seatService;
    }
}
