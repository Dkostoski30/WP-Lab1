package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
public class Event {
    public Event(String name, String description, double popularityScore, Location location) {
        this.ID = (long)(Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }
    private Long ID;
    private String name;
    private String description;
    private double popularityScore;

    private Location location;
}
