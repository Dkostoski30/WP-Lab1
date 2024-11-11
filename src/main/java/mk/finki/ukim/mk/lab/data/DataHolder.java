package mk.finki.ukim.mk.lab.data;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class DataHolder {
    public static List<Event> events = null;
    public static List<EventBooking> eventBookings = null;
    public static List<Location> locations = null;
    @PostConstruct
    public static void init(){
        events = new ArrayList<>();
        eventBookings = new ArrayList<>();
        locations = new ArrayList<>();
        Random rand = new Random();
        locations.add(new Location("City Park", "123 Main St, Skopje", "500", "A large park with lakes and walking trails."));
        locations.add(new Location("Sports Arena", "456 Sports Rd, Skopje", "10000", "A venue for sporting events and concerts."));
        locations.add(new Location("Mountain View Resort", "789 Hillside Ave, Skopje", "150", "A mountain resort offering breathtaking views and hiking trails."));
        locations.add(new Location("Old Town Square", "321 Heritage St, Skopje", "200", "Historic town square with cafes and shops."));
        locations.add(new Location("Modern Art Gallery", "654 Art Blvd, Skopje", "50", "An art gallery showcasing contemporary artworks."));

        events.add(new Event("Music Concert", "An outdoor music concert", 8.7, locations.get(rand.nextInt(locations.size()))));
        events.add(new Event("Art Exhibition", "A modern art exhibition", 7.5, locations.get(rand.nextInt(locations.size()))));
        events.add(new Event("Tech Conference", "A tech event about AI", 9.3, locations.get(rand.nextInt(locations.size()))));
        events.add(new Event("Science Expo", "Science fair showcasing innovations", 8.9, locations.get(rand.nextInt(locations.size()))));
        events.add(new Event("Theatre Play", "A local theatre performance", 7.7, locations.get(rand.nextInt(locations.size()))));
    }
}
