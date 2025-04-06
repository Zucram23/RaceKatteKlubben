package application;


import domain.Event;


import domain.User;
import infrastructure.EventRepositoryImpl;
import org.springframework.stereotype.Service;
import domain.AccessDeniedException;
import java.util.List;

@Service
public class EventServiceImpl implements ServiceInterface<Event> {

    private final EventRepositoryImpl repository;

    public EventServiceImpl(EventRepositoryImpl repository){this.repository = repository;}


    @Override
    public Event save(Event event){return repository.save(event);}

    @Override
    public List<Event> findAll(){return repository.findAll();}

    @Override
    public void update(Event event){ repository.update(event);}

    @Override
    public void delete(int id){repository.delete(id);}


    public List<Event> getEventsByUser(User user){return repository.getEventsByUser(user);}


    public Event getEventById(int id){return repository.getById(id);}

    public void delete(Long eventId, Long userId) {
        Event event = repository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event ikke fundet"));

        if (event.getAdmin_id() != userId.intValue()) {
            throw new AccessDeniedException("Du må kun slette dine egne events");
        }
        repository.delete(eventId.intValue());
    }
    public Event findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event ikke fundet"));
    }

}
