package application;


import domain.Event;


import domain.User;
import infrastructure.EventRepositoryImpl;
import org.springframework.stereotype.Service;

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

}
