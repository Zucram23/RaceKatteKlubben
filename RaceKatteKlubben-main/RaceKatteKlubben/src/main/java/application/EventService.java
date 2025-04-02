package application;


import domain.Event;
import domain.User;
import infrastructure.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repository){this.repository = repository;}

    public Event createEvent(Event event){return repository.createEvent(event);}

    public void updateEvent(Event event){ repository.updateEvent(event);}

    public List<Event> getAllEvents(Event event){return repository.getAllEvents();}

    public void deleteEvent(Event event){repository.deleteEvent(event.getId());}

    public List<Event> getEventsByUser(User user){return repository.getEventsByUser(user);}


}
