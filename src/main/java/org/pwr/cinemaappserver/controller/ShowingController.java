package org.pwr.cinemaappserver.controller;

import org.pwr.cinemaappserver.entity.Showing;
import org.pwr.cinemaappserver.service.showing.ShowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ShowingController {
    private final ShowingService showingService;

    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @PostMapping("/add-showing")
    public ResponseEntity<Showing> addShowing(@RequestParam String movieTitle, @RequestParam String screeningRoomName, @RequestParam String startTime) {
        return showingService.addShowing(movieTitle, screeningRoomName, startTime);
    }

    @GetMapping("/showings")
    public ResponseEntity<List<Showing>> getAllShowings() {
        return showingService.getAllShowings();
    }

    @GetMapping("/delete-showing-by-id")
    public ResponseEntity<Void> deleteShowingById(@RequestParam Long id) {
        showingService.deleteShowingById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update-showing-by-id")
    public ResponseEntity<Showing> updateShowingById(@RequestParam Long id, @RequestBody Map<String, Object> updates){
        return ResponseEntity.ok(showingService.patchUpdateShowingById(id, updates));
    }
}