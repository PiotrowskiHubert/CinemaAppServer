package org.pwr.cinemaappserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.pwr.cinemaappserver.entity.Seat;

import java.util.LinkedList;

@Data
@Builder
@AllArgsConstructor
public class ScreeningRoomDTO {
    private String name;
}
