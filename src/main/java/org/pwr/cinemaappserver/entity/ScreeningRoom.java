package org.pwr.cinemaappserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "screening_rooms")
public class ScreeningRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "screeningRoom")
    @JsonBackReference
    private List<Showing> showings = new ArrayList<>();

    @OneToMany(mappedBy = "screeningRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Seat> seats = new ArrayList<>();
}
