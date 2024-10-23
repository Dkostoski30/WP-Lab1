package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Event {
    private String name;
    private String description;
    private double popularityScore;
}