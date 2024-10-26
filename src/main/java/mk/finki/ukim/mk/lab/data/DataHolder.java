package mk.finki.ukim.mk.lab.data;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataHolder {
    public static List<Event> events = null;
    public static List<EventBooking> eventBookings = null;
    @PostConstruct
    public static void init(){
        events = new ArrayList<>();
        eventBookings = new ArrayList<>();
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
}
