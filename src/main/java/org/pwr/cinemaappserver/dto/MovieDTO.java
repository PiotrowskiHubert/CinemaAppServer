package org.pwr.cinemaappserver.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MovieDTO {
    private String title;
    private String time;
}
