package org.pwr.cinemaappserver.controller;

import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.pwr.cinemaappserver.entity.Seat;
import org.pwr.cinemaappserver.service.screeningRoom.ScreeningRoomService;
import org.pwr.cinemaappserver.service.seat.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ScreeningRoomController {
    private final ScreeningRoomService screeningRoomService;
    private final SeatService seatService;

    public ScreeningRoomController(ScreeningRoomService screeningRoomService, SeatService seatService) {
        this.screeningRoomService = screeningRoomService;
        this.seatService = seatService;
    }

    @PostMapping("/add-screening-room")
    public ResponseEntity<ScreeningRoom> addScreeningRoom(@RequestParam String name, @RequestParam int numOfSeats) {
        return ResponseEntity.ok(screeningRoomService.addScreeningRoom(name, numOfSeats));
    }

    @GetMapping("/find-screening-room-by-name")
    public ResponseEntity<Optional<ScreeningRoom>> getScreeningRoomByName(@RequestParam String name) {
        return ResponseEntity.ok(screeningRoomService.getScreeningRoomByName(name));
    }

    @GetMapping("/delete-screening-room-by-name")
    public ResponseEntity<Void> deleteScreeningRoomByName(@RequestParam String name) {
        screeningRoomService.deleteScreeningRoomByName(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("get-all-screening-rooms")
    public ResponseEntity<List<ScreeningRoom>> getAllScreeningRooms() {
        return ResponseEntity.ok(screeningRoomService.getAllScreeningRooms());
    }

    @PatchMapping("/update-screening-room")
    public ResponseEntity<ScreeningRoom> updateScreeningRoom(@RequestParam Long id, @RequestBody Map<String, Object> updates){
        return screeningRoomService.patchUpdate(id, updates);
    }
}
