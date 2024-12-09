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
        return eventRepository.findAll().stream()
                .filter(event -> event.getName().contains(text) || event.getDescription().contains(text))
                .toList();
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event addEvent(Event event){
        return eventRepository.save(event);
    }

    @Override
    public List<Event> searchByLocationID(Long locationID) {
        return eventRepository.findAllByLocation_Id(locationID);
    }

    @Override
    public Event addEvent(Event event, Long id) {
        event.setId(id);
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> getEvent(Long id) {
        return eventRepository.findById(id);
    }
}
