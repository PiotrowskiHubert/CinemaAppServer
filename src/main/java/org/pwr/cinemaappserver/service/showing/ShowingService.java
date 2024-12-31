package org.pwr.cinemaappserver.service.showing;

import org.pwr.cinemaappserver.entity.Movie;
import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.pwr.cinemaappserver.entity.Showing;
import org.pwr.cinemaappserver.service.movie.IMovieService;
import org.pwr.cinemaappserver.service.screeningRoom.IScreeningRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShowingService {
    private final IShowingService showingService;
    private final IMovieService movieService;
    private final IScreeningRoomService screeningRoomService;

    public ShowingService(IShowingService showingService, IMovieService movieService, IScreeningRoomService screeningRoomService) {
        this.showingService = showingService;
        this.movieService = movieService;
        this.screeningRoomService = screeningRoomService;
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

        return ResponseEntity.ok(showingService.add(showing));
    }

    public ResponseEntity<List<Showing>> getAllShowings() {
        return showingService.getAllShowings();
    }

    public void deleteShowing(String movieTitle, String screeningRoomName, String startTime) {
        showingService.deleteShowing(movieTitle, screeningRoomName, startTime);
    }

    public void deleteShowingById(Long id) {
        showingService.deleteShowingById(id);
    }

    public Showing patchUpdateShowingById(Long id, Map<String, Object> updates) {
        return showingService.patchUpdateShowingById(id, updates);
    }
}
