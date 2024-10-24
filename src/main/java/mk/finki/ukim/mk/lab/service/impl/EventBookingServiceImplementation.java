package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.EventBookingRepository;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImplementation implements EventBookingService {
    public EventBookingServiceImplementation(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    public EventBookingRepository eventBookingRepository;
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        if(eventName.isEmpty() || attendeeName.isBlank() || attendeeName.isBlank()){
            throw new IllegalArgumentException("One or more arguments are not valid");
        }
        Long tickets = Long.parseLong(String.valueOf(numberOfTickets));
        return eventBookingRepository.save(eventName, attendeeName, attendeeAddress, tickets);
    }

    @Override
    public List<EventBooking> listAll() {
        return eventBookingRepository.all();
    }

}
