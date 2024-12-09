package mk.finki.ukim.mk.lab.data;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class DataHolder implements CommandLineRunner {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    public static List<EventBooking> eventBookings;
    public DataHolder(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        eventBookings = new ArrayList<>();
    }
    @Override
    public void run(String... args) {
        if (locationRepository.count() == 0) {
            Location location1 = new Location("City Park", "123 Main St, Skopje", "500", "A large park with lakes and walking trails.");
            Location location2 = new Location("Sports Arena", "456 Sports Rd, Skopje", "10000", "A venue for sporting events and concerts.");
            Location location3 = new Location("Mountain View Resort", "789 Hillside Ave, Skopje", "150", "A mountain resort offering breathtaking views and hiking trails.");
            Location location4 = new Location("Old Town Square", "321 Heritage St, Skopje", "200", "Historic town square with cafes and shops.");
            Location location5 = new Location("Modern Art Gallery", "654 Art Blvd, Skopje", "50", "An art gallery showcasing contemporary artworks.");

            locationRepository.save(location1);
            locationRepository.save(location2);
            locationRepository.save(location3);
            locationRepository.save(location4);
            locationRepository.save(location5);

            Random rand = new Random();
            eventRepository.save(new Event("Music Concert", "An outdoor music concert", 8.7, location1));
            eventRepository.save(new Event("Art Exhibition", "A modern art exhibition", 7.5, location2));
            eventRepository.save(new Event("Tech Conference", "A tech event about AI", 9.3, location3));
            eventRepository.save(new Event("Science Expo", "Science fair showcasing innovations", 8.9, location4));
            eventRepository.save(new Event("Theatre Play", "A local theatre performance", 7.7, location5));
        }
    }
}
