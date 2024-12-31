package org.pwr.cinemaappserver.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.pwr.cinemaappserver.entity.ScreeningRoom;

@Data
@Builder
@AllArgsConstructor
public class SeatDTO {
//    private ScreeningRoom screeningRoom;
    private int seatNumber;
    private boolean isAvailable;
}
