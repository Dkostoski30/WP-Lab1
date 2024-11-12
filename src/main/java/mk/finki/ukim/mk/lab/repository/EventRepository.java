package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.data.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {


    public List<Event> findAll(){
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.events
                .stream()
                .filter(event -> event.getName().contains(text) || event.getDescription().contains(text))
                .toList();
    }
    public Optional<Event> findEvent(Long id) {
        return DataHolder.events
                .stream().filter(event -> event.getID().equals(id)).findFirst();
    }

    public void deleteEvent(Long id) {
        DataHolder.events.removeIf(event -> event.getID().equals(id));
    }
    public Event createEvent(Event event) {
        DataHolder.events.add(event);
        return event;
    }
    public Event update(Long ID, Event event) {
        deleteEvent(ID);
        DataHolder.events.add(event);
        return event;
    }
}
