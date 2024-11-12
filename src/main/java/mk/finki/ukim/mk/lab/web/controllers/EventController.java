package mk.finki.ukim.mk.lab.web.controllers;


import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;
    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", error);
        }else{
            List<Event> events = eventService.listAll();
            model.addAttribute("events_list", events);
        }
        return "listEvents";
    }
    @PostMapping(path = "/create")
    public String createEvent(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String popularityScore,
                              @RequestParam String locationId,
                              Model model){
        Location location = locationService.findById(Long.parseLong(locationId)).orElse(null);
        eventService.addEvent(new Event(name, description, Double.parseDouble(popularityScore), location));
        return "redirect:/events";
    }


    @GetMapping(path = "/add-form")
    public String getAddEventPage(Model model){
        Event event = new Event("", "", 0.0, null);
        model.addAttribute("event", event);
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }
    @PostMapping(path = "/add/{id}")
    public String addEvent(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String description,
                           @RequestParam Double popularityScore,
                           @RequestParam Long locationId
                           ){
        Event new_event = new Event(id, name, description, popularityScore, locationService.findById(locationId).orElse(null));
        eventService.addEvent(new_event, id);
        return "redirect:/events";
    }


    @GetMapping(path = "/edit/{id}")
    public String getEditPage(@PathVariable Long id, Model model){
        Optional<Event> event = eventService.getEvent(id);
        if(event.isPresent()){
            model.addAttribute("event", event.get());
            List<Location> locations = locationService.findAll();
            model.addAttribute("locations", locations);
            return "add-event";
        }else{

            model.addAttribute("errorMessage", "Event not found");
            return "errorPage";
        }
    }
    @PostMapping(path = "/edit/{id}")
    public String updateEvent(@PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Double popularityScore,
                              @RequestParam Long locationId,
                              Model model){

        Optional<Event> op_event = eventService.getEvent(id);
        if(op_event.isPresent()){
            Optional<Location> op_location = locationService.findById(locationId);
            Event new_event = new Event(name, description, popularityScore, op_location.orElse(null));
            eventService.addEvent(new_event, id);
            return "redirect:/events";
        }else{
            model.addAttribute("errorMessage", "Event not found");
            return "errorPage";
        }
    }
    @PostMapping(path = "/delete/{id}")
    public String deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return "redirect:/events";
    }
    @PostMapping(path = "/search")
    public String searchEvents(@RequestParam(required = false) String query,
                               @RequestParam(required = false) Double rating,
                               Model model){
        double minRating;
        System.out.println(query);
        minRating = Objects.requireNonNullElse(rating, 0.0);
        List<Event> events = eventService.searchEvents(query).stream().filter(event -> event.getPopularityScore() > minRating).toList();
        model.addAttribute("events_list", events);
        model.addAttribute("locations", locationService.findAll());
        return "listEvents";
    }

}
