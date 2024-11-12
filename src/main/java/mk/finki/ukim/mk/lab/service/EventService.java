package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    void deleteEvent(Long id);
    Event addEvent(Event event);
    Event addEvent(Event event, Long ID);
    Optional<Event> getEvent(Long id);
}
