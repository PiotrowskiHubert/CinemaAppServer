package org.pwr.cinemaappserver.service.showing;

import org.pwr.cinemaappserver.entity.Movie;
import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.pwr.cinemaappserver.entity.Seat;
import org.pwr.cinemaappserver.entity.Showing;
import org.pwr.cinemaappserver.service.movie.IMovieService;
import org.pwr.cinemaappserver.service.screeningRoom.IScreeningRoomService;
import org.pwr.cinemaappserver.service.seat.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShowingService {
    private final IShowingService showingService;
    private final IMovieService movieService;
    private final IScreeningRoomService screeningRoomService;
    private final SeatService seatService;

    public ShowingService(IShowingService showingService, IMovieService movieService, IScreeningRoomService screeningRoomService, SeatService seatService) {
        this.showingService = showingService;
        this.movieService = movieService;
        this.screeningRoomService = screeningRoomService;
        this.seatService = seatService;
    }

    public ResponseEntity<Showing> addShowing(String movieTitle,String screeningRoomName, String startTime) {
        final Optional<Movie> movie = movieService.findMovieByTitle(movieTitle);
        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final Optional<ScreeningRoom> screeningRoom = screeningRoomService.findByName(screeningRoomName);
        if (screeningRoom.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final Showing showing = Showing.builder()
                .movie(movie.get())
                .screeningRoom(screeningRoom.get())
                .startTime(startTime)
                .build();
        Showing savedShowing = showingService.add(showing);

        List<Seat> seats = new LinkedList<>();
        for (int i = 0; i < screeningRoom.get().getNumOfSeats(); i++) {
            Seat seat = Seat.builder()
                    .seatNumber(i + 1)
                    .isAvailable(true)
                    .showing(savedShowing)
                    .build();
            seats.add(seat);
            seatService.add(seat);
        }
        savedShowing.setSeats(seats);
        movie.get().getShowings().add(savedShowing);
        screeningRoom.get().getShowings().add(savedShowing);
        return ResponseEntity.ok(showing);
    }

    public ResponseEntity<List<Showing>> getAllShowings() {
        return showingService.getAllShowings();
    }

    public void deleteShowingById(Long id) {
        showingService.deleteShowingById(id);
    }

    public Showing patchUpdateShowingById(Long id, Map<String, Object> updates) {
        return showingService.patchUpdateShowingById(id, updates);
    }
}
