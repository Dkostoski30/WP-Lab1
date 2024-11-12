package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplementation implements EventService {
    public EventRepository eventRepository;

    public EventServiceImplementation(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteEvent(id);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.createEvent(event);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        return eventRepository.update(id, event);
    }

    @Override
    public Optional<Event> getEvent(Long id) {
        return eventRepository.findEvent(id);
    }
}
