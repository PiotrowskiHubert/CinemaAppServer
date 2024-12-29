package org.pwr.cinemaappserver.service.movie;

import org.pwr.cinemaappserver.repository.IScreeningRoomRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class JpaScreeningRoomService implements IScreeningRoomService {
    private final IScreeningRoomRepository screeningRoomRepository;

    public JpaScreeningRoomService(IScreeningRoomRepository screeningRoomRepository) {
        this.screeningRoomRepository = screeningRoomRepository;
    }
}
