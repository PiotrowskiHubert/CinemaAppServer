package org.pwr.cinemaappserver.service.screeningRoom;

import org.pwr.cinemaappserver.dto.ScreeningRoomDTO;
import org.pwr.cinemaappserver.entity.ScreeningRoom;
import org.pwr.cinemaappserver.repository.IScreeningRoomRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Primary
public class JpaScreeningRoomService implements IScreeningRoomService {
    private final IScreeningRoomRepository screeningRoomRepository;

    public JpaScreeningRoomService(IScreeningRoomRepository screeningRoomRepository) {
        this.screeningRoomRepository = screeningRoomRepository;
    }

    @Override
    public ScreeningRoom add(ScreeningRoomDTO newScreeningRoom) {
        ScreeningRoom screeningRoom = ScreeningRoom.builder()
                .name(newScreeningRoom.getName())
                .build();
        return screeningRoomRepository.save(screeningRoom);
    }
    @Override
    public Optional<ScreeningRoom> findByName(String name) {
        return screeningRoomRepository.findByName(name);
    }

    @Override
    public void deleteScreeningRoomByName(String name) {
        screeningRoomRepository.deleteByName(name);
    }

    @Override
    public List<ScreeningRoom> getAllScreeningRooms() {
        return screeningRoomRepository.findAll();
    }

    @Override
    public ScreeningRoom patchUpdate(Long id, Map<String, Object> updates) {
        return screeningRoomRepository.findById(id)
                .map(existingScreeningRoom -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "name":
                                existingScreeningRoom.setName((String) value);
                                break;
//                            case "seats":
//                                existingScreeningRoom.setSeats(null);
//                                break;
                            default:
                                throw new IllegalArgumentException("Unknown key: " + key);
                        }
                    });
                    return screeningRoomRepository.save(existingScreeningRoom);
                })
                .orElseThrow(() -> new IllegalArgumentException("Screening room with id: " + id + " not found"));
    }
}
