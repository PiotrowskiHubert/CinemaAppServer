package org.pwr.cinemaappserver.service.showing;

import org.pwr.cinemaappserver.entity.Showing;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IShowingService {
    Showing add(Showing showing);
    ResponseEntity<List<Showing>> getAllShowings();
    void deleteShowingById(Long id);
    Showing patchUpdateShowingById(Long id, Map<String, Object> updates);
}
