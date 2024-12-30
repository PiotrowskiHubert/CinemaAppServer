package org.pwr.cinemaappserver.service.showing;

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
                        }
                    });
                    return showingRepository.save(existingShowing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Showing with id: " + id + " not found"));
    }
}
