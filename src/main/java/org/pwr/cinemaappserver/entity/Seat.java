package org.pwr.cinemaappserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "showing_id", nullable = false)
    @JsonBackReference
    private Showing showing;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;
}
