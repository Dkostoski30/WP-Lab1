package mk.finki.ukim.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/eventBooking")
public class EventBookingController {
    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }
    private String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
    private final EventBookingService eventBookingService;
    @PostMapping(path = "/book-event")
    public String bookEvent(@RequestParam String event_name,
                            @RequestParam Integer numTickets,
                            @RequestParam String name,
                            HttpServletRequest request,
                            Model model) {
        EventBooking booking = eventBookingService.placeBooking(event_name, name, getClientIpAddress(request), numTickets);
        model.addAttribute("booking", booking);
        List<EventBooking> userBookings = eventBookingService.listAll().stream().filter(eventBooking -> eventBooking.getAttendeeName().equals(name)).toList();
        model.addAttribute("booking_list", userBookings);
        return "bookingConfirmation";
    }
}
