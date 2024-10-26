package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.data.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventBookingRepository {

    public List<EventBooking> all(){
        return DataHolder.eventBookings;
    }
    public EventBooking save(String name, String attendee, String address, Long tickets){
        DataHolder.eventBookings
                .removeIf(eventBooking -> {
                   return eventBooking.getEventName().equals(name)
                           && eventBooking.getAttendeeName().equals(attendee)
                           && eventBooking.getAttendeeAddress().equals(address)
                           && eventBooking.getNumberOfTickets().equals(tickets);
                });
        EventBooking eventBooking = new EventBooking(name, attendee, address, tickets);
        DataHolder.eventBookings.add(eventBooking);
        return eventBooking;
    }
    public void delete(EventBooking eventBooking){
        DataHolder.eventBookings
                .removeIf(eventBooking::equals);
    }
}
