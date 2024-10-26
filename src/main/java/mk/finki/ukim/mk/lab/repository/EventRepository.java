package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.data.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

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

}
