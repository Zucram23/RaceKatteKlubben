package application;

import infrastructure.EventParticipantsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventParticipantsService {

private final EventParticipantsRepository eventParticipantsRepository;

public EventParticipantsService(EventParticipantsRepository eventParticipantsRepository) {
    this.eventParticipantsRepository = eventParticipantsRepository;
}

public void enterEvent(Long userId, Long eventId, List<Long> catIds) {
    eventParticipantsRepository.enterEvent(userId, eventId, catIds);
    }

public List<String> getEventParticipantsWithCats(int eventId) {
    return eventParticipantsRepository.getEventParticipantsWithCats(eventId);
}

public void removeCatFromEvent(int eventId, int userId, int catId) {
    eventParticipantsRepository.removeCatFromEvent(eventId, userId, catId);
}

}
