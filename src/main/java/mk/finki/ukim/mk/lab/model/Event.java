package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Event {
    public Event(Long ID, String name, String description, double popularityScore, Location location) {
        this.id = ID;
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }

    public Event(String name, String description, double popularityScore, Location location) {
        this.id = (long)(Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double popularityScore;
    @Getter
    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;

    public Long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPopularityScore() {
        return popularityScore;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPopularityScore(double popularityScore) {
        this.popularityScore = popularityScore;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Event() {
        this.id = (long)(Math.random() * 1000);
        this.name = "";
        this.description = "";
        this.popularityScore = 0;
        this.location = null;
    }
}
