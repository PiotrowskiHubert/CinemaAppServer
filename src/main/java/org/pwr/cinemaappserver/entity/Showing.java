package org.pwr.cinemaappserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "showings")
public class Showing {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonManagedReference
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screening_room_id", nullable = false)
    @JsonManagedReference
    private ScreeningRoom screeningRoom;

    private String startTime;
}
