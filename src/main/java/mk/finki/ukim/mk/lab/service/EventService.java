package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
}
