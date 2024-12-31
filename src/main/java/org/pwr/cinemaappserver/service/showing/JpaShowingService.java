package org.pwr.cinemaappserver.service.showing;

import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.pwr.cinemaappserver.entity.Seat;
import org.pwr.cinemaappserver.entity.Showing;
import org.pwr.cinemaappserver.repository.IShowingRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Primary
public class JpaShowingService implements IShowingService {
    private final IShowingRepository showingRepository;

    public JpaShowingService(IShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    @Override
    public Showing add(Showing showing) {
        return showingRepository.save(showing);
    }

    @Override
    public ResponseEntity<List<Showing>> getAllShowings() {
        return ResponseEntity.ok(showingRepository.findAll());
    }

    @Override
    public void deleteShowing(String movieTitle, String screeningRoomName, String startTime) {
        showingRepository.deleteByMovieTitleAndScreeningRoomNameAndStartTime(movieTitle, screeningRoomName, startTime);
    }

    @Override
    public void deleteShowingById(Long id) {
        showingRepository.deleteById(id);
    }

    @Override
    public Showing patchUpdateShowingById(Long id, Map<String, Object> updates) {
        return showingRepository.findById(id)
                .map(existingShowing -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "startTime":
                                existingShowing.setStartTime((String) value);
                                break;
                            case "screeningRoom":
                                Map<String, Object> screeningRoomUpdates = (Map<String, Object>) value;
                                updateScreeningRoom(existingShowing.getScreeningRoom(), screeningRoomUpdates);
                                break;
                            default:
                                throw new IllegalArgumentException("Unknown key: " + key);
                        }
                    });
                    return showingRepository.save(existingShowing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Showing with id: " + id + " not found"));
    }

    private void updateScreeningRoom(ScreeningRoom screeningRoom, Map<String, Object> updates) {
        updates.forEach((key, value) -> {
            switch (key) {
                case "seats":
                    List<Map<String, Object>> seatUpdates = (List<Map<String, Object>>) value;
                    updateSeatsAvailability(screeningRoom.getSeats(), seatUpdates);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown key: " + key);
            }
        });
    }

    private void updateSeatsAvailability(List<Seat> seats, List<Map<String, Object>> seatUpdates) {
        seatUpdates.forEach(seatUpdate -> {
            Long seatId =((Number) seatUpdate.get("id")).longValue();
            Boolean isAvailable = (Boolean) seatUpdate.get("isAvailable");

            seats.stream()
                    .filter(seat -> seat.getId().equals(seatId))
                    .findFirst()
                    .ifPresent(seat -> seat.setAvailable(isAvailable));
        });
    }
}
