package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {
    public List<Event> events = null;
    @PostConstruct
    public void init(){
        events = new ArrayList<>();
        events.add(new Event("Music Concert", "An outdoor music concert", 8.7));
        events.add(new Event("Art Exhibition", "A modern art exhibition", 7.5));
        events.add(new Event("Tech Conference", "A tech event about AI", 9.3));
        events.add(new Event("Food Festival", "A festival with street food vendors", 8.2));
        events.add(new Event("Charity Run", "A 5k run for charity", 7.9));
        events.add(new Event("Film Screening", "Independent film screening", 6.8));
        events.add(new Event("Book Fair", "Annual book fair with local authors", 7.4));
        events.add(new Event("Startup Pitch", "Pitch event for tech startups", 8.1));
        events.add(new Event("Science Expo", "Science fair showcasing innovations", 8.9));
        events.add(new Event("Theatre Play", "A local theatre performance", 7.7));
    }

    public List<Event> findAll(){
        return events;
    }

    public List<Event> searchEvents(String text) {
        return events
                .stream()
                .filter(event -> event.getName().contains(text) || event.getDescription().contains(text))
                .toList();
    }
}
