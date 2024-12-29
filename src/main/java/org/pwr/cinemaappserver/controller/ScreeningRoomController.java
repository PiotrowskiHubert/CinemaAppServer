package org.pwr.cinemaappserver.controller;

import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.pwr.cinemaappserver.entity.Seat;
import org.pwr.cinemaappserver.service.screeningRoom.ScreeningRoomService;
import org.pwr.cinemaappserver.service.seat.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ScreeningRoomController {
    private final ScreeningRoomService screeningRoomService;
    private final SeatService seatService;

    public ScreeningRoomController(ScreeningRoomService screeningRoomService, SeatService seatService) {
        this.screeningRoomService = screeningRoomService;
        this.seatService = seatService;
    }

    @PostMapping("/screening-room")
    public ResponseEntity<ScreeningRoom> addScreeningRoom(@RequestParam String name, @RequestParam int numOfSeats) {
        return ResponseEntity.ok(screeningRoomService.addScreeningRoom(name, numOfSeats));
    }

    @GetMapping("/screening-room-find-seats-by-name")
    public ResponseEntity<Optional<List<Seat>>> getSeatsForScreeningRoom(@RequestParam String name) {
        return ResponseEntity.ok(seatService.getSeatsByScreeningRoomName(name));
    }

    @GetMapping("/screening-room-find-by-name")
    public ResponseEntity<List<ScreeningRoom>> getScreeningRoomByName(@RequestParam String name) {
        return ResponseEntity.ok(screeningRoomService.getScreeningRoomByName(name));
    }
}
